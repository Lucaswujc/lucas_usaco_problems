package bronze2020to2021;

import java.util.*;
public class AcowdemiaII {
    static String[] is_sorted(String cow1, String cow2, String[] papers){
        String[] ret = new String[2];
        int i = 0; 
        while(!papers[i].equals(cow1) && !papers[i].equals(cow2)){
            i++;
        }
        ret[1] = papers[i];
        i++;
        while(true){
            if(papers[i].compareTo(papers[i-1]) < 0 ){
                ret[0] = "false";
                return ret;
            }
            if(papers[i].equals(cow1) || papers[i].equals(cow2)){
                ret[0] = "true";
                return ret;
            }
            i++;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int n = scan.nextInt();
        String[] names = new String[n];
        for(int i = 0; i < n;i++){
            names[i] = scan.next();
        }
        String[][] papers = new String[k][n];
        for(int i = 0; i < k; i++){
            for(int j = 0; j < n; j++){
                papers[i][j] = scan.next();
            }
        }
        char[][] solution = new char[n][n];
        for(int i = 0; i < n; i++){
            solution[i][i] = 'B';
        }
        for(int i = 0; i < n; i++){
            for(int j = i+1; j <n; j++){
                Boolean known = false;
                for(int h = 0; h < k; h++){
                    String[] sortedArr = is_sorted(names[i], names[j], papers[h]);
                    Boolean sorted = sortedArr[0].equals("true") ? true : false;
                    if (!sorted){
                        if(sortedArr[1].equals(names[i])){
                            solution[i][j] = '0';
                            solution[j][i] = '1';
                            known = true;
                        }
                        else{
                            solution[i][j] = '1';
                            solution[j][i] = '0';
                            known = true;
                        }
                    }

                    if(!known){
                        solution[i][j] = '?';
                        solution[j][i] = '?';
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int  j =0 ; j < n; j++){
                System.out.print(solution[i][j]);
            }
            System.out.println();
        }
        scan.close();
    }
}
