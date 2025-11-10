import java.util.*;

public class Checksum {
    static int addAndWrap(int a, int b) {
        int s = a + b;
        while (s > 255) s = (s & 255) + (s >> 8);
        return s;
    }

    static String checksum(String[] data) {
        int sum = 0;
        for (String s : data) sum = addAndWrap(sum, Integer.parseInt(s, 2));
        int cs = ~sum & 255;
        return String.format("%8s", Integer.toBinaryString(cs)).replace(' ', '0');
    }

    static boolean verify(String[] data) {
        int sum = 0;
        for (String s : data) sum = addAndWrap(sum, Integer.parseInt(s, 2));
        System.out.println("Final 8-bit sum: " + String.format("%8s",
                Integer.toBinaryString(sum)).replace(' ', '0'));
        return sum == 255;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("8-bit One's Complement Checksum Program");
        System.out.print("No. of data segments: ");
        int n = sc.nextInt();
        String[] d = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter 8-bit data " + (i + 1) + ": ");
            d[i] = sc.next();
        }
        String cs = checksum(d);
        System.out.println("Calculated Checksum: " + cs);
        String[] all = Arrays.copyOf(d, n + 1);
        all[n] = cs;
        System.out.println("Sender Verification: " + verify(all));
        System.out.print("Verify at receiver? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter number of received segments: ");
            int m = sc.nextInt();
            String[] r = new String[m];
            for (int i = 0; i < m; i++) {
                System.out.print("Received segment " + (i + 1) + ": ");
                r[i] = sc.next();
            }
            System.out.println("Receiver Verification: " + verify(r));
        }
    }
}
