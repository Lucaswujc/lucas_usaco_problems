package USACOGuide;

import java.io.*;
import java.util.*;
public class hps {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int[] hooves = new int[n + 1];
		int[] paper = new int[n + 1];
		int[] scissors = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			hooves[i] += hooves[i - 1];
			paper[i] += paper[i - 1];
			scissors[i] += scissors[i - 1];
            st = new StringTokenizer(br.readLine());
			char action = st.nextToken().charAt(0);
			if (action == 'H') {
				paper[i]++;
			} else if (action == 'P') {
				scissors[i]++;
			} else if (action == 'S') {
				hooves[i]++;
			}
		}
        int max = 0;
        for(int i = 1; i <= n ;i++){
            int before = Math.max(scissors[i], hooves[i]);
            before = Math.max(paper[i], before);
            int after = Math.max(scissors[n] - scissors[i], hooves[n] - hooves[i]);
            after = Math.max(paper[n] - paper[i], after);
            max = Math.max(max, after+before);
        }
        out.println(max);
        out.close();
        br.close();
    }
}