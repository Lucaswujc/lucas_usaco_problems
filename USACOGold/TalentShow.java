package USACOGold;
import java.io.*;
import java.util.*;
public class TalentShow {
    static int n;
    static int w;
    static int[] weights;
    static int[] talents;
    static long[] dp = new long[1001];
    static boolean check(int y){
        for (int i = 0; i <= w; i++) {
            dp[i] = Long.MIN_VALUE;
          }
          dp[0] = 0;
        
          for (int j = 0; j < n; j++) {
            long value = 1000 * (long) talents[j] - y* (long) weights[j];
            int inc = weights[j];
            for (int k = w; k >= 0; k--) {
              int k1 = Math.min(w, k + inc);
              if (dp[k] != Long.MIN_VALUE) {
                if (dp[k1] < dp[k] + value) {
                  dp[k1] = dp[k] + value;
                }
              }
            }
          }
        
          return dp[w] >= 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("talent.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        weights = new int[n];
        talents = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            talents[i] = Integer.parseInt(st.nextToken());
        }
        int lo = 0;
        int hi = 1000*250*1000+1;
        while(hi > lo + 1){
            int mid = (lo + hi)/2;
            if(check(mid)){
                lo = mid;
            }
            else{
                hi = mid;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
        pw.println(lo);
        pw.close();
        br.close();
    }
}