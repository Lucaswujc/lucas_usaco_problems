import java.io.*;
import java.util.*;

public class HamiltonPaths {
    static final int MOD = (int)1e9 + 7;
    static List<Integer>[] adj = new ArrayList[21];
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[b].add(a); 
        }

        dp = new long[1 << n][n + 1];
        dp[1][1] = 1; 

        for (int s = 2; s < (1 << n); s++) {
            if (((s & (1 << (n - 1))) != 0) && s != ((1 << n) - 1)) {
                continue;
            }

            for (int d = 1; d <= n; d++) {
                if ((s & (1 << (d - 1))) == 0) {
                    continue;
                }
                for (int v : adj[d]) {
                    if ((s & (1 << (v - 1))) != 0) {
                        dp[s][d] += dp[s ^ (1 << (d - 1))][v];
                        dp[s][d] %= MOD;
                    }
                }
            }
        }

        System.out.println(dp[(1 << n) - 1][n]);
    }
}
