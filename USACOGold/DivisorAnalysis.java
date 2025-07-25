package USACOGold;

import java.io.*;
import java.util.*;

public class DivisorAnalysis {
    static final long MOD = (long) 1e9 + 7;
    static final int MAXN = 100001;
    static long[] p = new long[MAXN];
    static long[] k = new long[MAXN];

    static long expo(long base, long pow) {
        long ans = 1;
        base %= MOD;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            pow >>= 1;
        }
        return ans;
    }

    static long modInverse(long a) {
        return expo(a, MOD - 2); // Fermat's Little Theorem
    }

    static long expoModM1(long base, long pow) {
        long ans = 1;
        base %= MOD;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            pow >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Long.parseLong(st.nextToken());
            k[i] = Long.parseLong(st.nextToken());
        }

        long count = 1, sum = 1, prod = 1, count2 = 1;

        for (int i = 0; i < n; i++) {
            count = count * (k[i] + 1) % MOD;
            long numerator = expo(p[i], k[i] + 1) - 1;
            if (numerator < 0)
                numerator += MOD;
            long denominator = modInverse(p[i] - 1);
            sum = sum * numerator % MOD * denominator % MOD;
            long powerSum = k[i] * (k[i] + 1) / 2 % (MOD - 1); // use MOD - 1 for exponent modulus
            long basePower = expo(p[i], powerSum);
            prod = expo(prod, k[i] + 1) * expo(basePower, count2) % MOD;

            count2 = count2 * (k[i] + 1) % (MOD - 1);
        }

        out.println(count + " " + sum + " " + prod);
        out.close();
    }
}
