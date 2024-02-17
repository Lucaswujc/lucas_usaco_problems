package USACO_bronzeclass_problems;

import java.util.*;

public class HayExpenses {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int[] hay = new int[a];
            for(int i =0; i <a; i++){
                hay[i] = scan.nextInt();
            }
            int[] startdays = new int[b];;
            int[] enddays = new int[b];
            for(int i =0; i <b; i++){
                startdays[i] = scan.nextInt();
                enddays[i] = scan.nextInt();
            }
            int calc = 0;
            for(int i = 0; i < b; i++){
                calc = 0;
                for(int j = startdays[i]; j <= enddays[i]; j++){
                    calc = calc + hay[j-1];
                }
                System.out.println(calc);
            }
        }
    }
}