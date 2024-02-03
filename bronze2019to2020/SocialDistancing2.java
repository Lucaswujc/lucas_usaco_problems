package bronze2019to2020;

import java.util.*;
public class SocialDistancing2 {
    public static void sortbyColumn(int arr[][]){ 
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0],b[0])); 
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] cows = new int[n][2];
        for(int i = 0 ; i < n; i++){
            cows[i][0] = scan.nextInt();
            cows[i][1] = scan.nextInt();
        }
        sortbyColumn(cows);
        int radius = Integer.MAX_VALUE;
        for(int i = 0; i < n-1; i++){
            if(cows[i][1] != cows[i+1][1]){
                if(Math.abs(cows[i+1][0]-cows[i][0]) < radius){
                    radius = Math.abs(cows[i+1][0]-cows[i][0])-1;
                }
            }
        }
        int idx = 0;
        int clusters = 0;
        while(idx < n){
            if(cows[idx][1] == 0){
                idx++;
                continue;
            }
            else{
                if(idx == n-1){
                    if(cows[idx-1][1] == 0){
                        clusters++;
                    }
                    else{
                        clusters++;
                    }
                    break;
                }
                
                if(cows[idx+1][1] ==1){
                    if(cows[idx+1][0]- cows[idx][0] > radius){
                        clusters++;
                    }
                }
                else{
                    clusters++;
                }
                idx++;
            }
        }

        for(int i = 0; i < n; i++){
            System.out.println(cows[i][0] + " " + cows[i][1]);
        }
        System.out.println(clusters);
        scan.close();
    }
}