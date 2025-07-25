package USACOGuide;

import java.io.*;
import java.util.*;

public class GameRoutes {
	public static final int MOD = (int) 1e9 + 7;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(read.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] dp = new int[n];
		dp[0] = 1; // Starting point
		List<Integer>[] graph = new ArrayList[n];
		List<Integer>[] backwards = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
			backwards[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
			backwards[b].add(a);
		}

		int[] inDegree = new int[n];
		for (List<Integer> nodes : graph) {
			for (int node : nodes) {
				inDegree[node]++;
			}
		}

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		List<Integer> topSort = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			topSort.add(curr);
			for (int next : graph[curr]) {
				inDegree[next]--;
				if (inDegree[next] == 0) {
					queue.add(next);
				}
			}
			for (int prev : backwards[curr]) {
				dp[curr] = (dp[curr] + dp[prev]) % MOD;
			}
		}
		System.out.println(dp[n - 1]);
	}
}