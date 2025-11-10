import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class LeakyBucket
 {
    private int capacity;
    private int leakRate;
    private Queue<Integer> buffer;
    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.buffer = new LinkedList<>();
    }
    public void addPackets(int packets) {
        System.out.println("Incoming packets: " + packets);
        System.out.println("Buffer size before adding: " + buffer.size());
        int dropped = 0;
        int added = 0;
        for (int i = 0; i < packets; i++) {
            if (buffer.size() < capacity) {
                buffer.add(1);
                added++;
                System.out.println("Packet added to bucket. Current size: " + buffer.size());
            } else {
                dropped++;
            }
        }
        System.out.println("Packets added: " + added);
        if (dropped > 0) {
            System.out.println("Bucket overflow! Packets dropped: " + dropped);
        }
    }
    public void leak() {
        int leaked = 0;
        for (int i = 0; i < leakRate; i++) {
            if (!buffer.isEmpty()) {
                buffer.poll();
                leaked++;
            }
        }
        System.out.println("Packets processed (leaked): " + leaked);
        System.out.println("Buffer size after leaking: " + buffer.size());
        System.out.println("---------------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket size (capacity): ");
        int capacity = sc.nextInt();
        System.out.print("Enter leak rate (packets processed per unit time): ");
        int leakRate = sc.nextInt();
        LeakyBucket leakyBucket = new LeakyBucket(capacity, leakRate);
        System.out.println("\nStarting simulation. Enter -1 to stop.");
        while (true) {
            System.out.print("Enter incoming packets: ");
            int packets = sc.nextInt();
            if (packets == -1) {
                System.out.println("Simulation ended.");
                break;
            }
            leakyBucket.addPackets(packets);
            leakyBucket.leak();
        }
        sc.close();
    }
}