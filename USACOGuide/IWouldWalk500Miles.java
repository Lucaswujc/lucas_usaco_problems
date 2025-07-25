package USACOGuide;

import java.io.*;
import java.util.*;

public class IWouldWalk500Miles {
	static final long MOD = 2019201997L;
	static final long FACTOR1 = 2019201913L;
	static final long FACTOR2 = 2019201949L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("walk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		StringTokenizer initial = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(initial.nextToken());
		int k = Integer.parseInt(initial.nextToken());

		long[] MST = prim(n);
		// O(1) math implementation
		// long ans = 2019201997L - 48L * n - 84L * (k - 1L);
		// out.println(ans);
		Arrays.sort(MST);
		out.println(MST[n - k]);
		br.close();
		out.close();
	}

	static long[] prim(int N) {
		long[] dist = new long[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, MOD);
		for (int i = 0; i < N; i++) {
			int min = -1;
			for (int j = 0; j < N; j++) {
				if (!visited[j] && (min < 0 || dist[j] < dist[min])) {
					min = j;
				}
			}
			visited[min] = true;
			for (int j = 0; j < N; j++) {
				if (!visited[j]) {
					dist[j] = Math.min(dist[j], calcDist(Math.min(min, j), Math.max(min, j)));
				}
			}
		}
		return dist;
	}

	public static long calcDist(long a, long b) {
		a++;
		b++;
		return (a * FACTOR1 + b * FACTOR2) % MOD;
	}
}
