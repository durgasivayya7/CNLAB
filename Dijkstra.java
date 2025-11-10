import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        char[] vertices = new char[n];
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter vertex " + (i + 1) + ": ");
            vertices[i] = sc.next().charAt(0);
            map.put(vertices[i], i);
        }

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            System.out.print("Enter edge (u v w): ");
            char u = sc.next().charAt(0), v = sc.next().charAt(0);
            int w = sc.nextInt();
            adj.get(map.get(u)).add(new int[]{map.get(v), w});
        }

        System.out.print("Enter source vertex: ");
        char srcChar = sc.next().charAt(0);
        System.out.print("Enter destination vertex: ");
        char destChar = sc.next().charAt(0);
        int src = map.get(srcChar), dest = map.get(destChar);

        int[] dist = new int[n], prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            for (int[] nb : adj.get(u)) {
                int v = nb[0], w = nb[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        if (dist[dest] == Integer.MAX_VALUE) {
            System.out.println("No path from " + srcChar + " to " + destChar);
        } else {
            System.out.print("Shortest path: ");
            List<Character> path = new ArrayList<>();
            for (int at = dest; at != -1; at = prev[at]) path.add(vertices[at]);
            Collections.reverse(path);
            path.forEach(c -> System.out.print(c + " "));
            System.out.println("\nTotal cost: " + dist[dest]);
        }
    }
}
