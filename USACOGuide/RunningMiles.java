package USACOGuide;
import java.util.*;
public class RunningMiles {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0 ){
            int n = scan.nextInt();
            int[] b = new int[n];
            int[] prefix = new int[n];
            int[] suffix = new int[n];
            for(int i = 0; i < n ;i++){
                b[i] = scan.nextInt();
                prefix[i] = b[i] + i;
                suffix[i] = b[i] - i;
            }
            for (int i = 1; i < n; i++) {
                prefix[i] = Math.max(prefix[i], prefix[i - 1]);
            }
            for (int i = n - 2; i >= 0; i--) {
                suffix[i] = Math.max(suffix[i], suffix[i + 1]);
            }
            int ans = 0;
            for(int i = 1; i < n- 1; i++){
                int a = prefix[i-1] + b[i] + suffix[i+1];
                ans = Math.max(a, ans);
            }
            System.out.println(ans);
            t--;
        }
        scan.close();
    }
}