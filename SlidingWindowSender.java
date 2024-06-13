import java.io.*;
import java.net.*;

public class SlidingWindowSender {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress IPAddress = InetAddress.getByName("localhost");
            int port = 9877;

            // Sliding window parameters
            int windowSize = 4;
            int numPackets = 10;

            int base = 0;
            int nextSeqNum = 0;

            // Sender loop
            while (base < numPackets) {
                while (nextSeqNum < base + windowSize && nextSeqNum < numPackets) {
                    String packetData = "Packet" + nextSeqNum;
                    byte[] sendData = packetData.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    socket.send(sendPacket);
                    System.out.println("Sent: " + packetData);
                    nextSeqNum++;
                }

                // Listen for ACKs
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    socket.setSoTimeout(1000);
                    socket.receive(receivePacket);
                    String ack = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                    int ackNum = Integer.parseInt(ack);
                    System.out.println("Received ACK: " + ackNum);

                    // Slide window
                    if (ackNum >= base) {
                        base = ackNum + 1;
                    }
                } catch (SocketTimeoutException e) {
                    // Timeout, resend all packets in window
                    nextSeqNum = base;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
