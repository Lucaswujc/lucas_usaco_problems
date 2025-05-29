package USACOGold;
import java.util.*;
public class GridPaths {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int  n = scan.nextInt();
        long[][] dp = new long[n][n];
		int[][] value = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = scan.next();
			for (int j = 0; j < n; j++) {
				if (s.charAt(j) == '.'){
                    value[i][j] = 1;
                }
                else{
                    value[i][j] = 0;
                }
			}
		}

		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (value[i][j] == 0) {
                    dp[i][j] = 0;
                }
				else {
					if (i > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
					if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
					dp[i][j] %= 1000000007;
				}
			}
		}
        System.out.println(dp[n-1][n-1]);
        scan.close();
    }
}