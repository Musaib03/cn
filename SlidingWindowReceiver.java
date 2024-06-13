import java.io.*;
import java.net.*;

public class SlidingWindowReceiver {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(9877)) {
            int expectedSeqNum = 0;

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String packetData = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                int seqNum = Integer.parseInt(packetData.replace("Packet", ""));
                System.out.println("Received: " + packetData);

                if (seqNum == expectedSeqNum) {
                    System.out.println("Packet " + seqNum + " accepted.");
                    expectedSeqNum++;
                } else {
                    System.out.println("Packet " + seqNum + " discarded.");
                }

                String ack = String.valueOf(seqNum);
                byte[] sendAckData = ack.getBytes();
                DatagramPacket sendAckPacket = new DatagramPacket(sendAckData, sendAckData.length, receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendAckPacket);
                System.out.println("Sent ACK: " + ack);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
