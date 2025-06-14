import java.io.*;
import java.util.*;
//example - kruskals
public class RoadReparation {
	static class Road {
		int c1, c2;
		int cost;

		public Road(int c1, int c2, int cost) {
			this.c1 = c1;
			this.c2 = c2;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer initial = new StringTokenizer(read.readLine());
		int cityNum = Integer.parseInt(initial.nextToken());
		int roadNum = Integer.parseInt(initial.nextToken());

		Road[] roads = new Road[roadNum];
		for (int r = 0; r < roadNum; r++) {
			StringTokenizer road = new StringTokenizer(read.readLine());
			roads[r] = new Road(Integer.parseInt(road.nextToken()) - 1,
			                    Integer.parseInt(road.nextToken()) - 1,
			                    Integer.parseInt(road.nextToken()));
		}
		Arrays.sort(roads, Comparator.comparingInt(r -> r.cost));
		DisjointSets cities = new DisjointSets(cityNum);
		long minCost = 0;
		int added = 0;
		for (Road r : roads) {
			int status = cities.unite(r.c1, r.c2) ? 1 : 0;
			minCost += status * r.cost;
			added += status;
		}

		if (added != cityNum - 1) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(minCost);
		}
	}
}

class DisjointSets {
	int[] parents;
	int[] sizes;

	public DisjointSets(int size) {
		parents = new int[size];
		sizes = new int[size];
		for (int i = 0; i < size; i++) {
			parents[i] = i;
			sizes[i] = -1;
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