import java.io.*;
import java.util.*;

public class LeastCommonAncestor {
	public static int[] euler_tour, tin, depth;
	public static int timer, size, n;
	public static ArrayList<Integer>[] neighbors;
	public static final int MAX_N = (int) 2e5 * 2; // For safety
	public static int[] t = new int[MAX_N];

	public static void update(int p, int value) {
		for (t[p += size] = value; p > 1; p >>= 1)
			t[p >> 1] = mn_tin(t[p], t[p ^ 1]);
	}

	public static int query(int l, int r) {
		int res = -1;
		for (l += size, r += size; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) != 0)
				res = mn_tin(res, t[l++]);
			if ((r & 1) != 0)
				res = mn_tin(res, t[--r]);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Kattio io = new Kattio();
		n = io.nextInt();
		int q = io.nextInt();
		neighbors = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			neighbors[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			int a = io.nextInt(), b = io.nextInt();
			neighbors[a].add(b);
			neighbors[b].add(a);
		}

		euler_tour = new int[2 * n - 1];
		tin = new int[n + 1];
		depth = new int[n + 1];
		dfs(1, 0, 0); // start at node 1 with depth 0

		size = 1;
		while (size < 2 * n - 1)
			size <<= 1;
		Arrays.fill(t, -1);
		for (int i = 0; i < 2 * n - 1; i++) {
			update(i, euler_tour[i]);
		}

		for (int i = 0; i < q; i++) {
			int a = io.nextInt();
			int b = io.nextInt();
			int lca = lca(a, b);
			int dist = depth[a] + depth[b] - 2 * depth[lca];
			io.println(dist);
		}
		io.close();
	}

	public static void dfs(int node, int parent, int d) {
		tin[node] = timer;
		depth[node] = d;
		euler_tour[timer++] = node;
		for (int next : neighbors[node]) {
			if (next != parent) {
				dfs(next, node, d + 1);
				euler_tour[timer++] = node;
			}
		}
	}

	public static int lca(int a, int b) {
		if (tin[a] > tin[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		return query(tin[a], tin[b] + 1);
	}

	public static int mn_tin(int x, int y) {
		if (x == -1)
			return y;
		if (y == -1)
			return x;
		return (tin[x] < tin[y] ? x : y);
	}

	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;

		public Kattio() {
			this(System.in, System.out);
		}

		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}

		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) {
			}
			return null;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}
