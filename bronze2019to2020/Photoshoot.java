package bronze2019to2020;

import java.util.*;

public class Photoshoot {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] bessieorder = new int[n-1];
        for(int i = 0 ; i < n-1; i++) {
            bessieorder[i] = scan.nextInt();
        }
        //intialize/scan
        int[] correctorder = new int[n];

        for(int i = 1; i <= n; i++){
            Boolean[] used = new Boolean[n];
            Arrays.fill(used, false);
            Boolean works = true;
            Arrays.fill(correctorder, 0);
            int b_idx = 0;
            int a_idx = 0;
            int current = i;
            int nextvalue = -1;
            //test integer
            while(true){
                //loop through to see if it works
                nextvalue = bessieorder[b_idx] - current;
                correctorder[a_idx] = current;
                correctorder[a_idx+1] = nextvalue;
                if((nextvalue <= 0 || nextvalue > n)){
                    works = false;
                    break;
                }
                used[current-1] = true;
                used[nextvalue-1] = true;
                a_idx++;
                b_idx++;
                current = nextvalue;
                if(b_idx == (n-1)){
                    break;
                } 
            }
            //test if everything passed
            for(int j = 0; j < used.length; j++){
                if(!used[j]){
                    //if false, breakout
                    works = false;
                    break;
                }
            }
            if(works){
                break;
            }
        }
        for(int i = 0; i < correctorder.length; i++){
            System.out.print(correctorder[i] +" ");
        }
        scan.close();
    }
}
