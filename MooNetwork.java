import java.io.*;
import java.util.*;

public class MooNetwork {
    static final int MAX_Y = 10;

    static int findParent(int[] top, int a) {
        if (top[a] == a) return a;
        return top[a] = findParent(top, top[a]);
    }

    static boolean merge(int[] top, int[] sizes, int a, int b) {
        a = findParent(top, a);
        b = findParent(top, b);
        if (a == b) return false;
        if (sizes[b] > sizes[a]) {
            int temp = a;
            a = b;
            b = temp;
        }
        top[b] = a;
        sizes[a] += sizes[b];
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] points = new long[n][3]; // {x, y, index}
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
            points[i][2] = i;
        }

        Arrays.sort(points, Comparator.comparingLong(a -> a[0]));

        long[][] buffer = new long[MAX_Y + 1][3];
        for (int i = 0; i <= MAX_Y; i++) {
            Arrays.fill(buffer[i], -1);
        }

        List<long[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= MAX_Y; j++) {
                if (buffer[j][2] != -1) {
                    long dx = points[i][0] - buffer[j][0];
                    long dy = points[i][1] - buffer[j][1];
                    long dist = dx * dx + dy * dy;
                    edges.add(new long[]{dist, points[i][2], buffer[j][2]});
                }
            }
            int y = (int) points[i][1];
            if (y >= 0 && y <= MAX_Y)
                buffer[y] = Arrays.copyOf(points[i], 3);
        }

        edges.sort(Comparator.comparingLong(a -> a[0]));

        int[] top = new int[n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            top[i] = i;
            sizes[i] = 1;
        }

        long ans = 0;
        for (long[] edge : edges) {
            if (merge(top, sizes, (int) edge[1], (int) edge[2])) {
                ans += edge[0];
            }
        }

        System.out.println(ans);
    }
}
