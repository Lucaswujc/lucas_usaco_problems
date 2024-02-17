package USACO_bronzeclass_problems;

import java.util.*;

public class CowCounting {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int numOfCows = scan.nextInt();
            int symbolToAvoid = scan.nextInt();
            int win = 0;
            String s = "1";
            String b = Integer.toString(symbolToAvoid);
            while(win <= numOfCows){
                if(s.contains(b) == true){
                    
                }
                else{
                    win = win + 1;
                }
                int s1 = Integer.parseInt(s);
                if(win == numOfCows){
                    win = s1;
                    break;
                }
                s1 = s1 + 1;
                s = Integer.toString(s1);
            }
            System.out.println(win);
        }
    }
}