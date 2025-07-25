package USACOGuide;

import java.io.*;
import java.util.*;

public class Moocast {
	static class Edge {
		public int a;
		public int b;
		public int dist;

		public Edge(int a, int b, int dist) {
			this.a = a;
			this.b = b;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		int n = Integer.parseInt(br.readLine());

		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer cow = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(cow.nextToken());
			y[i] = Integer.parseInt(cow.nextToken());
		}
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				edges.add(new Edge(i, j, dx * dx + dy * dy));
			}
		}

		edges.sort(Comparator.comparingInt(e -> e.dist));

		int lastDist = 0;
		int compNum = n;
		DSU dsu = new DSU(n);
		for (Edge e : edges) {
			if (dsu.link(e.a, e.b)) {
				lastDist = e.dist;
				if (--compNum == 1) {
					break;
				}
			}
		}

		PrintWriter out = new PrintWriter("moocast.out");
		out.println(lastDist);
		out.close();
		br.close();
	}
}

class DSU {
	private final int[] parents;
	private final int[] sizes;

	public DSU(int size) {
		parents = new int[size];
		sizes = new int[size];
		for (int i = 0; i < size; i++) {
			parents[i] = i;
			sizes[i] = 1;
		}
	}

	public int getTop(int n) {
		return parents[n] == n ? n : (parents[n] = getTop(parents[n]));
	}

	public boolean link(int e1, int e2) {
		e1 = getTop(e1);
		e2 = getTop(e2);
		if (e1 == e2) {
			return false;
		}
		if (sizes[e2] > sizes[e1]) {
			return link(e2, e1);
		}
		parents[e2] = e1;
		sizes[e1] += sizes[e2];
		return true;
	}
}
// EndCodeSnip