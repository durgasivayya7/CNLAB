import java.util.*;

public class ChecksumMethodInteractive {
    static String calcChecksum(String[] data) {
        int sum = 0;
        for (String s : data) {
            sum += Integer.parseInt(s, 2);
            while (sum > 0xFF) sum = (sum & 0xFF) + (sum >> 8);
        }
        int chk = (~sum) & 0xFF;
        return String.format("%8s", Integer.toBinaryString(chk)).replace(' ', '0');
    }

    static boolean verify(String[] segs) {
        int sum = 0;
        for (String s : segs) {
            sum += Integer.parseInt(s, 2);
            while (sum > 0xFF) sum = (sum & 0xFF) + (sum >> 8);
        }
        return sum == 0xFF;
    }

    static boolean valid(String s) {
        return s.matches("[01]{8}");
    }

    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of 8-bit segments: ");
        int n = sc.nextInt();
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.print("Segment " + (i + 1) + ": ");
                data[i] = sc.next();
            } while (!valid(data[i]));
        }
        String chk = calcChecksum(data);
        System.out.println("\nChecksum: " + chk);
        String[] recv = Arrays.copyOf(data, n + 1);
        recv[n] = chk;
        System.out.println("Verification (auto): " + verify(recv));
        System.out.print("\nVerify custom set? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("How many segments: ");
            int m = sc.nextInt();
            String[] r = new String[m];
            for (int i = 0; i < m; i++) {
                do {
                    System.out.print("Segment " + (i + 1) + ": ");
                    r[i] = sc.next();
                } while (!valid(r[i]));
            }
            System.out.println("Verification result: " + verify(r));
        }
        sc.close();
        System.out.println("Done.");
    }
}
