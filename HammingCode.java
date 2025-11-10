import java.util.*;

public class HammingCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Sender Side :");
        System.out.print("Enter no of data bits : ");
        int m = sc.nextInt();
        int[] data = new int[m];
        System.out.println("Enter data Bits : ");
        for (int i = 0; i < m; i++) {
            data[i] = sc.nextInt();
        }
        
        int r = 0;
        while (Math.pow(2, r) < m + r + 1) {
            r++;
        }
        
        int[] hamming = new int[m + r + 1];
        int dataIndex = 0;
        
        for (int i = 1; i < hamming.length; i++) {
            if (isPowerOfTwo(i)) {
                hamming[i] = 0;
            } else {
                hamming[i] = data[dataIndex];
                dataIndex++;
            }
        }
        
        for (int i = 0; i < r; i++) {
            int pos = (int) Math.pow(2, i);
            int val = 0;
            for (int k = 1; k < hamming.length; k++) {
                if (((k >> i) & 1) == 1 && k != pos) {
                    val ^= hamming[k];
                }
            }
            hamming[pos] = val;
        }
        
        System.out.print("Genrated hamming code : ");
        for (int i = 1; i < hamming.length; i++) {
            System.out.print(hamming[i]);
        }
        System.out.println();
        
        System.out.println("Reciver side:");
        System.out.print("Enter Recieved message : ");
        String recv = sc.next();
        int[] recvd = new int[recv.length() + 1];
        
        for (int i = 1; i <= recv.length(); i++) {
            recvd[i] = recv.charAt(i - 1) - '0';
        }
        
        int errorPos = 0;
        for (int i = 0; i < r; i++) {
            int pos = (int) Math.pow(2, i);
            int val = 0;
            for (int k = 1; k < recvd.length; k++) {
                if (((k >> i) & 1) == 1) {
                    val ^= recvd[k];
                }
            }
            if (val != 0) {
                errorPos += pos;
            }
        }
        
        if (errorPos == 0) {
            System.out.println("Message Recieved correctly ");
        } else {
            System.out.println("Error Detected at position " + errorPos);
            System.out.print("Corrected message : ");
            recvd[errorPos] = 1 - recvd[errorPos];
            for (int i = 1; i < recvd.length; i++) {
                System.out.print(recvd[i]);
            }
            System.out.println();
        }
        
        sc.close();
    }
    
    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}