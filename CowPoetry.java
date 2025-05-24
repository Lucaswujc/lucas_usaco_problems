import java.io.*;
import java.util.*;

public class CowPoetry {
    static final long MOD = (long) 1e9 + 7;

    // Binary exponentiation with a modulus
    static long modExp(long a, long b) {
        if (a == 0) return 0;
        long ret = 1;
        while (b > 0) {
            if (b % 2 == 1) ret = (ret * a) % MOD;
            a = (a * a) % MOD;
            b /= 2;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        Scanner fin = new Scanner(new File("poetry.in"));
        PrintWriter fout = new PrintWriter(new File("poetry.out"));

        int n = fin.nextInt();
        int m = fin.nextInt();
        int k = fin.nextInt();

        long[] count = new long[k + 1];
        List<List<Integer>> type = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            type.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int a = fin.nextInt();
            int b = fin.nextInt();
            count[a]++;
            type.get(b).add(a);
        }

        int[] rhyme = new int[m];
        String line = fin.next();
        for (int i = 0; i < m; i++) {
            rhyme[i] = line.charAt(i) - 'A';
        }

        long[] dp = new long[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = (dp[i] + dp[i - j] * count[j]) % MOD;
            }
        }

        long[] total = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int syllable : type.get(i)) {
                total[i] = (total[i] + dp[k - syllable]) % MOD;
            }
        }

        if (dp[k] == 0) {
            fout.println(0);
            fout.close();
            return;
        }

        Arrays.sort(rhyme);
        List<Integer> groups = new ArrayList<>();
        int back = 0;
        for (int i = 1; i <= m; i++) {
            int curr = (i == m) ? -1 : rhyme[i];
            if (curr != rhyme[i - 1]) {
                groups.add(i - back);
                back = i;
            }
        }

        long ans = 1;
        for (int g : groups) {
            long curr = 0;
            for (int i = 1; i <= n; i++) {
                curr = (curr + modExp(total[i], g)) % MOD;
            }
            ans = (ans * curr) % MOD;
        }

        fout.println(ans);
        fout.close();
    }
}
