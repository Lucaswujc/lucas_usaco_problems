package USACOSilver;
import java.util.*;
public class CowCheckups {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long[] a = new long[(int) n];
        long[] b = new long[(int) n];

        for (int i = 0; i < n; i++) {
            a[i] = scan.nextLong();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scan.nextLong();
        }
        //count how many species already match
        long[] correct = new long[(int) n + 1];
        correct[0] = 0;
        for (int i = 0; i < n; i++) {
            correct[i + 1] = correct[i] + (a[i] == b[i] ? 1 : 0);
        }
        Map<Long, List<Long>> gIdx = new HashMap<>();
        Map<Long, List<Long>> gPre = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long cow = b[i];
            //group similar species
            //weight them closer to edges instead of middle
            gIdx.putIfAbsent(cow, new ArrayList<>());
            gIdx.get(cow).add(Math.min(i+1, n- i));
        }
        long ans = 0;
        //sum cows that are already matching
        for (int i = 1; i < n; i++) {
            ans += (correct[i] - correct[0]) * (n - i);
        }
        for (int i = (int) n - 2; i >= 0; i--) {
            ans += (correct[(int) n] - correct[i + 1]) * (i + 1);
        }
        for (Map.Entry<Long, List<Long>> entry : gIdx.entrySet()) {
            //calculate prefix sum for each species
            List<Long> idx = entry.getValue();
            Collections.sort(idx);
            List<Long> prefix = new ArrayList<>();
            prefix.add(0L);
            for (long i : idx) {
                prefix.add(prefix.get(prefix.size() - 1) + i);
            }
            gPre.put(entry.getKey(), prefix);
        }

        for (int i = 0; i < n; i++) {
            long cow = a[i];
            if (!gIdx.containsKey(cow)) {
                continue;
            }
            List<Long> idx = gIdx.get(cow);
            List<Long> prefix = gPre.get(cow);
            long min = Math.min(i + 1, n - i);
            int upper = Collections.binarySearch(idx, min);
            if (upper < 0) {
                upper = -1*upper - 1;
            }
            long matching = idx.size() - upper;
            ans += matching * min;
            ans += prefix.get(upper);
        }

        System.out.println(ans);
        scan.close();
    }
}
