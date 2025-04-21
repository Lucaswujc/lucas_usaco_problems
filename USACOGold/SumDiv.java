package USACOGold;

import java.util.*;

public class SumDiv {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        double ans = 1;
        for (int i = 2; i <= a; i++) {
            if (!(a % i == 0)) {
                continue;
            }
            int count = 0;
            while (a % i == 0) {
                count++;
                a /= i;
            }
            double sum = 0;
            sum = (Math.pow(i, (count * b) + 1) - 1) / (i - 1);
            ans *= sum;
            ans = ans % (Math.pow(10, 9) + 7);
        }
        System.out.println((int) ans);
        scan.close();
    }
}