package USACOGuide;
import java.util.*;
public class SaltBae {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] steak = new int[n];
        for(int i = 0; i < n ; i++){
            steak[i] = scan.nextInt();
        }
        for(int i = 0; i < n-1; i++){
            if(steak[i] < steak[i+1]){
                steak[i] = Math.max(steak[i], steak[i+1] - m); 
            }
            else{
                steak[i+1] = Math.max(steak[i+1], steak[i] - m);
            }
        }
        for(int i = n-1; i > 0 ; i--){
            if(steak[i] < steak[i-1]){
                steak[i] = Math.max(steak[i], steak[i-1] - m); 
            }
            else{
                steak[i-1] = Math.max(steak[i-1], steak[i] - m);
            }
        }
        for(int i = 0; i < n ; i++){
            System.out.print(steak[i] + " ");
        }
        scan.close();
    }
}