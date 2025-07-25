package USACOGuide;

import java.io.*;
import java.util.*;

public class HaybaleFeast {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[][] haybales = new long[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            haybales[i][0] = Long.parseLong(st.nextToken());
            haybales[i][1] = Long.parseLong(st.nextToken());
        }
        int left = 0;
        long min = Long.MAX_VALUE;
        TreeMap<Long, Long> seen = new TreeMap<Long, Long>();
        long flavor = 0;
        for (int i = 0; i < n; i++) {
            flavor += haybales[i][0];
            update(seen, haybales[i][1], 1);
            while (flavor - haybales[left][0] >= m) {
                update(seen, haybales[left][1], -1);
                flavor -= haybales[left++][0];
            }
            if (flavor >= m) {
                min = Math.min(min, seen.lastKey());
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        pw.println(min);
        pw.close();
        br.close();
    }

    private static void update(Map<Long, Long> map, long k, int v) {
        if (!map.containsKey(k)) {
            map.put(k, 0L);
        }
        long nv = map.get(k) + v;
        if (nv == 0) {
            map.remove(k);
        } else {
            map.put(k, nv);
        }
    }
}