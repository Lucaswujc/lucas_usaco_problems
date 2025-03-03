package USACOSilver;
import java.util.*;
public class MooRoute {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] times = new int[n+1];
        for(int i = 0; i < n; i++){
            times[i] = scan.nextInt();
        }
        String ans = "";
        int pos = 0;
        while(!(pos == 0 && times[pos] == 0)){
            while(pos < n && times[pos] > 1){
                ans += "R";
                times[pos]--;
                pos++;
            }
            while(pos > 0 && (times[pos-1] > 1 || times[pos] == 0)){
                ans += "L";
                pos--;
                times[pos]--;
                
            }
        }
        System.out.println(ans);
        scan.close();
    }
}