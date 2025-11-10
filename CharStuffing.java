import java.util.*;

class CharStuffing {

    static String charStuff(String s) {
        int n = s.length();
        String res = "";

        for (int i = 0; i < n; i++) {
         
            if (i == 0) {
                res += 'F';   
            }

            char ch = s.charAt(i);
            if (ch == 'E' || ch == 'F') {
                res += 'E'; 
                res += ch;
            } else {
                res += ch;
            }

         
            if (i == n - 1) {
                res += 'F';  
            }
        }
        return res;
    }


    static String charUnstuff(String s) {
        int n = s.length();
        String res = "";

       
        for (int i = 1; i < n - 1; i++) {
            char ch = s.charAt(i);
            if (ch == 'E') {
               
                i++;
                res += s.charAt(i);
            } else {
                res += ch;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the data to be stuffed: ");
        String s = sc.nextLine();   
        String stuffed = charStuff(s);
        String unstuffed = charUnstuff(stuffed);

        System.out.println("\nOriginal Data:   " + s);
        System.out.println("Stuffed Frame:   " + stuffed);
        System.out.println("Unstuffed Data:  " + unstuffed);

        sc.close();
    }
}
