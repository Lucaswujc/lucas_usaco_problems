package USACO_bronze_problems;

import java.util.*;

public class ScavengerHunt {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int a1 = 0;
            int b1 = 0;
            for(int i = 1;i < a+1; i++){
                if(a % i ==0)
                    a1 = a1 + 1;
            }
            for(int i = 1;i < b+1; i++){
                if(b % i == 0)
                    b1 = b1 + 1;
            }
            int[] factors = new int[a1];
            int[] factors1 = new int[b1];
            int x =0;
            for(int i = 1;i < a+1; i++){
                if(a%i==0){
                    factors[x] = i;
                    x = x + 1;
                }
            }
            x = 0;
            for(int i = 1;i < b+1; i++){
                if(b%i==0){
                    factors1[x] = i;
                    x = x + 1;
                }
            }
            for(int i = 0; i < a1; i++){
                for(int y = 0; y < b1; y++){
                    System.out.print(factors[i]);
                    System.out.print(" ");
                    System.out.print(factors1[y]);
                    System.out.println();
                }
            }
        }
    }
}