package USACO_bronze_problems;
import java.util.*;

public class harvestingcrops {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int[][] crops = new int[2][2];
        int[][] crops2 = new int[2][2];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                crops[i][j] = scan.nextInt();
            }
        }
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                crops2[i][j] = scan.nextInt();
            }
        }
        int x1 = crops[0][0];
        int y1= crops[0][1];
        int x2 = crops[1][0];
        int y2 = crops[1][1];
        int a1 = crops2[0][0];
        int b1= crops2[0][1];
        int a2 = crops2[1][0];
        int b2 = crops2[1][1];
        int length = x2 - x1;
        int width = y2- y1;
        int area = length*width;
        int[][] field = new int[width][length];
        int i1 = 0;
        for(int i = y1; i < y2; i++){
            int j1 = 0;
            for(int j = x1; j < x2; j++){
                if(((a1<=i) && (i <=a2)) && ((b1<=j)&&(j<=b2))){
                    field[i1][j1] = 1;
                }
                j1++;
            }
            i1++;
        }
        int count = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j <length; j++){
                if(field[i][j] == 1){
                    count++;
                }
            }
        }
        if(((a2 >= x2) && (b2 >= y2)) && ((a1 <= x1) && (b1 <= y1))){
            area = 0;
        }
        else if(count == area){
            area = length* width;
        }
        else{
            area= count;
        }
        System.out.println(area);
        scan.close();
    }
}