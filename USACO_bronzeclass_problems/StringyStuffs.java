package USACO_bronzeclass_problems;

import java.util.*;

public class StringyStuffs {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int num = scan.nextInt();
            String[] inputs = new String[num];
            String[] inputs1 = new String[num];
            int[] move1 = new int[num];
            int[] move2 = new int[num];
            for(int i = 0; i < num; i++){
                move1[i] = scan.nextInt();
                move2[i] = scan.nextInt();
                inputs[i] = scan.next();
            }
            for(int i = 0; i < num; i++){
                inputs1[i] = inputs[i];
                for(int j = 0; j < move2[i]; j++ ){
                    inputs1[i] = inputs1[i].substring(move1[i],inputs1[i].length()) + inputs1[i];
                }
                System.out.println(inputs1[i]);
            }
        }    
    }
}