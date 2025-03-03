package USACOGuide;
import java.io.*;
import java.util.*;
public class lazy {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] field = new int[2*n-1][2*n-1];
        for(int i = 0; i < 2*n-1; i++){
            Arrays.fill(field[i], -1);
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				field[i + j][n- i + j -1] = Integer.parseInt(st.nextToken());
			}
		}
        int[][] prefix = new int[2*n][2*n];
        for (int i = 0; i < 2*n-1; i++) {
			for (int j = 0; j < 2*n-1; j++) {
				int val = Math.max(field[i][j], 0);
				prefix[i + 1][j + 1] = (val + prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j]);
			}
		}
        int max = 0;
		for (int i = k; i < n*2-1 - k; i++) {
			for (int j = k; j < n*2-1- k; j++) {
				if (field[i][j] == -1) {
					continue; 
				}
				max = Math.max(max, prefix[i + k + 1][j + k + 1] - prefix[i + k + 1][j - k] - prefix[i - k][j + k + 1] + prefix[i - k][j - k]);
			}
		}
        if( k >= n){
            max = prefix[2*n-1][2*n-1];
        }
        out.println(max);
        out.close();
        br.close();
        scan.close();
    }
}