package ronin.s01.hw02;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class CacheManager {
    public static <T> Cache<String, T> createCacheManager(int size, Long expireTime) {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMillis(expireTime))
                .maximumSize(size)
                .build();
    }
}
