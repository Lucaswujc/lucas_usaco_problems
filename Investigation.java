import java.io.*;
import java.util.*;

public class Investigation {
	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<List<Pair>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj.get(a).add(new Pair(cost, b));
		}

		long[] dist = new long[n];
		long[] num = new long[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Long.MAX_VALUE;
			num[i] = 1;
		}
		int[] max = new int[n];
		int[] minf = new int[n];

		Queue<Pair> toVisit = new PriorityQueue<>();
		dist[0] = 0;
		toVisit.add(new Pair(0, 0));
		while (!toVisit.isEmpty()) {
			Pair node = toVisit.remove();
			long nodeDist = node.f;
			int idx = node.s;
			if (dist[idx] != nodeDist) {
                continue;
            }
			for (Pair p : adj.get(idx)) {
				long cost = p.f;
				int child = p.s;
				num[child] %= MOD;
				if (cost + dist[idx] < dist[child]) {
					dist[child] = cost + dist[idx];
					num[child] = num[idx];
					max[child] = max[idx] + 1;
					minf[child] = minf[idx] + 1;
					toVisit.add(new Pair(dist[child], child));
				} else if (cost + dist[idx] == dist[child]) {
					num[child] += num[idx];
					max[child] = Math.max(max[child], max[idx] + 1);
					minf[child] = Math.min(minf[child], minf[idx] + 1);
				}
			}
		}

		num[n - 1] %= MOD;
		out.println(String.format("%d %d %d %d", dist[n - 1], num[n - 1], minf[n - 1],
		                          max[n - 1]));
		out.close();
	}

	private static class Pair implements Comparable<Pair> {
		long f;
		int s;

		public Pair(long x, int y) {
			f = x;
			s = y;
		}

		@Override
		public int compareTo(Pair o) {
			int dx = Long.compare(f, o.f);
			if (dx == 0) return s - o.s;
			else return dx;
		}
	}
}