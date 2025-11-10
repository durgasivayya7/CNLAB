import java.util.*;
public class StopAndWaitProtocol {
    private final int totalFrames;
    private final int TIMEOUT = 3000;
    public StopAndWaitProtocol(int totalFrames) {
        this.totalFrames = totalFrames;
    }
    public void sender(Scanner sc) {
        for (int frameNo = 1; frameNo <= totalFrames; frameNo++) {
            boolean ackReceived = false;
            while (!ackReceived) {
                System.out.println("Sender : Sending frame " + frameNo);
                delay(500);
                System.out.print("Was Ack for frame " + frameNo + " received ? (y/n) : ");
                String response = sc.next().trim();
                if (response.equalsIgnoreCase("y")) {
                    System.out.println("Sender : Ack received for frame " + frameNo);
                    ackReceived = true;
                } else if (response.equalsIgnoreCase("n")) {
                    System.out.println("Sender : Ack lost for frame " + frameNo + " waiting for timeout...");
                    try { Thread.sleep(TIMEOUT); } catch (InterruptedException e) {}
                    System.out.println("Time Out! Resending frame " + frameNo);
                } else System.out.println("Invalid input. Enter y or n.");
            }
        }
        System.out.println("All frames sent and acknowledged successfully.");
    }
    private void delay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of frames to send : ");
        int totalFrames = sc.nextInt();
        new StopAndWaitProtocol(totalFrames).sender(sc);
        sc.close();
    }
}