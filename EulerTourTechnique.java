import java.util.*;
import java.io.*;

public class EulerTourTechnique {
    static List<List<Integer>> neighbors;
    static int[] start, end, arr;
    static int timer = 0;

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();
        int q = io.nextInt();

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = io.nextInt();
        }

        neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int e = 0; e < n - 1; e++) {
            int a = io.nextInt() - 1;
            int b = io.nextInt() - 1;
            neighbors.get(a).add(b);
            neighbors.get(b).add(a);
        }

        start = new int[n];
        end = new int[n];
        eulerTour(0, -1);

        BIT bit = new BIT(n);
        for (int i = 0; i < n; i++) {
            bit.set(start[i], (long) arr[i]);
        }

        for (int i = 0; i < q; i++) {
            int type = io.nextInt();
            if (type == 1) {
                int node = io.nextInt() - 1;
                int val = io.nextInt();
                bit.set(start[node], (long) val);
            } else if (type == 2) {
                int node = io.nextInt() - 1;
                long endSum = bit.prefSum(end[node] - 1);
                long startSum = (start[node] == 0) ? 0 : bit.prefSum(start[node] - 1);
                System.out.println(endSum - startSum);
            }
        }
        io.close();
    }

    static void eulerTour(int at, int prev) {
        start[at] = timer++;
        for (int neighbor : neighbors.get(at)) {
            if (neighbor != prev) {
                eulerTour(neighbor, at);
            }
        }
        end[at] = timer;
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

    static class BIT {
        private final long[] bit;
        private final long[] arr;
        private final int len;

        public BIT(int len) {
            bit = new long[len + 1];
            arr = new long[len];
            this.len = len;
        }

        public void set(int ind, long val) {
            add(ind, val - arr[ind]);
        }

        public void add(int ind, long val) {
            arr[ind] += val;
            ind++;
            for (; ind <= len; ind += ind & -ind) {
                bit[ind] += val;
            }
        }

        public long prefSum(int ind) {
            ind++;
            long sum = 0;
            for (; ind > 0; ind -= ind & -ind) {
                sum += bit[ind];
            }
            return sum;
        }
    }
}
