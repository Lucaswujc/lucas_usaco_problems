import java.util.*;
import java.io.*;

public class Arena {
    static final int N = 505;
    static final int MOD = 998244353;

    static int[][] c = new int[N][N]; // Binomial coefficients
    static int[][] dp = new int[N][N]; // DP table

    static int add(int x, int y) {
        x += y;
        if (x >= MOD)
            x -= MOD;
        return x;
    }

    static int mul(int x, int y) {
        return (int) ((long) x * y % MOD);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // Compute binomial coefficients
        for (int i = 0; i <= n; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = add(c[i - 1][j], c[i - 1][j - 1]);
            }
        }

        dp[n][0] = 1;

        for (int i = n; i > 1; i--) {
            for (int j = 0; j < x; j++) {
                if (dp[i][j] == 0)
                    continue;
                int pw = 1;
                int nj = Math.min(x, j + i - 1);
                for (int k = i; k >= 0; k--) {
                    int val = mul(dp[i][j], mul(c[i][k], pw));
                    dp[k][nj] = add(dp[k][nj], val);
                    pw = mul(pw, nj - j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= x; i++) {
            ans = add(ans, dp[0][i]);
        }

        System.out.println(ans);
    }
}
