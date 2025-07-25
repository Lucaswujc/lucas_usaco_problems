package USACOGold;

import java.io.*;
import java.util.*;

public class CheckSum {
    static class DSU {
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
            if (xRoot == yRoot) {
                return false;
            }

            if (sizes[xRoot] < sizes[yRoot]) {
                return unite(yRoot, xRoot);
            }
            parents[yRoot] = xRoot;
            sizes[xRoot] += sizes[yRoot];
            return true;
        }

        /** @return whether x and y are in the same connected component */
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    static class Edge implements Comparable<Edge> {
        int weight;
        int u, v;

        Edge(int weight, int u, int v) {
            this.weight = weight;
            this.u = u;
            this.v = v;
        }

        public int compareTo(Edge other) {
            return Integer.compare(other.weight, this.weight); // Descending order
        }
    }

    public static int kruskal(int N, List<Edge> edges) {
        Collections.sort(edges);
        DSU dsu = new DSU(N);
        int ans = 0;
        for (Edge e : edges) {
            if (dsu.unite(e.u, e.v)) {
                ans += e.weight;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        while (T > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] A = new int[n][n];
            int[][] B = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    B[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            br.readLine(); // Skip row checksums
            br.readLine(); // Skip column checksums

            List<Edge> edges = new ArrayList<>();
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (A[i][j] == -1) {
                        edges.add(new Edge(B[i][j], i, n + j)); // distinguish row/col nodes
                        total += B[i][j];
                    }
                }
            }

            int maxSpanning = kruskal(2 * n, edges);
            out.println("Case #" + T + ": " + (total - maxSpanning));
            T--;
        }
        out.close();
    }
}
