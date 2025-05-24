import java.util.*;
public class EditDistance {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
		char[] str1 = scan.next().toCharArray();
		char[] str2 = scan.next().toCharArray();
		int[][] dp = new int[str1.length + 1][str2.length + 1];
		for (int i = 0; i < dp.length; i++) { Arrays.fill(dp[i], Integer.MAX_VALUE); }
		dp[0][0] = 0;
		for (int i = 0; i <= str1.length; i++) {
			for (int j = 0; j <= str2.length; j++) {
				if (i != 0) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
				}
				if (j != 0) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
				}
				if (i != 0 && j != 0) {
					int newCost = dp[i - 1][j - 1] + ((str1[i - 1] == str2[j - 1]) ? 0 : 1);
					dp[i][j] = Math.min(dp[i][j], newCost);
				}
			}
		}
		System.out.println(dp[str1.length][str2.length]);
		scan.close();
        scan.close();
    }
}