import java.util.*;
public class DistanceVector{
    static final int INF = 9999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int N = Integer.parseInt(sc.nextLine().trim());
        String[] nodes = new String[N];
        Map<String,Integer> idx = new HashMap<>();
        System.out.println("Enter node names:");
        for (int i=0;i<N;i++){ nodes[i]=sc.nextLine().trim(); idx.put(nodes[i].toLowerCase(), i); }

        int[][] graph = new int[N][N];
        for (int i=0;i<N;i++) Arrays.fill(graph[i], INF);
        for (int i=0;i<N;i++) graph[i][i]=0;

        System.out.print("Enter number of edges: ");
        int E = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter edges (format: A B 5):");
        for (int e=0;e<E;e++){
            String[] p = sc.nextLine().trim().split("\\s+");
            if (p.length!=3) { System.out.println("Invalid line, skipping."); e--; continue; }
            Integer s=idx.get(p[0].toLowerCase()), d=idx.get(p[1].toLowerCase());
            if (s==null||d==null){ System.out.println("Unknown node, try again."); e--; continue; }
            int w; try{ w=Integer.parseInt(p[2]); }catch(Exception ex){ System.out.println("Bad weight."); e--; continue; }
            graph[s][d]=w; // directed
        }

        int[][] dist = new int[N][N], nextHop = new int[N][N];
        for (int i=0;i<N;i++) for (int j=0;j<N;j++){ dist[i][j]=graph[i][j]; nextHop[i][j]=(graph[i][j]!=INF && i!=j)? j : -1; }

        boolean changed;
        do {
            changed=false;
            for (int i=0;i<N;i++) for (int j=0;j<N;j++) for (int k=0;k<N;k++){
                if (dist[i][k]==INF||dist[k][j]==INF) continue;
                if (dist[i][k]+dist[k][j] < dist[i][j]) { dist[i][j]=dist[i][k]+dist[k][j]; nextHop[i][j]=nextHop[i][k]; changed=true; }
            }
            
        } while (changed);

        for (int i=0;i<N;i++){
            System.out.println("\nRouting table for " + nodes[i]);
            System.out.println("Destination\tCost\tNext Hop");
            for (int j=0;j<N;j++){
                String nh = nextHop[i][j]==-1? "-" : nodes[nextHop[i][j]];
                System.out.println(nodes[j] + "\t\t" + (dist[i][j]==INF? "INF" : dist[i][j]) + "\t" + nh);
            }
        }

        while (true){
            System.out.print("\nEnter source node (or 'exit'): ");
            String sname = sc.nextLine().trim();
            if (sname.equalsIgnoreCase("exit")) break;
            System.out.print("Enter destination node: ");
            String dname = sc.nextLine().trim();
            Integer s = idx.get(sname.toLowerCase()), d = idx.get(dname.toLowerCase());
            if (s==null||d==null){ System.out.println("Invalid node name!"); continue; }
            if (dist[s][d]==INF) { System.out.println("No path from " + sname + " to " + dname); continue; }
            System.out.print("Shortest path: " + nodes[s]);
            int cur = s;
            while (cur!=d){ cur = nextHop[cur][d]; System.out.print(" -> " + nodes[cur]); }
            System.out.println("\nDistance: " + dist[s][d]);
        }
        sc.close();
    }
}
