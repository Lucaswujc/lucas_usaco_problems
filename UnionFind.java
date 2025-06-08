
import java.util.*;

public class UnionFind {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		int queryNum = scan.nextInt();

		DisjointSets dsu = new DisjointSets(size);
		for (int i = 0; i < queryNum; i++) {
			int type = scan.nextInt();
			int u = scan.nextInt();
			int v = scan.nextInt();
			if (type == 0) {
				dsu.unite(u, v);
			} else {
				if (dsu.connected(u, v)) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			}
		}
		scan.close();
	}
}

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