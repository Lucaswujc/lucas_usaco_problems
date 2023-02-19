package usaco_2022_dec;

import java.util.Arrays;
import java.util.Scanner;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1251
 * 
 */
public class BronzeProblem1 {
   
    public static void main(String[] args){
        int N = 0;
        int[] c = null ;
        try(Scanner scanner = new Scanner(System.in)){
            N = scanner.nextInt();
            c = new int[N];
            
            for(int i = 0 ; i < N; i ++){
                c[i] = scanner.nextInt();
            }
        }
        Arrays.sort(c);
        long profit = 0l ;
        int tuition = 0;
        // iterate from the largest number to the smallest
        // each iteration will increase the # of cows willing to attend
        // however, the money made from # of cows may decrease.. 
        for(int i = c.length-1 ; i >0 ;i--){
            long p2 = (long)(N-i) * (long)c[i];
            if (p2 >= profit){
                profit = p2;
                tuition = c[i];
            }
        }
        System.out.println(String.format("tuition = %d, profit = %d",tuition,profit));
    }
    
}
