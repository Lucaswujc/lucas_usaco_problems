package USACOGold;

import java.util.*;

public class HoofPaperScissors {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		int[] moves = new int[n + 1];
		String s = scan.next();
		for (int x = 1; x <= n; x++) {
			char c = s.charAt(0);
			if (c == 'H') {
				moves[x] = 0;
			} else if (c == 'P') {
				moves[x] = 1;
			} else {
				moves[x] = 2;
			}
		}

		int[][][] dp = new int[n + 1][k + 2][3];
		int max = 0;

		for (int c = 1; c <= n; c++) {
			
			for (int j = 1; j < k + 2; j++) {
				int add1 = 0;
				if (moves[c] == 0) { 
					add1 = 1; 
				}
				dp[c][j][0] = Math.max(dp[c - 1][n][0] + add1,Math.max(dp[c - 1][j - 1][1] + add1, dp[c - 1][j - 1][2] + add1));

				int add2 = 0;
				if (moves[c] == 1) { 
					add2 = 1; 
				}
				dp[c][j][1] = Math.max(dp[c - 1][n][1] + add2, Math.max(dp[c - 1][j - 1][0] + add2, dp[c - 1][j - 1][2] + add2));

				int add3 = 0;
				if (moves[c] == 2) { 
					add3 = 1; 
				}
				dp[c][j][2] = Math.max(dp[c - 1][n][2] + add3, Math.max(dp[c - 1][j - 1][0] + add3, dp[c - 1][j - 1][1] + add3));

				if (c == j) { 
					max = Math.max(max, Math.max(dp[n][j][0], Math.max(dp[n][j][1], dp[n][j][2])));
				}
			}
		}
		System.out.println(max);
		scan.close();
    }
}
