package bronze2020to2021;

import java.util.*;
import java.util.Arrays;
public class JustStalling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Integer[] cows = new Integer[n];
        Integer[] stalls = new Integer[n];
        for(int i = 0; i < n; i++){
            cows[i] = scan.nextInt();
        }
        for(int i = 0; i < n; i++){
            stalls[i] = scan.nextInt();
        }
        Arrays.sort(cows, Collections.reverseOrder());
        Arrays.sort(stalls, Collections.reverseOrder());
        long answer = 1;
        long count = 0;
        for(int i =  0; i < n ;i++){
            long fits = 0;
            for(int j = 0; j <n ;j++){
                if(stalls[j] >= cows[i]){
                    fits++;
                }
                else{
                    break;
                }
            }
            answer =  answer * (fits-count);
            count++;
        }
        System.out.println(answer);
        scan.close();
    }
}
