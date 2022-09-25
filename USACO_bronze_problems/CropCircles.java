package USACO_bronze_problems;

import java.util.*;


public class CropCircles {
    static Boolean overlap(int x1, int x2, int y1, int y2, int r1, int r2){
        Boolean overlap = false;
        int distance = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
        int square = (r1 + r2)*(r1+r2);
        if(distance < square)           
            overlap = true;
        return overlap;
    }
    
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int num = scan.nextInt();
            int[] x = new int[num];
            int[] y = new int[num];
            int[] r = new int[num];
            int[] overlaps = new int[num];
            for(int i = 0; i< num; i++){
                x[i] = scan.nextInt();
                y[i] = scan.nextInt();
                r[i] = scan.nextInt();
            }
            for(int i = 0; i < num; i++){
                for(int j = 0; j < num; j++){
                    Boolean ans = false;
                    if(i == j)
                        ans = false;
                    else
                        ans = overlap(x[i], x[j], y[i], y[j], r[i], r[j]);
                    if(ans == true)
                        overlaps[i] = overlaps[i] + 1;
                }
            }
            for(int i = 0; i < num; i++){
                System.out.println(overlaps[i]);
            }
        }
    }
}