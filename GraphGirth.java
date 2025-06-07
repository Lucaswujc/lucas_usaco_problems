import java.util.*;

public class GraphGirth {
    static final int MAXN = 2510;
    static final int INF = 1_000_000_007;

    static int n, m;
    static List<Integer>[] adj = new ArrayList[MAXN];

    static int cycleLen(int start) {
        int ans = INF;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> bfs = new LinkedList<>();

        dist[start] = 0;
        bfs.offer(start);

        while (!bfs.isEmpty()) {
            int node = bfs.poll();

            for (int neighbor : adj[node]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    bfs.offer(neighbor);
                } else if (dist[neighbor] >= dist[node]) {
                    ans = Math.min(ans, 1 + dist[neighbor] + dist[node]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int res = INF;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, cycleLen(i));
        }

        System.out.println(res == INF ? -1 : res);
    }
}
