package Server;

import java.io.IOException;
import java.net.*;

public class MainServer {
    public static void main(String[] args) {
        try {
            int port = 3000;
            DatagramSocket dSocket = new DatagramSocket(port);
            // è un server perché lo instanziamo con una porta quindi le primitive sono socket() e bind()
            System.out.println("Apertura porta in corso!");
            // 1 ricevo le informazioni nel buffer in
            byte[] bufferIn = new byte[256];
            // 2 decido il nome del datagram packet
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
            // 3 metodo bloccante
            dSocket.receive(inPacket);
            // 4 stampo che ho ricevuto i messagi
            System.out.println("Ricezione effettuata");

            //1 prendiamo l' indirizzo del client
            String message = new String(inPacket.getData(), 0, inPacket.getLength());
            InetAddress clientAddress = inPacket.getAddress();
            int p = inPacket.getPort();
            //2 instanzio una datagram socket
            DatagramPacket output = new DatagramPacket(message.getBytes(), message.length(), clientAddress, p);
            //3 spedisco il messaggio output
            dSocket.send(output);

        }catch (BindException e){
          System.err.println("porta già in uso");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
