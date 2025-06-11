import java.io.*;
import java.util.*;

public class Shortcut {
	static long[] farms;
	static List<Edge>[] adj;
	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		long[] dist = new long[N];
		farms = new long[N];
		adj = new List[N];
        st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			farms[i] = Long.parseLong(st.nextToken());
			dist[i] = Long.MAX_VALUE;
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Edge(a, b, c));
			adj[b].add(new Edge(b, a, c));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0, 0));
		List<Edge> fin = new ArrayList<>();
		while (!pq.isEmpty()) {
			Edge c = pq.poll();

			if (dist[c.t] <= c.e) { 
                continue; 
            }
			dist[c.t] = c.e;
			fin.add(c);

			for (Edge e : adj[c.t]) {
				int nw = c.e + e.e;

				if (dist[e.t] <= nw) { 
                    continue; 
                }

				pq.add(new Edge(c.t, e.t, nw));
			}
		}
		fin.remove(0);

		for (int i = 0; i < N; i++) { adj[i].clear(); }

		for (int i = 0; i < fin.size(); i++) {
			Edge e = fin.get(i);

			adj[e.f].add(new Edge(e.f, e.t, 0));
		}

		dfs(0);

		long res = 0L;
		for (int i = 1; i < farms.length; i++) {
			res = Math.max(res, (dist[i] - T) * farms[i]);
		}

		out.println(res);
		out.close();
        br.close();
	}

	public static long dfs(int t) {
		for (Edge e : adj[t]) {
			farms[t] += dfs(e.t);
		}
		return farms[t];
	}

	private static class Edge implements Comparable<Edge> {
		int f, t, e;
		public Edge(int f, int t, int e) {
			this.t = t;
			this.e = e;
			this.f = f;
		}

		@Override
		public int compareTo(Edge o) {
			if (o.e == e) { return f - o.f; }
			return e - o.e;
		}
	}
}