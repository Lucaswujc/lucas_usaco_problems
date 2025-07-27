import java.util.*;
import java.io.*;

public class HairCut {
    private static final int MAX_N = (int) 1e7;

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int t = io.nextInt();
        while (t-- > 0) {
            BIT bit = new BIT(MAX_N + 1);
            int n = io.nextInt();
            long invs = 0;
            for (int i = 0; i < n; i++) {
                int a = io.nextInt();
                bit.add(a, 1);
                invs += bit.prefSum(MAX_N) - bit.prefSum(a);
            }
            io.println(invs);
        }
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

    static class BIT {
        private final long[] bit;
        private final long[] arr;
        private final int len;

        public BIT(int len) {
            bit = new long[len + 1];
            arr = new long[len];
            this.len = len;
        }

        /** Sets the value of index ind at the actual array to vall. */
        public void set(int ind, long val) {
            add(ind, val - arr[ind]);
        }

        /** Adds val to the element at index ind. */
        public void add(int ind, long val) {
            arr[ind] += val;
            ind++;
            for (; ind <= len; ind += ind & -ind) {
                bit[ind] += val;
            }
        }

        /** @return The sum of all values in [0, ind]. */
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
