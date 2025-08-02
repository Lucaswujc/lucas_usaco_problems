import java.util.*;
import java.io.*;

public class MinCross {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("mincross");
        int n = io.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = io.nextInt() - 1;
        }

        for (int i = 0; i < n; i++) {
            b[i] = io.nextInt() - 1;
        }

        long finalAns = Long.MAX_VALUE;

        for (int i = 0; i < 2; i++) {
            int[] posA = new int[n];
            int[] posB = new int[n];
            for (int j = 0; j < n; j++) {
                posA[a[j]] = j;
                posB[b[j]] = j;
            }

            OrderStatisticSet oset = new OrderStatisticSet();
            for (int j = 0; j < n; j++) {
                oset.insert(posB[j]);
            }

            long crossings = 0;
            for (int j = 0; j < n; j++) {
                oset.remove(posB[a[j]]);
                crossings += oset.orderOfKey(posB[a[j]]);
            }

            long x = crossings;
            for (int j = 0; j < n; j++) {
                crossings -= posA[b[j]];
                crossings += n - posA[b[j]] - 1;
                x = Math.min(x, crossings);
            }

            finalAns = Math.min(finalAns, x);
            int[] tmp = a;
            a = b;
            b = tmp;
        }

        io.println(finalAns);
        io.close();
    }

    static class OrderStatisticSet {
        private TreeMap<Integer, Integer> map = new TreeMap<>();
        private int size = 0;

        public void insert(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
        }

        public void remove(int val) {
            if (!map.containsKey(val))
                return;
            if (map.get(val) == 1)
                map.remove(val);
            else
                map.put(val, map.get(val) - 1);
            size--;
        }

        public int orderOfKey(int val) {
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : map.headMap(val, false).entrySet()) {
                count += entry.getValue();
            }
            return count;
        }

        public int size() {
            return size;
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