package USACOGold;

import java.util.*;

public class Snakes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();

		int[][] dp = new int[n + 1][k + 1];
		int[] snakes = new int[n + 1];
		int total = 0; 
		int max = -1;
		for (int i = 1; i <= n; i++) {
			snakes[i] = scan.nextInt();
			max = Math.max(max, snakes[i]);
			dp[i][0] = max * i;

			for (int j = 1; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				int size = snakes[i];
				for (int prev = i - 1; prev >= 0; prev--) {
					dp[i][j] = Math.min(dp[i][j], dp[prev][j - 1] + size * (i - prev));
					size = Integer.max(size, snakes[prev]);
				}
			}
			total += snakes[i];
		}

		int ans = dp[n][k] - total;
		System.out.println(ans);
		scan.close();
	}
}