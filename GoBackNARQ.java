
import java.util.*;
public class GoBackNARQ {
    private int totalFrames;
    private int windowSize;
    private int base = 0;
    private int nextFrame = 0;
    private final int TIMEOUT = 3000;
    private Scanner sc = new Scanner(System.in);
    public GoBackNARQ(int totalFrames, int windowSize) {
        this.totalFrames = totalFrames;
        this.windowSize = windowSize;
    }
    public void SendFrames() {
        while (base < totalFrames) {
            while (nextFrame < base + windowSize && nextFrame < totalFrames) {
                System.out.println("Sending frame " + nextFrame);
                nextFrame++;
            }
            System.out.print("Was Ack for frame " + base + " received ? (y/n) : ");
            String ackInput = sc.next();
            if (ackInput.equals("y")) {
                System.out.println("Ack received for frame " + base);
                base++;
            } else if (ackInput.equals("n")) {
                System.out.println("Ack for frame " + base + " lost. Waiting for timeout...");
                try { Thread.sleep(TIMEOUT); } catch (InterruptedException e) {}
                int end = Math.min(base + windowSize, totalFrames);
                System.out.println("Timeout! Resending frames from " + base + " to " + (end - 1));
                for (int i = base; i < end; i++) System.out.println("Sending frame " + i);
                nextFrame = end;
            } else System.out.println("Invalid input. Enter y or n.");
        }
        System.out.println("All frames acknowledged.");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of frames to send : ");
        int totalFrames = sc.nextInt();
        System.out.print("Enter Window Size : ");
        int windowSize = sc.nextInt();
        if (windowSize > totalFrames) windowSize = totalFrames;
        new GoBackNARQ(totalFrames, windowSize).SendFrames();
        sc.close();
    }
}
