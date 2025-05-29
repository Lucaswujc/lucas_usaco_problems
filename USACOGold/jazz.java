package USACOGold;
import java.io.*;
import java.util.*;

public class jazz {
	public static final int MAXN = 500;
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		char[] inp = scan.next().toCharArray();

		int[][] dp = new int[MAXN][MAXN];
		for (int j = 0; j <= inp.length; j++) {
			for (int i = 0; i < inp.length - j; i++) {
				dp[i][i + j] = dp[i + 1][i + j] + 1;

				for (int k = i + 1; k <= i + j; k++) {
					if (inp[k] == inp[i]) { 
						dp[i][i + j] =
						    Math.min(dp[i][i + j], dp[i + 1][k - 1] + dp[k + 1][i + j]);
					}
				}
			}
		}
		System.out.println(dp[0][inp.length - 1]);
		scan.close();
	}
}