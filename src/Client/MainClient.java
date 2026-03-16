package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class MainClient {
    public static void main(String[] args) {

        try {
            InetAddress serverAddress = InetAddress.getLocalHost();//1 prendiamo l' indirizzo del server
            System.out.println("Indirizzo del server trovato!");
            DatagramSocket dSocket = new DatagramSocket();//2 instanzio una datagram socket
            String message = "ciao";//3 scrivo un messaggio
            int port = 3000;
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
            dSocket.send(outPacket);//4 spedisco il messaggio

            // 1 ricevo le informazioni nel buffer in
            byte[] buffer = new byte[256];
            // 2 decido il nome del datagram packet
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            // 3 metodo bloccante
            dSocket.receive(inPacket);
            // 4 stampo che ho ricevuto i messagi
            System.out.println("Ricezione effettuata" + inPacket);
            dSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("non trovo l'indirizzo del server");
        } catch(SocketException e){
            System.err.println("socket non trovato");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    }