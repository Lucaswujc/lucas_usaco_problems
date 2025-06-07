import java.io.*;
import java.util.*;

public class CowAtLarge {
	static int n, K, A = 0;
	static List<Integer>[] adj;
	static int[] depth, leaf, inDeg, parent;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("atlarge.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adj = new List[n];
		depth = new int[n];
		leaf = new int[n];
		inDeg = new int[n];
		parent = new int[n];

		Arrays.fill(depth, -1);
		Arrays.fill(leaf, Integer.MAX_VALUE);

		for (int i = 0; i < n; i++) { adj[i] = new ArrayList<>(); }

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			adj[a].add(b);
			adj[b].add(a);

			inDeg[a]++;
			inDeg[b]++;
		}

		depth[K - 1] = 0;
		dfs(K - 1);

		for (int i = 0; i < n; i++) {
			if (inDeg[i] == 1) { bfs(i); }
		}

		for (int i = 0; i < n; i++) {
			if (i != K - 1) {
				if (depth[parent[i]] < leaf[parent[i]] && depth[i] >= leaf[i]) { A++; }
			}
		}

		pw.println(A);
		br.close();
		pw.close();
	}

	public static void dfs(int n) {
		for (Integer c : adj[n]) {
			if (depth[c] != -1) continue;

			depth[c] = depth[n] + 1;
			parent[c] = n;
			dfs(c);
		}
	}

	public static void bfs(int l) {
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(l, 0));

		while (!q.isEmpty()) {
			Edge curr = q.poll();

			leaf[curr.n] = Math.min(leaf[curr.n], curr.d);

			for (Integer n : adj[curr.n]) {
				if (leaf[n] < curr.d + 1) continue;

				q.add(new Edge(n, curr.d + 1));
			}
		}
	}

	private static class Edge {
		int n, d;
		public Edge(int a, int b) {
			n = a;
			d = b;
		}
	}
}