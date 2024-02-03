package bronze2019to2020;
import java.util.*;
public class SocialDistancingI {
    public static int smallestGap(int[] stalls){
        int small = Integer.MAX_VALUE;
        Boolean cowbefore = false;
        int previouscow = 0;
        for(int i = 0; i < stalls.length; i++){
            if(stalls[i] == 1){
                if(!cowbefore){
                    cowbefore = true;
                    previouscow = i;
                }
                else{
                    small = Math.min(small, i-previouscow);
                    previouscow = i;
                }
            }
        }
        return small;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.next();
        int[] stalls = new int[n];
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '1'){
                stalls[i] = 1;
            }
            else{
                stalls[i] = 0;
            }
        }
        int small = -1;
        for(int i = 0; i < n; i++){
            if(stalls[i] == 1){
                continue;
            }
            for(int j = i + 1; j < n; j++){
                if(stalls[j] == 1){
                    continue;
                }
                stalls[i] = 1;
                stalls[j] = 1;
                if(smallestGap(stalls) > small){
                    small = smallestGap(stalls);
                } 
                stalls[i] = 0;
                stalls[j] = 0;
            }
            while(i < n && stalls[i] != 1){
                i++;
            }
        } 
        System.out.println(small);
        scan.close();
    }
}

