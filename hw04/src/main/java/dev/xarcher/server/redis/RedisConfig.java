package dev.xarcher.server.redis;

import lombok.experimental.UtilityClass;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@UtilityClass
public class RedisConfig {
    public static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://192.168.10.241:6379",
                        "redis://192.168.10.241:6380",
                        "redis://192.168.10.242:6379",
                        "redis://192.168.10.242:6380",
                        "redis://192.168.10.243:6379",
                        "redis://192.168.10.243:6380");

        return Redisson.create(config);
    }
}
