package USACOGold;

import java.util.*;

public class CommonDivisors {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] divisors = new int[1000001];
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int max = (int) Math.sqrt(arr[i]);
            for (int div = 1; div <= max; div++) {
                if (arr[i] % div == 0) {
                    divisors[div]++;
                    if (arr[i] / div != div) {
                        divisors[arr[i] / div]++;
                    }
                }
            }
        }
        for (int i = 1000000; i >= 1; i--) {
            if (divisors[i] >= 2) {
                System.out.println(i);
                break;
            }
        }
        scan.close();
    }
}