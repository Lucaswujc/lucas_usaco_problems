package USACO_bronze_problems;

import java.util.*;

public class ClaustrophobicCows {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int n = scan.nextInt();
            long[] x = new long[n];
            long[] y = new long[n];
            for(int i= 0; i < n; i ++){
                x[i] = scan.nextLong();
                y[i] = scan.nextLong();
            }
            int one = 0;
            int two = 0;
            long calc = 0;
            long win = (y[1] - y[0])*(y[1] - y[0]) + (x[1] - x[0])*(x[1] - x[0]);
            for(int i = 0; i < n; i++ ){
                for(int j = i; j < n; j++){
                    calc = (y[j] - y[i])*(y[j] - y[i]) + (x[j] - x[i])*(x[j] - x[i]);
                    if(calc < win){
                        if(i == j){
                            calc = 0;
                        }
                        else{
                            win = calc;
                            one = i;
                            two = j;
                        }
                    }
                }
            }
            System.out.print(one+1);
            System.out.print(" ");
            System.out.print(two+1);
        }
    }
}