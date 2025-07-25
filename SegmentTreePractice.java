import java.io.*;
import java.util.*;

/** A data structure that can answer point update & range min queries. */
public class SegmentTreePractice {
    public final int len;
    private final int[] segtree; // index 0 is not in use

    private int combine(int a, int b) {
        return Math.min(a, b);
    }

    private void build(int[] arr, int at, int atLeft, int atRight) {
        if (atLeft == atRight) {
            segtree[at] = arr[atLeft];
            return;
        }
        int mid = (atLeft + atRight) / 2;
        build(arr, 2 * at, atLeft, mid);
        build(arr, 2 * at + 1, mid + 1, atRight);
        segtree[at] = combine(segtree[2 * at], segtree[2 * at + 1]);
    }

    private void set(int ind, int val, int at, int atLeft, int atRight) {
        if (atLeft == atRight) {
            segtree[at] = val;
            return;
        }
        int mid = (atLeft + atRight) / 2;
        if (ind <= mid) {
            set(ind, val, 2 * at, atLeft, mid);
        } else {
            set(ind, val, 2 * at + 1, mid + 1, atRight);
        }
        segtree[at] = combine(segtree[2 * at], segtree[2 * at + 1]);
    }

    private int rangeMin(int start, int end, int at, int atLeft, int atRight) {
        if (atRight < start || end < atLeft) {
            return Integer.MAX_VALUE;
        }
        if (start <= atLeft && atRight <= end) {
            return segtree[at];
        }
        int mid = (atLeft + atRight) / 2;
        int leftRes = rangeMin(start, end, 2 * at, atLeft, mid);
        int rightRes = rangeMin(start, end, 2 * at + 1, mid + 1, atRight);
        return combine(leftRes, rightRes);
    }

    /** Sets the value at ind to val. */
    void set(int ind, int val) {
        set(ind, val, 1, 0, len - 1);
    }

    /** @return the minimum element in the range [start, end] */
    int rangeMin(int start, int end) {
        return rangeMin(start, end, 1, 0, len - 1);
    }

    public SegmentTreePractice(int len) {
        this.len = len;
        segtree = new int[len * 4];
        Arrays.fill(segtree, Integer.MAX_VALUE);
    }

    public SegmentTreePractice(int[] arr) {
        this.len = arr.length;
        segtree = new int[len * 4];
        build(arr, 1, 0, len - 1);
    }

    public static void main(String[] args) {
        Kattio io = new Kattio();
        int arrLen = io.nextInt();
        int queryNum = io.nextInt();

        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = io.nextInt();
        }
        SegmentTreePractice segtree = new SegmentTreePractice(arr);

        for (int i = 0; i < queryNum; i++) {
            int type = io.nextInt();
            int arg1 = io.nextInt();
            int arg2 = io.nextInt();
            if (type == 1) {
                segtree.set(arg1 - 1, arg2);
            } else {
                io.println(segtree.rangeMin(arg1 - 1, arg2 - 1));
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