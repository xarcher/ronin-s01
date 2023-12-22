package dev.xarcher.server;

import dev.xarcher.server.dns.DNSServer;
import dev.xarcher.server.redis.RedisConfig;

import java.util.Random;

public class ServerApplication {
    public static void main(String[] args) {
        var dnsServer = new DNSServer(new Random(), RedisConfig.getRedissonClient());
        var udpServer = new UDPServer(5004, dnsServer);

        udpServer.listen();
    }
}
