import java.io.*; 
import java.net.*; 

class rarpser { 
    public static void main(String args[]) { 
        try { 
            DatagramSocket server = new DatagramSocket(1309); 
            System.out.println("RARP Server is running...");

            while (true) { 
                byte[] receivebyte = new byte[1024]; 
                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length); 
                server.receive(receiver); 

                String str = new String(receiver.getData()).trim(); 
                InetAddress addr = receiver.getAddress(); 
                int port = receiver.getPort(); 

                String ip[] = {"165.165.80.80", "165.165.79.1"}; 
                String mac[] = {"6A:08:AA:C2", "8A:BC:E3:FA"}; 

                boolean found = false;
                for (int i = 0; i < mac.length; i++) { 
                    if (str.equals(mac[i])) { 
                        byte[] sendbyte = ip[i].getBytes(); 
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port); 
                        server.send(sender); 
                        found = true;
                        break; 
                    } 
                } 
                if (!found) {
                    String notFound = "MAC Address not found";
                    byte[] sendbyte = notFound.getBytes(); 
                    DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port); 
                    server.send(sender);
                }
            } 
        } catch (IOException e) { 
            System.out.println(e); 
        } 
    } 
}
