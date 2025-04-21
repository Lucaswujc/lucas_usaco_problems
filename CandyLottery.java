import java.util.*;

public class CandyLottery {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        double ans = 0;
        for (int i = 1; i <= k; i++) {
            double term1 = Math.pow((double) i / k, n);
            double term2 = Math.pow((double) (i - 1) / k, n);
            ans += i * (term1 - term2);
        }
        System.out.printf("%.6f\n", ans);
        scan.close();
    }
}