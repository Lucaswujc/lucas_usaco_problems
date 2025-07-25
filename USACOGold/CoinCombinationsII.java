package USACOGold;
import java.io.*;
import java.util.*;

public class CoinCombinationsII {
    static int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int x = scan.nextInt();
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) { 
            coins[i] = scan.nextInt();
        }

		int[] dp = new int[x + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= x; j++) {
				if (j-coins[i] >= 0) { 
                    dp[j] = (dp[j] + dp[j-coins[i]]) % mod;
                }
			}
		}

		System.out.println(dp[x]);
        scan.close();
	}
}