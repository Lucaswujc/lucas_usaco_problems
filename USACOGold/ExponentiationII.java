package USACOGold;

import java.io.*;
import java.util.*;

public class ExponentiationII {
    static long mod = (long) (1e9 + 7);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long compress = exp(b, c, mod - 1);
            out.println(exp(a, compress, mod));

        }
        out.close();
    }

    public static long exp(long x, long n, long m) {
        assert (n >= 0);
        x %= m;
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * x % m;
            }
            x = x * x % m;
            n /= 2;
        }
        return res;
    }
}