import java.util.*;

public class crsimple { 

    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in); 

        System.out.print("Enter the Generator: "); 
        String gen = s.nextLine(); 

        System.out.print("Enter the Data: "); 
        String data = s.nextLine(); 

        String code = data + "0".repeat(gen.length() - 1); 
        code = data + div(code, gen);     

        System.out.println("The transmitted code word is: " + code);    

        System.out.print("Please enter the received code word: "); 
        String rec = s.nextLine(); 

        if (Integer.parseInt(div(rec, gen)) == 0) 
            System.out.println("The received code word contains no errors"); 
        else 
            System.out.println("The received code word contains errors"); 
    } 

    static String div(String n1, String n2) { 
        String remainder = n1.substring(0, n2.length());

        for (int i = n2.length(); i <= n1.length(); i++) { 
            if (remainder.charAt(0) == '1') 
                remainder = xor(remainder, n2);
            if (i < n1.length())
                remainder = remainder.substring(1) + n1.charAt(i); 
        } 
        return remainder.substring(1); 
    } 

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) 
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1'); 
        return result.toString(); 
    }
}
