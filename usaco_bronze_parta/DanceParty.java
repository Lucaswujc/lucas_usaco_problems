package usaco_bronze_parta;

import java.util.Scanner;


public class DanceParty {
    public static void main(String[] args) {
        // we will recieve a list of (x,y) and then
        // we will find out which pair of (x,y) has the greatest (xi - xj)^2 + (yi-yj)^2

        // parse the input
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[][] points  = new int[N][2];
        //iterativelyl read lines and get the xy
        for(int line =0 ; line < N ; line++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            points[line][0] = x;
            points[line][1]= y;
        }
        int[][] distanceArray = new int[N*(N-1)/2][3];
        int idx = 0;
        for (int i = 0 ; i < N; i ++){
            for(int j = i+1; j < N; j++){
                int D = (int) Math.pow((points[j][0]-points[i][0]),2) + 
                (int) Math.pow((points[j][1]- points[j][i]),2);
                distanceArray[idx][0] = i;
                distanceArray[idx][1] = j;
                distanceArray[idx][2] = D;
                idx++;
            }
        }

        //iterate through the distance array to find max 
        int maxi =-1,maxj=-1,maxd = Integer.MIN_VALUE;
        for(idx = 0; idx < distanceArray.length; idx++){
            if (distanceArray[idx][2]>maxd){
                maxi = distanceArray[idx][0];
                maxj = distanceArray[idx][1];
                maxd = distanceArray[idx][2];
            }
        
        }
        scan.close();
    }

}
