package usaco_bronze_parta;
import java.util.*;
import java.io.*;
public class herding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        for(int i  =0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        int max = 0;
        int min = Integer.MAX_VALUE;
		if (cows[n-2] - cows[0] == n-2 && cows[n-1] - cows[n-2] > 2) {
			min = 2;
		} else if (cows[n-1] - cows[1] == n-2 && cows[1] - cows[0] > 2) {
			min = 2;
		} else {
			int farthestCow = 0;
			for (int i = 0; i < n; i++) {
				while (farthestCow + 1 < n && cows[farthestCow + 1] - cows[i] < n) {
					farthestCow++;
				}
				min = Math.min(min, n - (farthestCow - i + 1));
			}
		}
		int empty = 0;
		for (int i = 1; i < n; i++) { 
			empty += cows[i] - cows[i - 1] - 1; 
		}

		max = Math.max(empty - (cows[1] - cows[0] - 1), empty - (cows[n - 1] - cows[n - 2] - 1));
		out.println(min + "\n" + max);
        out.close();
        br.close();
    }
}