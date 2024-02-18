package bronze2020to2021;

import java.util.*;
public class AcowdemiaIII {
    static int countn(int i, int j, int n, int m, char[][] pasture, int[] dirx, int[] diry){
        if(pasture[i][j] !='G'){
            return -1;
        }
        int neighbors = 0;
        for(int k = 0;k < 4; k++){
            if((i + dirx[k] < 0 || i + dirx[k] >= n) || (j + diry[k] < 0 || j + diry[k] >= m)){
                continue;
            }
            if(pasture[i+dirx[k]][j+diry[k]] == 'C'){
                neighbors++;
            }
        }
        return neighbors; 
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        char[][] pasture = new char[n][m];
        for(int i = 0; i < n; i++){
            String s = scan.next();
            for(int j = 0; j <m; j++){
                pasture[i][j] = s.charAt(j);
            }
        }
        int counter = 0;
        int[] dirx = new int[] {-1, 1, 0, 0};
        int[] diry = new int[] {0, 0, 1, -1};
        for(int i =0 ; i < n; i++) {
            for(int j = 0; j < m; j++){
                if(pasture[i][j] != 'G'){
                    continue;
                }
                int neighbors = 0;
                ArrayList<Integer> adjacents = new ArrayList<>();
                for(int k = 0;k < 4; k++){
                    if((i + dirx[k] < 0 || i + dirx[k] >= n) || (j + diry[k] < 0 || j + diry[k] >= m)){
                        continue;
                    }
                    if(pasture[i+dirx[k]][j+diry[k]] == 'C'){
                        neighbors++;
                        adjacents.add(k);
                    }
                }
                if(neighbors < 2){
                    continue;
                }
                counter++;
                if(neighbors > 2){
                    continue;
                }
                int doublecount1 = 0;
                int doublecount2 = 0;
                if(adjacents.get(0) == 1 && adjacents.get(1) ==2){
                    doublecount1 = i+1;
                    doublecount2 = j+1;
                }
                else if(adjacents.get(0) == 2 && adjacents.get(1) ==3){
                    doublecount1 = i+1;
                    doublecount2 = j+1;
                }
                if(countn(doublecount1, doublecount2, n, m, pasture, dirx, diry) == 2){
                    pasture[doublecount1][doublecount2] = '.';
                }

            }
        }
        System.out.println(counter);
        scan.close();
    }
}