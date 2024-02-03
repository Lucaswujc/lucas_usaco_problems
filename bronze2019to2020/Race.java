package bronze2019to2020;

import java.util.*;

public class Race {
    public static int calculate(int tracklength, int topspeed) {
        int currentspeed = 0;
        int currentpos = 0;
        int timecount = 0;
        while (currentpos < tracklength) {
            if (test(currentpos, currentspeed + 1, tracklength, topspeed)) {
                currentspeed++;
                currentpos = currentpos + currentspeed;
            } else if (test(currentpos, currentspeed, tracklength, topspeed)) {
                currentpos = currentpos + currentspeed;
            } else {
                currentspeed--;
                currentpos = currentpos + currentspeed;
            }
            System.out.println(String.format("Current Speed = %d, Current Position = %d",currentspeed,currentpos));
            timecount++;
        }
        return timecount;
    }

    public static Boolean test(int currentpos, int testspeed, int tracklength, int topspeed) {
        if (testspeed < topspeed) {
            return true;
        }
        int sum = testspeed*(testspeed +1)/2 - ((topspeed-1)*topspeed)/2 + currentpos;
        if (sum <= tracklength){
            return true;
        }
        else{
            sum = sum - topspeed;
            if(sum <= tracklength){
                return true;
            }
            else{
                return false;
            }
        }
        // for (int speed = testspeed; speed >= topspeed; speed--) {
        //     currentpos = currentpos + speed;
        //     if (currentpos >= tracklength && speed != topspeed) {
        //         return false;
        //     }
        // }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tracklength = scan.nextInt();
        int testcases = scan.nextInt();
        int[] topspeeds = new int[testcases];
        for (int i = 0; i < testcases; i++) {
            topspeeds[i] = scan.nextInt();
        }
        for (int i = 0; i < testcases; i++) {
            System.out.println(String.format("Timespent = %d ", calculate(tracklength, topspeeds[i])));
        }
        scan.close();
    }
}
