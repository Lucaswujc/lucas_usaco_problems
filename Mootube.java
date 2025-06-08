import java.io.*;
import java.util.*;

public class Mootube {
    static class DSU {
        int[] e;
        void init(int n) {
            e = new int[n];
            Arrays.fill(e, -1);
        }

        int get(int x) {
            if (e[x] < 0) return x;
            return e[x] = get(e[x]);
        }

        boolean sameSet(int x, int y) {
            return get(x) == get(y);
        }

        int size(int x) {
            return -e[get(x)];
        }

        boolean unite(int x, int y) {
            x = get(x);
            y = get(y);
            if (x == y) return false;
            if (e[x] > e[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            e[x] += e[y];
            e[y] = x;
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int weight, u, v;

        Edge(int weight, int u, int v) {
            this.weight = weight;
            this.u = u;
            this.v = v;
        }

        public int compareTo(Edge other) {
            return Integer.compare(other.weight, this.weight); // descending
        }
    }

    static class Query implements Comparable<Query> {
        int index, v, k;

        Query(int index, int v, int k) {
            this.index = index;
            this.v = v;
            this.k = k;
        }

        public int compareTo(Query other) {
            return Integer.compare(other.k, this.k);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[n - 1];
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(w, u, v);
        }

        Query[] queries = new Query[q];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;
            queries[i] = new Query(i, v, k);
        }

        Arrays.sort(edges);   // descending by weight
        Arrays.sort(queries); // descending by k

        DSU dsu = new DSU();
        dsu.init(n);

        int[] res = new int[q];
        int idx = 0;
        for (Query query : queries) {
            while (idx < edges.length && edges[idx].weight >= query.k) {
                dsu.unite(edges[idx].u, edges[idx].v);
                idx++;
            }
            res[query.index] = dsu.size(query.v) - 1;
        }

        for (int r : res) {
            pw.println(r);
        }

        pw.close();
        br.close();
    }
}
