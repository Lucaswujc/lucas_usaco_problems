
import java.util.*;
public class MinimizingCoins {
	public static int max = (int)10e6 + 2;
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int x = scan.nextInt();

		int[] coins = new int[n];
		for (int i = 0; i < n; i++) { 
            coins[i] = scan.nextInt();
        }

		int[] dp = new int[x + 1];
		for (int i = 0; i <= x; i++) { 
            dp[i] = max; 
        }
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int sum = coins[i - 1]; sum <= x; sum++) {
				dp[sum] = Integer.min(dp[sum], dp[sum - coins[i - 1]] + 1);
			}
		}
		if (dp[x] == max) {
			System.out.println(-1);
			System.exit(0);
		}
        else{
		    System.out.println(dp[x]);
        }
        scan.close();
	}
}