import java.util.*;
import java.io.*;

public class RangeUpdateQueries {
    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();
        int q = io.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = io.nextInt();
        }

        SegmentTree seg = new SegmentTree(n + 1);
        for (int i = 1; i <= n; i++) {
            seg.add(i, arr[i] - arr[i - 1]);
        }
        for (int i = 0; i < q; i++) {
            int operation = io.nextInt();
            if (operation == 1) {
                int a = io.nextInt();
                int b = io.nextInt();
                int u = io.nextInt();
                seg.add(a, u);
                if (b < n)
                    seg.add(b + 1, -u);
            } else {
                int k = io.nextInt();
                io.println(seg.sum(0, k));
            }
        }
        io.close();
    }

    static class SegmentTree {
        private long[] tree;
        private int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new long[n * 2];
        }

        public long sum(int a, int b) {
            a += n;
            b += n;
            long sum = 0;
            while (a <= b) {
                if (a % 2 == 1)
                    sum += tree[a++];
                if (b % 2 == 0)
                    sum += tree[b--];
                a /= 2;
                b /= 2;
            }
            return sum;
        }

        public void add(int index, long amount) {
            index += n;
            tree[index] += amount;
            for (index /= 2; index >= 1; index /= 2) {
                tree[index] = tree[2 * index] + tree[2 * index + 1];
            }
        }
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
