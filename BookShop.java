import java.util.*;
public class BookShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
		int x = scan.nextInt();

		int[] costs = new int[n];
		for (int i = 0; i < n; i++) { 
            costs[i] = scan.nextInt(); 
        }

		int[] pages = new int[n];
		for (int i = 0; i < n; i++) { 
            pages[i] = scan.nextInt();
        }

		int[][] dp = new int[n + 5][x + 5];
		for (int book = 0; book < n; book++) {
			for (int money = 0; money <= x; money++) {
				dp[book + 1][money] = dp[book][money];
				int prev = money - costs[book];
				if (prev >= 0) {
					int newState = dp[book][prev] + pages[book];
					dp[book + 1][money] = Integer.max(dp[book + 1][money], newState);
				}
			}
		}

		System.out.println(dp[n][x]);
        scan.close();
    }
}