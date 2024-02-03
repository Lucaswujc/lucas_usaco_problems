package bronze2020to2021;

import java.util.*;

public class StuckInARut {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] cows = new int[n][3];
        //north is 1, east is 0
        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        for(int i = 0 ; i <n ;i++){
            char s = scan.next().charAt(0);
            if(s == 'N'){
                cows[i][0] = 1;
            }
            else{
                cows[i][0] = 0;
            }
            cows[i][1] = scan.nextInt();
            cows[i][2] = scan.nextInt();
        }
        Boolean[] status = new Boolean[n];
        ArrayList<int[] > grazed = new ArrayList<int[]>();
        //false = no infinity/stop, true for infinity, null for continue
        while(true){
            Boolean[] grazing = new Boolean[n];
            Arrays.fill(grazing, false);
            for(int i = 0; i < n; i++){
                if(status[i] == null ){
                    
                    Boolean stop = false;
                    for(int j = 0; j < grazed.size(); j++){
                        int[] coords = grazed.get(j);
                        //check for if current cell has been eaten
                        if(coords[0] == cows[i][1] && coords[1] == cows[i][2]){
                            stop = true;
                            break;
                        }
                    }
                    if(stop){
                        //current cell has been eaten
                        status[i] = false;
                        continue;
                    }
                    else{
                        //move
                        grazing[i] = true;
                        continue;
                    }
                }
            }
            //infinity chceck
            Boolean north = cows[0][0] == 1 ? true : false;
            // cows will graze if possible
            for(int i = 0 ; i < n; i++){
                if(grazing[i]){
                    if(north){
                        int[] cellgrazed= new int[2];
                        cellgrazed[0] = cows[i][1];
                        cellgrazed[1] = cows[i][2];
                        grazed.add(cellgrazed);
                        cows[i][2]++;
                        ans[i]++;
                    }
                    else{
                        int[] cellgrazed= new int[2];
                        cellgrazed[0] = cows[i][1];
                        cellgrazed[1] = cows[i][2];
                        grazed.add(cellgrazed);
                        cows[i][1]++;
                        ans[i]++;
                    }
                }
            }
            Boolean breakout = true;
            for(int i =0; i < n; i++){
                if(status[i] == null){
                    breakout = false;
                    break;
                }
            }
            if(breakout){
                break;
            }
        }
        for(int i = 0; i <n ; i++){
            if(status[i] == true){
                System.out.println("Infinity");
            }
            else{
                System.out.println( ans[i]);
            }
        }
        scan.close();
    }
}
