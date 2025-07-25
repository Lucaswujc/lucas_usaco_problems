package USACOGold;
import java.util.Scanner;

public class DistributingApples {
    static final long MOD = (long) 1e9 + 7;
    static final int MAXN = (int) 2e6;
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

        int k = scan.nextInt();
        int n = scan.nextInt();

        long ans = factorial[n + k - 1];
        ans = (ans * inv[k - 1]) % MOD;
        ans = (ans * inv[n]) % MOD;
        System.out.println(ans);
        scan.close();
    }
}
