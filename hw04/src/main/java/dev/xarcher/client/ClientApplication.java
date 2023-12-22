package dev.xarcher.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientApplication {
    public static void main(String[] args) throws IOException {
        var address = "127.0.0.1";
        var port = 5004;
        var domain = "thanbv.com";

        var clientSocket = new DatagramSocket();
        var request = domain.getBytes();
        var dataRequest = new DatagramPacket(request, request.length, InetAddress.getByName(address), port);
        clientSocket.send(dataRequest);

        var buffer = new byte[1024];
        var dataResponse = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(dataResponse);

        var response = new String(buffer, 0, dataResponse.getLength());

        System.out.printf("-> response: %s", response);
    }
}
