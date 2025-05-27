import java.io.*;
import java.util.*;

public class ThreeSum {
	static final int MAX_VAL = 1000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("threesum.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("threesum.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] a = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { 
            a[i] = Integer.parseInt(st.nextToken()); 
        }
		long[][] dp = new long[N + 1][N + 1];
		int[] cnt = new int[2 * MAX_VAL + 1];

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) { cnt[a[j] + MAX_VAL] = 0; }
			for (int j = i + 1; j < N; j++) {
				int k = -a[i] - a[j];
				if (k >= -MAX_VAL && k <= MAX_VAL) {
					dp[i + 1][j + 1] += cnt[k + MAX_VAL];
				}
				cnt[a[j] + MAX_VAL]++;
			}
		}

		for (int i = N; i >= 1; i--) {
			for (int j = i + 1; j <= N; j++) {
				dp[i][j] += dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
			}
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a1 = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pw.println(dp[a1][b]);
		}
		pw.close();
	}
}