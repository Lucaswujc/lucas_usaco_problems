import java.util.*;

public class OracAndLCM {
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        long gcd = gcd(arr[0], arr[1]);
        long ans = arr[0] / gcd * arr[1];
        for (int i = 2; i < n; gcd = gcd(gcd, arr[i]), i++) {
            ans = gcd(ans, lcm(arr[i], gcd));
        }
        System.out.println(ans);
        scan.close();
    }
}