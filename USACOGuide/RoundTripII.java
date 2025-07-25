package USACOGuide;

import java.io.*;
import java.util.*;

public class RoundTripII {
	private static List<Integer>[] graph;
	private static boolean[] visited, onStack;
	private static List<Integer> cycle = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(read.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
		}

		visited = new boolean[n];
		onStack = new boolean[n];
		for (int i = 0; cycle.isEmpty() && i < n; i++) {
			dfs(i);
		}

		if (cycle.isEmpty()) {
			System.out.println("IMPOSSIBLE");
		} else {
			Collections.reverse(cycle);
			System.out.println(cycle.size() + 1);
			for (int node : cycle) {
				System.out.print((node + 1) + " ");
			}
			System.out.println(cycle.get(0) + 1);
		}
	}

	private static boolean dfs(int node) {
		visited[node] = onStack[node] = true;
		for (int next : graph[node]) {
			if (onStack[next]) {
				cycle.add(node);
				onStack[node] = onStack[next] = false;
				return true;
			} else if (!visited[next]) {
				if (dfs(next)) {
					if (onStack[node]) {
						cycle.add(node);
						onStack[node] = false;
						return true;
					} else {
						cycle.add(node);
						return false;
					}
				}

				if (!cycle.isEmpty()) {
					return false;
				}
			}
		}

		onStack[node] = false;
		return false;
	}
}