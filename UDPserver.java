import java.io.*;
import java.net.*;

public class UDPserver {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            System.out.println("Server is running on port 9876...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + sentence);

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                socket.send(sendPacket);
            }
        } catch (IOException ex) {
            System.err.println("Server error: " + ex.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
