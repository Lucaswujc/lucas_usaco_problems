package USACOGuide;

import java.io.*;
import java.util.*;

public class ShortestRoutesII {
	// We can't use Long.MAX_VALUE because of overflow
	private static final long BIG = (long) 1e18;

	public static void main(String[] args) {
		Kattio io = new Kattio();

		int n = io.nextInt();
		int m = io.nextInt();
		int q = io.nextInt();

		long[][] minDist = new long[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(minDist[i], BIG);
		}
		for (int i = 0; i < m; i++) {
			int a = io.nextInt() - 1;
			int b = io.nextInt() - 1;
			int c = io.nextInt();
			if (c < minDist[a][b]) {
				minDist[a][b] = minDist[b][a] = c;
			}
		}

		// Floyd-Warshall algorithm
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					long newDist = minDist[i][k] + minDist[k][j];
					if (newDist < minDist[i][j]) {
						minDist[i][j] = minDist[j][i] = newDist;
					}
				}
			}
		}

		for (int i = 0; i < q; i++) {
			int a = io.nextInt() - 1;
			int b = io.nextInt() - 1;

			// Check if the two nodes are the same or can't reach each other
			if (a == b) {
				minDist[a][b] = 0;
			} else if (minDist[a][b] == BIG) {
				minDist[a][b] = -1;
			}

			io.println(minDist[a][b]);
		}

		io.close();
	}

	// BeginCodeSnip{Kattio}
	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;

		// standard input
		public Kattio() {
			this(System.in, System.out);
		}

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
			} catch (Exception e) {
			}
			return null;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}
	// EndCodeSnip
}