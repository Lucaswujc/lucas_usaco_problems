import java.util.*;
import java.io.*;

public class SleepyCowSorting {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("sleepy");
        int n = io.nextInt();
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = io.nextInt() - 1;
        }

        int len = 1;
        for (int i = n - 1; i > 0; i--) {
            if (cows[i] > cows[i - 1]) {
                len++;
            } else {
                break;
            }
        }
        int ans = n - len;
        io.println(ans);

        SegmentTree seg = new SegmentTree(n);
        for (int i = ans; i < n; i++) {
            seg.add(cows[i], 1);
        }
        for (int i = 0; i < ans; i++) {
            int steps = seg.sum(0, cows[i] - 1);
            io.print(steps + (ans - i - 1));
            if (i < ans - 1) {
                io.print(" ");
            }
            seg.add(cows[i], 1);
        }
        io.println();
        io.close();
    }

    static class SegmentTree {
        private int[] tree;
        private int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[n * 2];
        }

        public int sum(int a, int b) {
            a += n;
            b += n;
            int sum = 0;
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

        public void add(int index, int amount) {
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