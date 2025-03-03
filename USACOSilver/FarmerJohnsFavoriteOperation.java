package USACOSilver;
import java.util.*;
public class FarmerJohnsFavoriteOperation {
        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t > 0) {
            long n = scan.nextLong();
            long m = scan.nextLong();
            long[] a = new long[(int) n];
            long[] rem = new long[(int) n];
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextLong();
                
                rem[i] = a[i] % m;
            }
            if (m == 1) {
                System.out.println(0);
                continue;
            }
            //sort
            Arrays.sort(rem);
            //double the remainder array and adjust by m
            List<Long> doubleR = new ArrayList<>();
            for (int i =0 ; i < n; i++) {
                doubleR.add(rem[i]);
            }
            for (int i =0 ; i < n; i++) {
                doubleR.add(rem[i] + m);
            }
            //prefix sums
            List<Long> prefix = new ArrayList<>();
            prefix.add(0L);
            for (long val : doubleR) {
                prefix.add(prefix.get(prefix.size() - 1) + val);
            }
            long ans = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                long curr = calculate(doubleR, prefix, i, (int) n);
                ans = Math.min(ans, curr);
            }

            System.out.println(ans);
            t--;
        }
        scan.close();
    }
    public static long calculate(List<Long> values, List<Long> prefix, int start, int length) {
        int end = start + length - 1;
        int mid = start + (length / 2);
        long center = values.get(mid);
        long left = mid - start;
        long total1 = center * left - (prefix.get(mid) - prefix.get(start));
        long right = end - mid;
        long total2 = (prefix.get(end + 1) - prefix.get(mid + 1)) - center * right;
        return total1 + total2;
    }
}
