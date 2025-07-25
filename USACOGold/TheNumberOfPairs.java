package USACOGold;

import java.util.*;

public class TheNumberOfPairs {
    public static void main(String[] args) {
        int n = 20000013;
        int[] mind = new int[n];
        int[] val = new int[n];
        Arrays.fill(mind, -1);
        mind[1] = 1;

        for (int i = 2; i < n; i++) {
            if (mind[i] == -1) {
                for (int j = i; j < n; j += i) {
                    if (mind[j] == -1) {
                        mind[j] = i;
                    }
                }
            }
        }

        for (int i = 2; i < n; i++) {
            int j = i / mind[i];
            val[i] = val[j] + (mind[i] != mind[j] ? 1 : 0);
        }

        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        while (t-- > 0) {
            int c = scan.nextInt();
            int d = scan.nextInt();
            int x = scan.nextInt();
            int ans = 0;

            for (int i = 1; i * i <= x; i++) {
                if (x % i != 0)
                    continue;

                int k1 = x / i + d;
                if (k1 % c == 0)
                    ans += 1 << val[k1 / c];

                if (i * i == x)
                    continue;

                int k2 = i + d;
                if (k2 % c == 0)
                    ans += 1 << val[k2 / c];
            }

            System.out.println(ans);
        }
        scan.close();
    }
}