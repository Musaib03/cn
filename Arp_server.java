import java.io.*; 
import java.net.*; 

public class Arp_server { 
    public static void main(String args[]) { 
        try { 
            ServerSocket serverSocket = new ServerSocket(8020); 
            System.out.println("ARP Server is running and waiting for a connection...");
            Socket clientSocket = serverSocket.accept(); 
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(clientSocket.getInputStream()); 
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream()); 
            
            String ip[] = {"165.165.80.80", "165.165.79.1"}; 
            String mac[] = {"6A:08:AA:C2", "8A:BC:E3:FA"}; 

            while (true) { 
                String str = din.readLine(); 
                if (str == null) {
                    break;
                }
                
                boolean found = false;
                for (int i = 0; i < ip.length; i++) { 
                    if (str.equals(ip[i])) { 
                        dout.writeBytes(mac[i] + '\n'); 
                        found = true;
                        break; 
                    } 
                } 
                if (!found) {
                    dout.writeBytes("MAC Address not found\n");
                }
            } 

            clientSocket.close(); 
            serverSocket.close(); 
        } catch (Exception e) { 
            System.out.println(e); 
        } 
    } 
}
