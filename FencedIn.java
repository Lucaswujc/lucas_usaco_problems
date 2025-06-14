import java.io.*;
import java.util.*;

public class FencedIn {
    static class Edge {
		int c1, c2;
		int cost;

		public Edge(int cost, int c1, int c2) {
			this.c1 = c1;
			this.c2 = c2;
			this.cost = cost;
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("fencedin.out"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] x = new int[n + 2];
        int[] y = new int[m + 2];

        for (int i = 1; i <= n; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= m; i++) {
            y[i] = Integer.parseInt(br.readLine());
        }

        x[n + 1] = A;
        y[m + 1] = B;

        Arrays.sort(x);
        Arrays.sort(y);
        n += 2;
        m += 2;
        List<Edge> edges = new ArrayList<>();

        int curSect = 0;
        for (int row = 1; row < m; row++) {
            for (int i = 0; i < n - 2; i++) {
                edges.add(new Edge(y[row] - y[row - 1], curSect, curSect + 1));
                curSect++;
            }
            curSect++;
        }

        curSect = n - 1;
        for (int col = 1; col < n; col++) {
            int init = curSect;
            for (int i = 0; i < m - 2; i++) {
                edges.add(new Edge(x[col] - x[col - 1], curSect - (n - 1), curSect));
                curSect += (n - 1);
            }
            curSect = init + 1;
        }

        Collections.sort(edges, Comparator.comparingInt(e -> e.cost));

        DSU dsu = new DSU((n - 1) * (m - 1));
        long ans = 0;
        for (Edge e : edges) {
            if (dsu.unite(e.c1, e.c2)) {
                ans += e.cost;
            }
        }

        pw.println(ans);
        pw.close();
        br.close();
    }
}
class DSU {
    int[] parents;
    int[] sizes;

    public DSU(int size) {
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
