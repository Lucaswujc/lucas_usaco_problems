package USACOGold;
import java.io.*;
import java.util.*;

public class RadioContact {
	public static int[][] fj;
	public static int[][] bessie;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("radio.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("radio.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		fj = new int[N + 1][2];
		bessie = new int[M + 1][2]; 

		st = new StringTokenizer(br.readLine());
		fj[0][0] = Integer.parseInt(st.nextToken());
		fj[0][1] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		bessie[0][0] = Integer.parseInt(st.nextToken());
		bessie[0][1] = Integer.parseInt(st.nextToken());

		String path = br.readLine();
		fill(fj, path);
        path = br.readLine();
		fill(bessie, path);

		int[][] dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + dist(fj[i], bessie[0]);
		}

		for (int j = 1; j <= M; j++) {
			dp[0][j] = dp[0][j - 1] + dist(fj[0], bessie[j]);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int curr = dist(fj[i], bessie[j]);
				int bothMove = dp[i - 1][j - 1] + curr;
				int fjMove = dp[i - 1][j] + curr;
				int bMove = dp[i][j - 1] + curr;
				dp[i][j] = Math.min(Math.min(bothMove, fjMove), bMove);
			}
		}

		pw.println(dp[N][M]);
		pw.close();
		br.close();
	}

	public static void fill(int[][] location, String s) {
		for (int i = 1; i <= s.length(); i++) {
			location[i][0] = location[i - 1][0];
			location[i][1] = location[i - 1][1];
			char c = s.charAt(i - 1);
			if (c == 'N') { location[i][1]++; }
			if (c == 'E') { location[i][0]++; }
			if (c == 'S') { location[i][1]--; }
			if (c == 'W') { location[i][0]--; }
		}
	}

	public static int dist(int[] a, int[] b) {
		return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
	}
}