package bronze2020to2021;

import java.util.*;

public class ComfortableCows {
    public static Boolean comfortable(int x, int y, int[][] adjacentcows){
        if(adjacentcows[x][y] ==3){
            return true;
        }
        return false;
    }
    public static int countComfortable(int x, int y, int[] directionsx, int[] directionsy, int[][] adjacentcows, Boolean[][] pasture){
        int counter = 0;
        for(int i = 0 ; i < directionsx.length; i++){
            if((x + directionsx[i] >= 1000 || x + directionsx[i] < 0) || (y + directionsy[i] >= 1000 || y + directionsy[i] < 0 )){
                continue;
            }
            if(comfortable(x + directionsx[i], y + directionsy[i], adjacentcows) && pasture[x + directionsx[i]][y+directionsy[i]]){
                counter++;
            }
        }
        return counter;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Boolean[][] pasture = new Boolean[1000][1000];
        int[][] adjacentcows = new int[1000][1000];
        int[] directionsx = new int[] {1, -1, 0, 0, 0};
        int[] directionsy = new int[] {0, 0, 1, -1, 0};
        for(int i = 0 ; i < 1000; i++){
            for(int j = 0; j < 1000; j++){
                pasture[i][j] = false;
            }
        }
        int ans = 0;
        for(int i = 0; i < n ; i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            int beforeNewCow = countComfortable(x, y, directionsx, directionsy, adjacentcows, pasture);
            pasture[x][y] = true;
            for(int j = 0; j < 4; j++){
                if((x + directionsx[j] >= 1000 || x + directionsx[j] < 0) || (y + directionsy[j] >= 1000 || y + directionsy[j] < 0 )){
                    continue;
                }
                adjacentcows[x+directionsx[j]][y+directionsy[j]]++;
            }
            int afterNewCow = countComfortable(x, y, directionsx, directionsy, adjacentcows, pasture);
            ans = ans + afterNewCow-beforeNewCow;
            System.out.println(afterNewCow-beforeNewCow);

        }

        scan.close();
    }
}