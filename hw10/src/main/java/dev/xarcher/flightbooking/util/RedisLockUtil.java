package dev.xarcher.flightbooking.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisLockUtil {

    private final StringRedisTemplate stringRedisTemplate;

    private static final DefaultRedisScript<Boolean> LOCK_REDIS_SCRIPT;
    private static final DefaultRedisScript<Boolean> UNLOCK_REDIS_SCRIPT;

    private static final long DEFAULT_RETRY_LOCK = 100;

    static {
        LOCK_REDIS_SCRIPT = new DefaultRedisScript<>();
        LOCK_REDIS_SCRIPT.setLocation(new ClassPathResource("scripts/lock.lua"));
        LOCK_REDIS_SCRIPT.setResultType(Boolean.class);

        UNLOCK_REDIS_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_REDIS_SCRIPT.setLocation(new ClassPathResource("scripts/unlock.lua"));
        UNLOCK_REDIS_SCRIPT.setResultType(Boolean.class);
    }

    public boolean lock(final Object lockKey, final String clientLockRequest, final long expireTime) {
        var keys = Collections.singletonList(String.valueOf(lockKey));
        var argvs = new Object[]{clientLockRequest, String.valueOf(expireTime)};

        return Boolean.TRUE.equals(stringRedisTemplate.execute(LOCK_REDIS_SCRIPT, keys, argvs));
    }

    public boolean tryLock(final Object lockKey,
                           final String clientLockRequest,
                           final long expireTime,
                           final long waitTime) {
        var isAcquireLock = false;
        try {

            isAcquireLock = lock(lockKey, clientLockRequest, expireTime);
            log.info("=> First lock key = {}, value = {}, result = {}", lockKey, clientLockRequest, isAcquireLock);
            if (isAcquireLock) {
                return true;
            }
            var expiredWaitTime = LocalDateTime.now().plusNanos(TimeUnit.MICROSECONDS.toNanos(waitTime));
            while (LocalDateTime.now().isBefore(expiredWaitTime) || !isAcquireLock) {
                Thread.sleep(DEFAULT_RETRY_LOCK);

                isAcquireLock = lock(lockKey, clientLockRequest, expireTime);
                log.debug("=> Retry lock key = {}, value = {}", lockKey, clientLockRequest);
            }

        } catch (Exception ex) {
            log.warn("{}", ex.getMessage(), ex);
        }

        return isAcquireLock;
    }

    public boolean unlock(final Object lockKey, final String clientLockRequest) {
        var keys = Collections.singletonList(String.valueOf(lockKey));
        var argvs = new Object[]{clientLockRequest};

        return Boolean.TRUE.equals(stringRedisTemplate.execute(UNLOCK_REDIS_SCRIPT, keys, argvs));
    }
}
