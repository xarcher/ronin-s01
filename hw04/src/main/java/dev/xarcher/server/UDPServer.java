package dev.xarcher.server;

import dev.xarcher.server.dns.DNSRequest;
import dev.xarcher.server.dns.DNSServer;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UDPServer {
    private final int port;
    private final DNSServer dnsServer;

    public void listen() {
        try {
            var serverSocket = new DatagramSocket(this.port);
            System.out.printf("Server started. Listening for clients on port %d%n", this.port);
            var receiveData = new byte[1024];
            DatagramPacket receivePacket;

            while (true) {
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                var clientAddress = receivePacket.getAddress();
                var clientPort = receivePacket.getPort();

                var clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.printf("-> [%s ,IP: %s,Port: %d] request: %s%n",
                        LocalDateTime.now(), clientAddress, clientPort, clientMessage);

                var result = dnsServer.lookup(new DNSRequest(clientMessage))
                        .orElse("Cannot find ip for domain %s".formatted(clientMessage));

                System.out.printf("<- [%s ,IP: %s,Port: %d] response: %s%n",
                        LocalDateTime.now(), clientAddress, clientPort, result);

                var buffer = result.getBytes();
                var response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                serverSocket.send(response);
            }
        } catch (IOException e) {
            System.out.printf("Ex: %s%n", e.getMessage());
        }
    }
}
