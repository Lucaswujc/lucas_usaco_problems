import java.util.*;
public class GCDExtreme {
    private static final int MAX_N = 1000000;
    private static long[] phi = new long[MAX_N + 1];
    private static Map<Long, Long> memo = new HashMap<>();
    private static long solve(long n) {
        if (n <= MAX_N) return phi[(int) n];
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long d = (long) Math.sqrt(n);
        long ans = n * (n + 1) / 2;
        
        for (int i = 2; n / i > d; i++) {
            ans -= solve(n / i);
        }
        
        for (int i = (int) d; i >= 1; i--) {
            ans -= (n / i - n / (i + 1)) * solve(i);
        }
        
        memo.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 1; i <= MAX_N; i++) {
            phi[i] = i;
        }
        for (int i = 2; i <= MAX_N; i++) {
            if (phi[i] == i) {
                phi[i]--;
                for (int j = i + i; j <= MAX_N; j += i) {
                    phi[j] = phi[j] / i * (i - 1);
                }
            }
        }
        for (int i = 2; i <= MAX_N; i++) {
            phi[i] += phi[i - 1];
        }
        while (scan.hasNextInt()) {
            int n = scan.nextInt();
            if (n == 0) break;

            long ans = 0;
            for (int d = 1; d <= n; d++) {
                ans += d * (solve(n / d) - 1);
            }
            
            System.out.println(ans);
        }
        
        scan.close();
    }
}
