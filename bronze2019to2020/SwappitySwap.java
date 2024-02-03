package bronze2019to2020;

import java.util.*;

public class SwappitySwap {
    public static int[] swap(int[] cowarray, int left, int right){
        while(left < right){
            int temp = cowarray[right];
            cowarray[right] = cowarray[left];
            cowarray[left] = temp;
            left++;
            right--;
        }
        return cowarray;
    }
    public static Boolean check(int[] cows){
        for(int i = 0; i < cows.length; i++){
            if(cows[i] != (i+1)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] cowarray = new int[n];
        for(int i = 0; i < n; i++){
            cowarray[i] = i + 1;
        }
        
        int left1 = scan.nextInt();
        int right1 = scan.nextInt();
        int left2 = scan.nextInt();
        int right2 = scan.nextInt();
        scan.close();
        // Solution 1: Keep on X times until all the cows are back at the original, then run k mod X times to get the final set
        // int counter = 0;
        // while(true){
        //     cowarray = swap(cowarray, left1-1, right1-1);
        //     cowarray = swap(cowarray, left2-1, right2-1);
        //     counter++;
        //     if(check(cowarray)){
        //         break;
        //     }
        // }
        // if(counter != k){
        //     for(int i = 0; i < k % counter; i++){
        //         cowarray = swap(cowarray, left1-1, right1-1);
        //         cowarray = swap(cowarray, left2-1, right2-1);
        //     }
        // }
        // for(int i = 0; i < n; i++){
        //     System.out.println(cowarray[i]);
        // }

        //Solution 2: FInd out how many cycles it takes for cow i to get back to its original spot, then run k % x times
        int[] correctorder = new int[n];
        for(int i = 0; i < n; i++){
            //counter for number of cycles
            int cyclecount = 0;
            //reset cow order
            for(int j = 0; j < n; j++){
                cowarray[j] = j + 1;
            }
            while(true){
                //iterate until cow i is back to original spot
                cowarray = swap(cowarray, left1-1, right1-1);
                cowarray = swap(cowarray, left2-1, right2-1);
                cyclecount++;
                if(cowarray[i] == i+1){
                    break;
                }
                
            }
            //reset array
            for(int j = 0; j < n; j++){
                cowarray[j] = j + 1;
            }
            //iterate k%cyclecount times
            for(int j = 0; j < k % cyclecount; j++){
                cowarray = swap(cowarray, left1-1, right1-1);
                cowarray = swap(cowarray, left2-1, right2-1);
            }
            ///store correct position
            for(int j = 0; j < n; j++){
                if(cowarray[j] == i+1){
                    correctorder[j] = i+1;
                }
            }
            
        }
        for(int i = 0; i < n; i++){
            System.out.println(correctorder[i]);
        }
    }
}
