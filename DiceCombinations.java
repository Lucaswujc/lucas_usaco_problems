import java.io.*;
import java.util.*;

public class DiceCombinations {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		long dp[] = new long[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= 6; j++) {
				if (i - j >= 0) { dp[i] += dp[i - j]; }
			}
			dp[i] %= 1000000007;
		}

		System.out.println(dp[n]);
        scan.close();
	}
}