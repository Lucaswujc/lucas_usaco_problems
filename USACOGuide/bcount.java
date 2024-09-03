package USACOGuide;
import java.io.*;
import java.util.*;
public class bcount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[][] prefix = new int[3][n+1];
        for(int i = 1; i <= n ; i++){
            int a = Integer.parseInt(br.readLine());
            for(int j = 0; j < 3; j++) {
				prefix[j][i] = prefix[j][i-1];
			}
            prefix[a-1][i]++;
        }
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            out.print(prefix[0][r]-prefix[0][l-1] + " ");
            out.print(prefix[1][r]-prefix[1][l-1] + " ");
            out.print(prefix[2][r]-prefix[2][l-1]);
            out.println();
        } 
        out.close();
        br.close();
    }
}