import java.util.*;

public class Exponentiation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            long a = scan.nextLong();
            long b = scan.nextLong();
            System.out.println(exp(a, b, (long) 1e9 + 7));
        }
        scan.close();
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