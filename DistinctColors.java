import java.io.*;
import java.util.*;

public class DistinctColors {
    static final int MAXN = 200_001;
    static int[] tour = new int[MAXN];
    static int[] color = new int[MAXN];
    static int[] ans = new int[MAXN];
    static int[] lend = new int[MAXN];
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new ArrayList[MAXN];
    static int idx = 0;

    static class BIT {
        int[] bit;
        int n;

        BIT(int n) {
            this.n = n + 1;
            bit = new int[this.n];
        }

        int sum(int r) {
            r++;
            int ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        void update(int idx, int v) {
            idx++;
            while (idx < n) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }

    static void dfs(int u, int par) {
        lend[u] = idx;
        for (int v : adj[u]) {
            if (v == par)
                continue;
            dfs(v, u);
        }
        tour[idx++] = u;
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio();

        int N = io.nextInt();
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            color[i] = io.nextInt();
        }
        for (int i = 0; i < N - 1; i++) {
            int u = io.nextInt();
            int v = io.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1, 0);

        BIT bit = new BIT(N);
        Map<Integer, Integer> last = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int c = color[tour[i]];
            if (last.containsKey(c)) {
                bit.update(last.get(c), -1);
            }
            last.put(c, i);
            bit.update(i, 1);
            ans[tour[i]] = bit.sum(i) - bit.sum(lend[tour[i]] - 1);
        }

        for (int i = 1; i <= N; i++) {
            io.print(ans[i] + " ");
        }
        io.println();
        io.close();
    }

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
}