package USACOGold;
import java.io.*;
import java.util.*;

public class CoinCombinationsI {
	static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int M = 1000000007;
		st = new StringTokenizer(r.readLine());
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) { coins[i] = Integer.parseInt(st.nextToken()); }
		Arrays.sort(coins);

		int[] dp = new int[x + 1];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 0;
			for (int j = 0; j < n; j++) {
				if (coins[j] > i) { break; }
				dp[i] += dp[i - coins[j]];
				if (dp[i] > M) dp[i] -= M;
			}
		}

		pw.println(dp[x]);
		pw.close();
	}
}