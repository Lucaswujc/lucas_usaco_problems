package USACOSilver;
import java.io.*;
import java.util.*;
public class Bakery {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t> 0) {
            int n = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();

            int[] a = new int[n];
            int[] b = new int[n];
            int[] c = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
                b[i] = scan.nextInt();
                c[i] = scan.nextInt();
            }

            int lo = 0;
            int hi = x+y-2;

            while (hi > lo) {
                int mid = (lo + hi) / 2;
                if (check(mid, n, x, y, a, b, c)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            System.out.println(lo);
            t--;
        }

        scan.close();
    }

    private static boolean check(int w, int N, int X, int Y, int[] A, int[] B, int[] C) {
        int lx = Math.max(1, X - w);
        int hx = Math.min(X + Y - w - 1, X);
        int d = X + Y - w;

        for (int i = 0; i < N; i++) {
            int a = A[i];
            int b = B[i];
            int c = C[i];

            if (b - a > 0) {
                lx = Math.max(lx, (-c + b * d + (b - a - 1)) / (b - a));
            } else if (a - b > 0) {
                hx = Math.min(hx, (c - b * d) / (a - b));
            } else {
                if (a * d > c) {
                    return false;
                }
            }
        }

        return lx <= hx;
    }
}