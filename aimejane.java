import java.util.*;
public class aimejane {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] pyramid = new int[5][5];
        for(int i = 0 ;i < 5; i++){
            Arrays.fill(pyramid[i], 0);
        }
        for(int i = 1; i <= 15; i++){
            for(int j = 1; j <= 15; j++){
                for(int k = 1; k <= 15; k++){
                    for(int l = 1; l <= 15; l++){
                        boolean[] used = new boolean[15];
                        for(int a = 0 ;a < 5; a++){
                            Arrays.fill(pyramid[a], 0);
                        }
                        pyramid[4][3] = 3;
                        if( i== 1 && j ==2 && k ==4){
                            int a = 0;
                        }
                        Arrays.fill(used, false);
                        used[2] = true;
                        if(((i == j || j ==k) || (l ==k || j == l)) || (i ==l || i == k)){
                            continue;
                        }
                        if(i == 3 || j ==3 || k == 3 || l == 3){
                            continue;
                        }
                        used[i-1] = true;
                        used[j-1] = true;
                        used[k-1]= true;
                        used[l-1] = true;
                        pyramid[4][0] = i;
                        pyramid[4][1] = j;
                        pyramid[4][2] = k;
                        pyramid[4][4] = l;
                        boolean work = true;
                        for(int m = 0; m < 4; m++){
                            int a = Math.abs(pyramid[4][m] - pyramid[4][m+1]);
                            pyramid[3][m] = a;
                            if(used[a-1]){
                                work = false;
                                break;
                            }
                            used[a-1] = true;
                        }
                        if(!work){
                            continue;
                        }
                        for(int m = 0; m < 3; m++){
                            int a = Math.abs(pyramid[3][m] - pyramid[3][m+1]);
                            pyramid[2][m] = a;
                            if(used[a-1]){
                                work = false;
                                break;
                            }
                            used[a-1] = true;
                        }
                        if(!work){
                            continue;
                        }
                        for(int m = 0; m < 2; m++){
                            int a = Math.abs(pyramid[2][m] - pyramid[2][m+1]);
                            pyramid[1][m] = a;
                            if(used[a-1]){
                                work = false;
                                break;
                            }
                            used[a-1] = true;
                        }
                        if(!work){
                            continue;
                        }
                        int a = Math.abs(pyramid[1][0] - pyramid[1][1]);
                        pyramid[0][0] = a;
                        if(used[a-1]){
                            work = false;
                            break;
                        }
                        used[a-1] = true;
                        if(work){
                            for(int r = 0; r < 5; r++){
                                for(int c = 0; c < 5; c++){
                                    System.out.print(pyramid[r][c]);
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
        scan.close();
    }
}