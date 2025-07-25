package USACOGold;
import java.io.*;
import java.util.*;

public class U248 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("248.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("248.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] board = new int[n];

		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			board[i] = Integer.parseInt(st.nextToken());
			dp[i + 1][i + 1] = board[i];
		}

		int max = 0;
		for (int i = n - 1; i >= 1; i--) {
			for (int j = i + 1; j <= n; j++) {
				for (int k = i; k < j; k++) {
					if (dp[i][k] == dp[k + 1][j]) {
						dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
					}
				}

				max = Math.max(max, dp[i][j]);
			}
		}

		pw.println(max);
		br.close();
		pw.close();
	}
}