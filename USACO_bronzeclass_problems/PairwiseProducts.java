package USACO_bronzeclass_problems;

import java.util.*;

public class PairwiseProducts {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            String a = scan.next();
            String b = scan.next();
            int[] sums = new int[a.length() *b.length()];
            int counter = 0;
            for(int i = 0; i <a.length(); i++){
                for(int j =0; j < b.length(); j++){
                    int a1 = Integer.parseInt(a.substring(i,i+1));
                    int b1 = Integer.parseInt(b.substring(j,j+1));
                    sums[counter] = a1*b1;
                    counter = counter + 1;
                }
            }
            int win = 0;
            for(int i =0; i < a.length()*b.length(); i++){
                win = win + sums[i];
            }
            System.out.println(win);
        }
    }
}