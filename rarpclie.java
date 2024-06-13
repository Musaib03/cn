import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

class rarpclie { 
    public static void main(String args[]) { 
        try { 
            DatagramSocket csocket = new DatagramSocket(); 
            InetAddress ia = InetAddress.getLocalHost(); 

            byte[] sData = new byte[1024]; 
            byte[] rData = new byte[1024]; 

            Scanner s = new Scanner(System.in); 
            System.out.println("Enter the Physical address (MAC):"); 
            String str = s.nextLine(); 
            sData = str.getBytes(); 

            DatagramPacket sender = new DatagramPacket(sData, sData.length, ia, 1309); 
            csocket.send(sender); 

            DatagramPacket receiver = new DatagramPacket(rData, rData.length); 
            csocket.receive(receiver); 

            String res = new String(receiver.getData()).trim(); 
            System.out.println("The Logical Address (IP) is: " + res); 

            csocket.close(); 
        } catch (IOException e) { 
            System.out.println(e); 
        } 
    } 
}
