package USACOGold;
import java.util.Scanner;

public class MontMortNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        long c = 1;
        for (int i = 1; i <= n; i++) {
            c = (c * i) + (i % 2 == 1 ? -1 : 1);
            c %= m;
            c += m;
            c %= m;
            System.out.print(c + " ");
        }
        System.out.println();
        scan.close();
    }
}