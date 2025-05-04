import java.util.*;
import java.io.*;
public class FruitFeast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("feast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        boolean[][] dp = new boolean[2][t+1];
        dp[0][0] = true;
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < t+1; i++) {
				if(!dp[j][i]) {
					continue;
				}
				if(i+a <= t) {
					dp[j][i+a] = true;
				}
				if(i+b <= t) {
					dp[j][i+b] = true;
				}
				if(j+1 < 2) {
					dp[j+1][i/2] = true;
				}
			}
		}
		int ans = t;
		while(!dp[1][ans]) {
			ans--;
		}
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        pw.println(ans);
        pw.close();
        br.close();
    }
}