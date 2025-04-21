import java.util.*;

public class CloseTuples {
    static final long MOD = (long) 1e9 + 7;
    static final int MAXN = (int) 1e6;
    static long[] factorial = new long[MAXN + 1];
    static long[] inv = new long[MAXN + 1];

    static long modpow(long a, long b, long m) {
        long prod = 1;
        a %= m;
        while (b > 0) {
            if (b % 2 == 1) {
                prod = (prod * a) % m;
            }
            a = (a * a) % m;
            b /= 2;
        }
        return prod;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        factorial[0] = inv[0] = 1;
        factorial[1] = inv[1] = 1;
        for (int i = 2; i <= MAXN; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
            inv[i] = modpow(factorial[i], MOD - 2, MOD);
        }
        int t = scan.nextInt();
        ;
        while (t > 0) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int k = scan.nextInt();
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextLong();
            }
            Arrays.sort(arr);
            long ans = 0;
            for (int i = 0; i < n - m + 1; i++) {
                int count = upperBound(arr, arr[i] + k);
                count = count - i;
                if (count >= m) {
                    long a = factorial[count - 1];
                    a = (a * inv[m - 1]) % MOD;
                    a = (a * inv[count - m]) % MOD;
                    ans += a;
                }
                ans = ans % MOD;
            }
            System.out.println(ans);
            t--;
        }
        scan.close();
    }

    static int upperBound(long[] arr, long val) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= val) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
