package USACO_bronze_problems;

import java.util.*;

public class TaskAssignments {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int cows = scan.nextInt();
        int tasks = scan.nextInt();
        int[] cowlist = new int[cows];
        int[][] tasklist = new int[tasks][2];
        for(int i = 0; i < tasks; i++){
            tasklist[i][0] = scan.nextInt();
            tasklist[i][1] = scan.nextInt();
        }

        for(int i = 0; i < tasks; i++){
            for(int j = tasklist[i][0]; j < cows; j= j+tasklist[i][1]){
                cowlist[j-1] = 1;
            }
        }
        int counter= 0;
        for(int i =0 ; i < cows; i++){
            if(cowlist[i] != 1){
                counter++;
            }
        }
        System.out.println(counter);
        scan.close();
    }
}