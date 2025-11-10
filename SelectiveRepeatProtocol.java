import java.util.*;
public class SelectiveRepeatProtocol {
    private final int totalFrames, windowSize;
    private final boolean[] sent, acked;
    private int base = 1;
    public SelectiveRepeatProtocol(int totalFrames, int windowSize) {
        this.totalFrames = totalFrames;
        this.windowSize = windowSize;
        this.sent = new boolean[totalFrames + 1];
        this.acked = new boolean[totalFrames + 1];
    }
    private void Sender(Scanner sc) {
        while (base <= totalFrames) {
            int windowEnd = Math.min(base + windowSize - 1, totalFrames);
            for (int i = base; i <= windowEnd; i++) if (!sent[i]) Send(i);
            for (int f = base; f <= windowEnd; f++) {
                if (!acked[f]) {
                    System.out.print("Ack for frame " + f + " received ? (y/n) : ");
                    String response = sc.next().trim();
                    if (response.equalsIgnoreCase("y")) {
                        acked[f] = true;
                        System.out.println("Sender : Ack received for frame " + f);
                        while (base <= totalFrames && acked[base]) base++;
                        int nextToSend = Math.min(base + windowSize - 1, totalFrames);
                        if (nextToSend > windowEnd && nextToSend <= totalFrames && !sent[nextToSend]) Send(nextToSend);
                    } else if (response.equalsIgnoreCase("n")) {
                        System.out.println("Sender : Ack lost for frame " + f + ", resending...");
                        Send(f);
                    } else System.out.println("Invalid input. Enter y or n.");
                }
            }
        }
        System.out.println("All frames sent and acknowledged successfully.");
    }
    private void Send(int frameNo) {
        System.out.println("Sender : Sending frame " + frameNo);
        sent[frameNo] = true;
        delay(100);
    }
    private void delay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of frames : ");
        int totalFrames = sc.nextInt();
        System.out.print("Enter Window Size : ");
        int windowSize = sc.nextInt();
        new SelectiveRepeatProtocol(totalFrames, windowSize).Sender(sc);
        sc.close();
    }
}