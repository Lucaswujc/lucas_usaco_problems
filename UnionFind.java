import java.io.*;
import java.util.*;

public class UnionFind {
	public static void main(String[] args) {
		Kattio io = new Kattio();
		int size = io.nextInt();
		int queryNum = io.nextInt();

		DisjointSets dsu = new DisjointSets(size);
		for (int i = 0; i < queryNum; i++) {
			int type = io.nextInt();
			int u = io.nextInt();
			int v = io.nextInt();
			if (type == 0) {
				dsu.unite(u, v);
			} else {
				if (dsu.connected(u, v)) {
					io.println(1);
				} else {
					io.println(0);
				}
			}
		}
		io.close();
	}

	//BeginCodeSnip{Kattio}
	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
		// standard input
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		// USACO-style file input
		public Kattio(String problemName) throws IOException {
			super(problemName + ".out");
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
		// returns null if no more input
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) { }
			return null;
		}
		public int nextInt() { return Integer.parseInt(next()); }
		public double nextDouble() { return Double.parseDouble(next()); }
		public long nextLong() { return Long.parseLong(next()); }
	}
	//EndCodeSnip
}

// BeginCodeSnip{DSU}
class DisjointSets {
	int[] parents;  // 0-indexed
	int[] sizes;

	public DisjointSets(int size) {
		parents = new int[size];
		sizes = new int[size];
		for (int i = 0; i < size; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
	}

	/** @return the "representative" node in x's component */
	public int find(int x) {
		return parents[x] == x ? x : (parents[x] = find(parents[x]));
	}

	/** @return whether the merge changed connectivity */
	public boolean unite(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		if (xRoot == yRoot) { return false; }

		if (sizes[xRoot] < sizes[yRoot]) { return unite(yRoot, xRoot); }
		parents[yRoot] = xRoot;
		sizes[xRoot] += sizes[yRoot];
		return true;
	}

	/** @return whether x and y are in the same connected component */
	public boolean connected(int x, int y) { return find(x) == find(y); }
}
// EndCodeSnip