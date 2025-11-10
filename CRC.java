
import java.util.*;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Frame Size: ");
        int n = sc.nextInt();
        int[] frame = new int[n];
        System.out.println("Enter Frame bits as a single binary string (e.g., 1010): ");
        String frameStr = sc.next();
        for (int i = 0; i < n; i++) frame[i] = frameStr.charAt(i) - '0';

        System.out.println("Enter Highest Power of x in Generator: ");
        int gp = sc.nextInt();
        int[] gen = new int[gp + 1];
        System.out.println("Enter Generator Coefficients:");
        for (int i = gp; i >= 0; i--) {
            System.out.println("x^" + i + ": ");
            gen[i] = sc.nextInt();
        }

        int[] tx = Arrays.copyOf(frame, n + gp);
        int[] crc = divide(tx.clone(), gen);

        System.out.print("CRC: ");
        for (int b : crc) System.out.print(b);
        System.out.println();

        for (int i = 0; i < gp; i++) tx[n + i] = crc[i];
        System.out.print("Transmitted Frame: ");
        for (int b : tx) System.out.print(b);
        System.out.println();

        System.out.println("Enter Received Frame as a single binary string: ");
        String recStr = sc.next();
        int rsize = recStr.length();
        int[] rec = new int[rsize];
        for (int i = 0; i < rsize; i++) rec[i] = recStr.charAt(i) - '0';

        int[] rem = divide(rec.clone(), gen);
        System.out.print("Remainder: ");
        boolean error = false;
        for (int b : rem) {
            System.out.print(b);
            if (b != 0) error = true;
        }
        System.out.println();
        System.out.println(error ? "Error Detected" : "No Error Detected");

        sc.close();
    }
    public static int[] divide(int[] data, int[] div) {
        int gp = div.length - 1;
        for (int i = 0; i <= data.length - div.length; i++)
            if (data[i] == 1)
                for (int j = 0; j < div.length; j++)
                    data[i + j] ^= div[j];
        return Arrays.copyOfRange(data, data.length - gp, data.length);
    }
}
