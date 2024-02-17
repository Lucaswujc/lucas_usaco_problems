package bronze2022to2023;

import java.util.Scanner;

public class Haybale {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // N is the expected # of input lines
        int N = scan.nextInt();
        // T is the total number of days
        scan.nextInt();
        long totalHaybaleEaten = 0;
        long halBalesLeftInTheBarn = 0;
        long previousDeliveryDay = -1;
        for (int i = 0; i < N; i++) {
            long di = scan.nextInt();
            long bi = scan.nextInt();
            if (previousDeliveryDay == -1) {
                // first delivery
                halBalesLeftInTheBarn += bi;
                previousDeliveryDay = di;
            } else {
                // calculate differentce between previous deliverday and
                // current delivery day
                // then use min difference and halbalesleftinthebarn to
                // calculate the incremental to the total eaten
                long daydiff = di - previousDeliveryDay;
                long eat = Math.min(halBalesLeftInTheBarn, daydiff);
                halBalesLeftInTheBarn = halBalesLeftInTheBarn - eat + bi;
                totalHaybaleEaten += eat;
                previousDeliveryDay = di;
            }
        }
        long daydiff = N - previousDeliveryDay;
        long eat = Math.min(halBalesLeftInTheBarn, daydiff);
        totalHaybaleEaten += eat;
        System.out.println(totalHaybaleEaten);
        scan.close();
    }
}