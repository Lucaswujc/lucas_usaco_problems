package USACOGuide;
import java.util.*;
public class StickLengths {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] sticks = new long[n];
        for(int i = 0; i < n; i++){
            sticks[i] = scan.nextLong();
        }
        Arrays.sort(sticks);
        long med = sticks[n/2];
        long ans = 0;
        for(int i  =0 ;i < n; i++){
            ans += Math.abs(med-sticks[i]);
        }
        System.out.println(ans);
        scan.close();
    }
}