import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receiveData = new byte[1024];

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter message: ");
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("From Server: " + modifiedSentence);

        } catch (IOException ex) {
            System.err.println("Client error: " + ex.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
