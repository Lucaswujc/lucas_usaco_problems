import java.util.*;
import java.io.*;

public class CircleCross {
    static class Pair implements Comparable<Pair> {
        int start, end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("circlecross");
        int n = io.nextInt();
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(-1, -1);
        }
        for (int i = 0; i < n; i++) {
            int a = io.nextInt();
            if (arr[a - 1] == null) {
                arr[a - 1] = new Pair(i + 1, -1);
            } else {
                arr[a - 1].end = i + 1;
            }
        }
        BIT bit = new BIT(n);
        int ans = 0;
        for (Pair p : arr) {
            ans += bit.sum(p.end) - bit.sum(p.start);
            bit.add(p.end, 1);
        }
        io.println(ans);
        io.close();
    }

    static class BIT {
        public int[] tree;

        public BIT(int n) {
            tree = new int[n + 5];
        }

        public void add(int index, int val) {
            index++;
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int sum(int index) {
            int ret = 0;
            index++;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
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