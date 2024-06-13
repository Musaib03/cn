import java.util.Scanner;

public class Belllinkstate{
    static final int INF = Integer.MAX_VALUE / 2;
    int[] dist;

    public Belllinkstate(int n) {
        dist = new int[n];
    }

    public void evaluate(int[][] graph, int src, int n) {
        for (int i = 0; i < n; i++) dist[i] = INF;
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != INF && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != INF && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("The graph contains a negative edge cycle.");
                    return;
                }
            }
        }

        System.out.println("\nRouting table for Router: " + (src + 1));
        System.out.println("Dest\tDistance");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + dist[i]);
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int n = s.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = s.nextInt();
                if (i == j) {
                    graph[i][j] = 0;
                } else if (graph[i][j] == 0) {
                    graph[i][j] = INF;
                }
            }
        }

        Belllinkstate bf = new Belllinkstate(n);
        for (int i = 0; i < n; i++) {
            bf.evaluate(graph, i, n);
        }
        s.close();
    }
}
