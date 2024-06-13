import java.io.*; 

import java.util.*; 

public class CRC { 

    public static void main(String[] args)throws IOException { 

       Scanner s = new Scanner (System.in); 

       System.out.print("Enter the Generator:"); 

       String gen = s.nextLine(); 

       System.out.print("Enter the Data:"); 

       String data = s.nextLine(); 

       String code = data; 

       int k = gen.length()-1; 

       for(int i = 0; i<k;i++) 

           code  += "0"; 

       code = data + div(code,gen);     

       System.out.println("The transmitted code word is: "+code);    

       System.out.print("Please enter the received code word: "); 

       String rec = s.nextLine(); 

       if(Integer.parseInt(div(rec,gen)) == 0) 

           System.out.println("The received code word contains no errors"); 

       else 

           System.out.println("The received code word contains errors"); 

    } 

     

    static String div(String n1, String n2) { 

        String remainder = ""; 

        if (n1.charAt(0) == '0') { 

            remainder += n1.substring(0, n2.length()); 

        }  

        else { 

            for (int i = 0; i < n2.length(); i++) { 

                if (n1.charAt(i) == n2.charAt(i)) 

                    remainder += "0"; 

                else 

                    remainder += "1"; 

            } 

        } 

 

 

        int p = n2.length(); 

        while (p < n1.length()) { 

            remainder = remainder.substring(1, remainder.length()); 

            remainder = remainder + String.valueOf(n1.charAt(p)); 

            p++; 

            if (remainder.charAt(0) == '0') { 

                continue; 

            } 

            String result = ""; 

            for (int i = 0; i < n2.length(); i++) { 

                if (remainder.charAt(i) == n2.charAt(i)) 

                    result += "0"; 

                else 

                    result += "1"; 

            } 

            remainder = result; 

        } 

        return remainder.substring(1, remainder.length()); 

    } 

} 