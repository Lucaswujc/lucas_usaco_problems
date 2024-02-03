package bronze2019to2020;

import java.util.*;
public class Triangles {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] points = new int[n][2];
        for(int i = 0; i < n; i++){
            points[i][0] = scan.nextInt();
            points[i][1] = scan.nextInt();
        }
        int max_area = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j <n; j++){
                if(i == j){
                    continue;
                }
                for(int k = 0; k < n; k++){
                    if(i == k || k == j){
                        continue;
                    }
                    if((points[i][0] == points[j][0]) && (points[j][1] == points[k][1])){
                        int new_max_area = Math.abs(points[i][1] - points[j][1]) * Math.abs(points[k][0] - points[j][0]);
                        if(new_max_area >max_area){
                            max_area = new_max_area;
                        }
                    }
                    if((points[i][0] == points[k][0]) && (points[k][1] == points[j][1])){
                        int new_max_area = Math.abs(points[i][1] - points[k][1]) * Math.abs(points[k][0] - points[j][0]);
                        if(new_max_area >max_area){
                            max_area = new_max_area;
                        }
                    }
                    if((points[i][1] == points[j][1]) && (points[j][0] == points[k][0])){
                        int new_max_area = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[k][1] - points[j][1]);
                        if(new_max_area >max_area){
                            max_area = new_max_area;
                        }
                    }
                    if((points[i][1] == points[k][1]) && (points[j][0] == points[k][0])){
                        int new_max_area = Math.abs(points[i][0] - points[k][0]) * Math.abs(points[k][1] - points[j][1]);
                        if(new_max_area >max_area){
                            max_area = new_max_area;
                        }
                    }
                }
            }
        }
        System.out.println(max_area);
        scan.close();
    }
}