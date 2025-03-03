package USACOGuide;
import java.util.*;
import java.io.*;
public class RestStops {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] c = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }
        
        Boolean[] good = new Boolean[n];
		int maxTastiness = 0;
        Arrays.fill(good, false);
		for (int i = n - 1; i >= 0; i--) {
			if (c[i] > maxTastiness) {
				good[i] = true;
				maxTastiness = c[i];
			}
		}
        int prevStopPos = 0;
		long ans = 0;
		for (int i = 0; i < n; i++) {
			if (good[i]) {
				long travelDist = x[i] - prevStopPos;
				long fTime = travelDist * f;
				long bTime = travelDist * b;
				long restTime = fTime - bTime;
				ans += restTime * c[i];
				prevStopPos = x[i];
			}
		}
        l = l-1;
        out.println(ans);
        out.close();
        br.close();
    }
}