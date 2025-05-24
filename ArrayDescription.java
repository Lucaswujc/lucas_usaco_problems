import java.util.*;
public class ArrayDescription {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] dp = new int[n][m+1];
        int first = scan.nextInt();
		if (first == 0) {
			Arrays.fill(dp[0], 1);
		} else {
			dp[0][first] = 1;
		}

		for (int i = 1; i < n; i++) {
			int a = scan.nextInt();
			if (a == 0) {
				for (int j = 1; j <= m; j++) {
					int[] neighbors = new int[] {j - 1, j, j + 1};
					for (int k : neighbors) {
						if (1 <= k && k <= m) {
							dp[i][j] += dp[i - 1][k];
							dp[i][j] %= 1000000007;
						}
					}
				}
			} else {
				int[] neighbors = new int[] {a - 1, a, a + 1};
				for (int k : neighbors) {
					if (1 <= k && k <= m) {
						dp[i][a] += dp[i - 1][k];
						dp[i][a] %= 1000000007;
					}
				}
			}
		}

		int ans = 0;
		for (int j = 1; j <= m; j++) {
			ans += dp[n - 1][j];
			ans %= 1000000007;
		}
		System.out.println(ans);
        scan.close();
    }
}