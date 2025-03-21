import java.io.*;

public class SumOfDivisors {
    static final int MOD = (int) 1e9 + 7;
    static final int TWO_MOD_INV = 500000004;

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(read.readLine());

        long total = 0;
        long pos = 1;
        while (pos <= n) {
            long add = n / pos;
            long end = n / add;
            total = (total + add * totalSum(pos, end)) % MOD;
            pos = end + 1;
        }
        System.out.println(total / 2);
    }

    static long totalSum(long start, long end) {
        return ((((end - start + 1) % MOD) * ((start + end) % MOD) % MOD) * TWO_MOD_INV % MOD);
    }
}