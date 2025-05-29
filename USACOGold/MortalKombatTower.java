package USACOGold;
import java.io.*;
import java.util.*;

public class MortalKombatTower {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();

		while(t > 0){
			int n = scan.nextInt();
			int[] bosses = new int[n];
			for(int i = 0; i < n; i++){
				bosses[i] = scan.nextInt();
			}
			int[][] dp = new int[2][n + 1];
			for (int j = 0; j <= n; j++) {
				dp[0][j] = Integer.MAX_VALUE;
				dp[1][j] = Integer.MAX_VALUE;
			}
			dp[1][0] = 0;

			for (int j = 0; j < n; j++) {
				if (dp[1][j] < Integer.MAX_VALUE) {
					dp[0][j + 1] = Math.min(dp[0][j + 1], dp[1][j] + bosses[j]);
				}
				dp[1][j + 1] = Math.min(dp[1][j + 1], dp[0][j]);

				if (j < n - 1) {
					if (dp[1][j] < Integer.MAX_VALUE) {
						dp[0][j + 2] = Math.min(dp[0][j + 2],
								dp[1][j] + bosses[j] + bosses[j + 1]);
					}
					dp[1][j + 2] = Math.min(dp[1][j + 2], dp[0][j]);
				}
			}

			System.out.println(Math.min(dp[0][n], dp[1][n]));
			t--;
		}
		scan.close();
	}
}