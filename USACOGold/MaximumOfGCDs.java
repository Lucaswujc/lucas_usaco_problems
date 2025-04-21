package USACOGold;

import java.util.*;
import java.io.*;

public class MaximumOfGCDs {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) throws java.lang.Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t > 0) {
            int n = scan.nextInt();
            int[] arr = new int[n];
            int size = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = scan.nextInt();
                size = Math.max(arr[i], size);
            }
            int[] divisors = new int[size + 1];
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
            for (int i = 1; i <= n; i++) {
                for (int j = size; j >= 1; j--) {
                    if (divisors[j] >= i) {
                        System.out.print(j + " ");
                        break;
                    }
                }
            }
            System.out.println();
            t--;
        }

        scan.close();
    }
}
