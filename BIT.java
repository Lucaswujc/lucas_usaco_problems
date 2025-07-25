import java.io.*;
import java.util.*;

public class BIT {
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

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int arrLen = io.nextInt();
        int queryNum = io.nextInt();

        BIT bit = new BIT(arrLen);
        for (int i = 0; i < arrLen; i++) {
            bit.set(i, io.nextInt());
        }

        for (int i = 0; i < queryNum; i++) {
            int type = io.nextInt();
            int arg1 = io.nextInt();
            int arg2 = io.nextInt();
            if (type == 1) {
                bit.set(arg1 - 1, arg2);
            } else if (type == 2) {
                io.println(bit.prefSum(arg2 - 1) - bit.prefSum((arg1 - 1) - 1));
            }
        }

        io.close();
    }

    // BeginCodeSnip{Kattio}
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
    // EndCodeSnip
}