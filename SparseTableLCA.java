import java.util.*;
import java.io.*;

public class SparseTableLCA {

    static int n, q, power;
    static int[] arr;
    static int[][] table;

    static int qry(int l, int r) {
        int sz = 31 - Integer.numberOfLeadingZeros(r - l); // equivalent to floor(power2(r - l))
        return Math.min(table[l][sz], table[r - (1 << sz)][sz]);
    }

    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio();
        n = io.nextInt();
        q = io.nextInt();

        power = 32 - Integer.numberOfLeadingZeros(n) + 1; // ceil(power2(n)) + 1
        arr = new int[n];
        table = new int[n][power];

        for (int i = 0; i < n; i++) {
            arr[i] = io.nextInt();
            table[i][0] = arr[i];
        }

        for (int j = 1; j < power; j++) {
            for (int i = 0; i < n; i++) {
                table[i][j] = table[i][j - 1];
                int next = i + (1 << (j - 1));
                if (next < n) {
                    table[i][j] = Math.min(table[i][j], table[next][j - 1]);
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int l = io.nextInt();
            int r = io.nextInt();
            io.print(qry(l, r) + "\n");
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
}
