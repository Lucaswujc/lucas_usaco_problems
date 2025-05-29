package USACOGold;
import java.io.*;
import java.util.*;
public class GuardMark {
    static class Cow {
		public int height;
		public int weight;
		public int strength;

		public Cow(int height, int weight, int strength) {
			this.height = height;
			this.weight = weight;
			this.strength = strength;
		}
	}


    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new FileWriter("radio.out"));
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cows[i] = new Cow(h1, w, s);
		}

		int[][] dp = new int[1 << n][2];

		dp[0][0] = 0;
		dp[0][1] = Integer.MAX_VALUE;

		for (int i = 1; i < (1 << n); i++) {
			dp[i][0] = 0;
			dp[i][1] = -1;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) == 0) { continue; }
				dp[i][0] += cows[j].height;
				int prev = i ^ (1 << j);
				dp[i][1] = Math.max(
				    dp[i][1], Math.min(dp[prev][1] - cows[j].weight, cows[j].strength));
			}
		}

		int ans = -1;
		for (int i = 1; i < (1 << n); i++) {
			if (dp[i][0] >= h) {
				ans = Math.max(dp[i][1], ans);
			}
		}
        if (ans == -1) {
			out.println("Mark is too tall");
		} else {
			out.println(ans);
		}
        br.close();
        out.close();
    }
}