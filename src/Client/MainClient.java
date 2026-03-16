package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class MainClient {
    public static void main(String[] args) {

        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            System.out.println("Indirizzo del server trovato!");
            DatagramSocket dSocket = new DatagramSocket();

            // Lettura messaggio da tastiera
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Inserisci il messaggio: ");
            String message = reader.readLine();

            int port = 3000;
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            dSocket.send(outPacket);

            byte[] buffer = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            dSocket.receive(inPacket);
            System.out.println("Ricezione effettuata: " + new String(inPacket.getData(), 0, inPacket.getLength()));
            dSocket.close();

        } catch (UnknownHostException e) {
            System.err.println("non trovo l'indirizzo del server");
        } catch (SocketException e) {
            System.err.println("socket non trovato");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}