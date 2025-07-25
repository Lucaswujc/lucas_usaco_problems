package USACOGold;

import java.util.*;

public class DigitSum {
    static final int MAX_LEN = 17;
    static long[] ans_pow10 = new long[MAX_LEN];

    static long pref(long upper) {
        String upperStr = Long.toString(upper);
        int n = upperStr.length();
        final int MAX_SUM = 9 * n + 1;

        long[][][] dp = new long[n][MAX_SUM][2];

        int firstDigit = upperStr.charAt(0) - '0';
        dp[0][firstDigit][0] = 1;
        for (int i = 1; i < firstDigit; i++) {
            dp[0][i][1] = 1;
        }

        int currSum = firstDigit;
        for (int i = 1; i < n; i++) {
            int nextDigit = upperStr.charAt(i) - '0';

            dp[i][currSum + nextDigit][0] = 1;
            for (int j = 0; j < nextDigit; j++) {
                dp[i][currSum + j][1]++;
            }

            for (int j = 0; j < 10; j++) {
                for (int prevSum = 0; prevSum < MAX_SUM - j; prevSum++) {
                    dp[i][prevSum + j][1] += dp[i - 1][prevSum][1];
                }
            }

            currSum += nextDigit;
        }

        long ans = 0;
        for (int sum = 0; sum < MAX_SUM; sum++) {
            for (int free = 0; free < 2; free++) {
                ans += dp[n - 1][sum][free] * sum;
            }
        }

        ans += ans_pow10[n - 1];

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long pow10 = 1;
        for (int i = 1; i < MAX_LEN; i++) {
            pow10 *= 10;
            ans_pow10[i] = pref(pow10 - 1);
        }

        int testNum = sc.nextInt();
        for (int t = 0; t < testNum; t++) {
            long l = sc.nextLong();
            long r = sc.nextLong();
            if (l == 0) {
                System.out.println(pref(r));
            } else {
                System.out.println(pref(r) - pref(l - 1));
            }
        }

        sc.close();
    }
}
