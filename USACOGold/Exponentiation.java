package USACOGold;

import java.io.*;
import java.util.*;

public class Exponentiation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            out.println(exp(a, b, (long) 1e9 + 7));
        }
        out.close();
    }

    public static long exp(long x, long n, long m) {
        assert (n >= 0);
        x %= m; // note: m * m must be less than 2^63 to avoid ll overflow
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