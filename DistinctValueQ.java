import java.util.*;
import java.io.*;

public class DistinctValueQ {
    private static class Query {
        public int left;
        public int right;

        Query(int a, int b) {
            left = a;
            right = b;
        }
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int n = io.nextInt();
        int q = io.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = io.nextInt();
        }

        List<List<Query>> queries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            queries.add(new ArrayList<>());
        }
        for (int i = 0; i < q; i++) {
            int l = io.nextInt() - 1;
            int r = io.nextInt() - 1;
            queries.get(l).add(new Query(r, i));
        }
        Map<Integer, Integer> last_index = new HashMap<>();
        BIT bit = new BIT(n);
        int[] solution = new int[q];
        for (int i = n - 1; i >= 0; i--) {
            int val = arr[i];
            if (last_index.containsKey(val)) {
                bit.add(last_index.get(val), -1);
            }
            last_index.put(val, i);
            bit.add(i, 1);

            for (Query qr : queries.get(i)) {
                solution[qr.right] = (int) bit.prefSum(qr.left);
            }
        }
        for (int i = 0; i < q; i++) {
            io.println(solution[i]);
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
