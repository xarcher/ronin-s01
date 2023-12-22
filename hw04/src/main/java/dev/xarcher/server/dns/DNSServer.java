package dev.xarcher.server.dns;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;

public record DNSServer(Random random, RedissonClient redissonClient) {

    public Optional<String> lookup(final DNSRequest request) {
        if (!request.isValid()) {
            return Optional.empty();
        }

        RBucket<String> ipBucket = redissonClient.getBucket(request.domain(), new StringCodec());
        if (ipBucket.isExists()) {
            System.out.printf("-> Found ip %s in cache for domain %s%n", ipBucket.get(), request.domain());
            return Optional.of(ipBucket.get());
        }

        var ip = "%d.%d.%d.%d".formatted(random.nextInt(256), random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
        ipBucket.set(ip, Duration.ofMillis(10_000));
        System.out.printf("-> Get ip %s for domain %s success%n", ip, request.domain());

        return Optional.of(ip);
    }
}
