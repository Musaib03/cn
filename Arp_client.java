import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Arp_client { 
    public static void main(String args[]) { 
        try { 
            Scanner s = new Scanner(System.in); 
            InetAddress ia = InetAddress.getLocalHost(); 
            Socket socket = new Socket(ia, 8020); 

            DataInputStream din = new DataInputStream(socket.getInputStream()); 
            PrintStream ps = new PrintStream(socket.getOutputStream()); 

            System.out.println("Enter the logical address (IP): "); 
            String str1 = s.nextLine(); 
            ps.println(str1); 

            String str = din.readLine(); 
            System.out.println("The physical Address is: " + str); 

            socket.close(); 
        } catch (IOException e) { 
            System.out.println(e); 
        } 
    } 
}
