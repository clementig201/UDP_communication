package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) {
        try {
            int port = 3000;
            DatagramSocket dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");
            byte[] bufferIn = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(inPacket);//metodo bloccante
            System.out.println("Ricezione effettuata");
            String message = new String (inPacket.getData(), inPacket.getLength());
            InetAddress clientAddress = inPacket.getAddress();
            int p = inPacket.getPort();
            DatagramPacket output = new DatagramPacket(message.getBytes(), message.length(), clientAddress, p);
            dSocket.send(output);

        }catch (SocketException e){
          throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
