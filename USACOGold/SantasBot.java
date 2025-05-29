package USACOGold;

import java.util.*;

public class SantasBot {
    private static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[][] children = new int[n][];
        HashMap<Integer, Integer> gifts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int g = scan.nextInt();
            children[i] = new int[g];
            for (int j = 0; j < g; j++) {
                children[i][j] = scan.nextInt();
            }
            for (int c : children[i]) {
                gifts.put(i, gifts.getOrDefault(c, 0) + 1);
            }
        }

        long probTotal = 0;
        for (int[] c : children) {
            long denom = (long) n * c.length * n;
            for (int i : c) {
                int num = gifts.get(i);
                probTotal = (probTotal + fracTotal(num, denom)) % MOD;
            }
        }
        System.out.println(probTotal);
        scan.close();
    }

    private static long fracTotal(long num, long denom) {
        return ((num % MOD) * modInv(denom)) % MOD;
    }

    private static long modInv(long n) {
        return pow(n, MOD - 2);
    }

    // from /gold/modular
    private static long pow(long base, long exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("exp must be nonnegative");
        }
        base %= MOD;
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp /= 2;
        }
        return res;
    }
}