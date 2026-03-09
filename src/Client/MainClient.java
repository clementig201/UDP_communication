package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class MainClient {
    public static void main(String[] args) {

        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            System.out.println("Indirizzo del server trovato!");
            DatagramSocket dSocket = new DatagramSocket();
            String message = "ciao";
            int port = 3000;
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            dSocket.send(outPacket);
            byte[] buffer = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(inPacket);
            String response = new String(inPacket.getData(), 0, inPacket.getLength());
            dSocket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    }