package USACO_bronze_problems;

import java.util.*;
public class BovineBones 
{
    static int sums(int d1, int d2, int d3){
        int[] sums1 = new int[d1+d2+d3-2];
        //3 nested loops for three inputs
        for(int i = 1; i < d1 + 1; i++){
            for(int j = 1; j < d2 + 1; j++){
                for(int k = 1; k < d3+ 1; k++){
                    sums1[i+j+k- 3] = sums1[i+j+k-3] + 1;
                }
            }
        }
        int large = 0;
        int idx = 0;
        //starts with small number, if its greater then increase
        for(int i = 0; i < d1+d2+d3-2; i++){
            if(sums1[i] == large){
                if(i < idx){
                    large = sums1[i];
                    idx = i;
                }
            }
            if(sums1[i] > large){
                large = sums1[i];
                idx = i;
            }
            
        }
        idx = idx + 3;
        return idx;
    }
    
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int d1 = scan.nextInt();
            int d2 = scan.nextInt();
            int d3 = scan.nextInt();
            System.out.println(sums(d1,d2,d3));
        }
    }
}