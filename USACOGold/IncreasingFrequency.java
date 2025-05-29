package USACOGold;
import java.util.*;
import java.io.*;

public class IncreasingFrequency {
    static int[] cntC;
    static final int INF = (int) 1e9;

    static int getCnt(int l, int r) {
        return cntC[r] - cntC[l];
    }

    static int maxSegment(List<Integer> s) {
        int mx = -INF, bal = 0;
        for (int v : s) {
            bal = Math.max(0, bal + v);
            mx = Math.max(mx, bal);
        }
        return mx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        cntC = new int[n + 1];
        for (int i = 0; i < n; i++) {
            cntC[i + 1] = cntC[i] + (a[i] == c ? 1 : 0);
        }

        int cntDif = Arrays.stream(a).max().orElse(0) + 1;
        List<List<Integer>> segs = new ArrayList<>(cntDif);
        int[] lst = new int[cntDif];
        Arrays.fill(lst, -1);

        for (int i = 0; i < cntDif; i++) segs.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            int val = a[i];
            segs.get(val).add(-getCnt(lst[val] + 1, i));
            lst[val] = i;
            segs.get(val).add(1);
        }

        for (int v = 0; v < cntDif; v++) {
            segs.get(v).add(-getCnt(lst[v] + 1, n));
        }

        int ans = 0;
        for (int v = 0; v < cntDif; v++) {
            if (v == c) continue;
            ans = Math.max(ans, maxSegment(segs.get(v)));
        }

        pw.println(getCnt(0, n) + ans);
        pw.flush();
    }
}
