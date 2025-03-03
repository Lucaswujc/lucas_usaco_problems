package USACOGuide;
import java.io.*;
import java.util.*;
public class paintbarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n  = Integer.parseInt(st.nextToken());
        int k  = Integer.parseInt(st.nextToken());
        int[][] barn = new int[200][200];
        //Silver
            //dad solution (row by row) 
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int j = x1; j < x2; j++){
                barn[j][y1]++;
                barn[j][y2]--;
            }
        }
        int ans = 0;
        for(int i =0; i < 10000; i++){
            int prefix = 0;
            for(int j =0; j < 10000; j++){
                prefix += barn[i][j];
                if(prefix == k){
                    ans++;
                }
            }
        }
            // official solution (2d prefix sums)
            // for(int i = 0; i < n; i++){
            //     st = new StringTokenizer(br.readLine());
            //     int x1 = Integer.parseInt(st.nextToken());
            //     int y1 = Integer.parseInt(st.nextToken());
            //     int x2 = Integer.parseInt(st.nextToken());
            //     int y2 = Integer.parseInt(st.nextToken());
            //     barn[x1][y1]++;
            //     barn[x2][y2]++;
            //     barn[x1][y2]--;
            //     barn[x2][y1]--;
            // }
            // int ans = 0;
            // for (int x = 0; x < 10000; x++) {
            // 	for (int y = 0; y < 10000; y++) {
            // 		if (x > 0) barn[x][y] += barn[x - 1][y];
            // 		if (y > 0) barn[x][y] += barn[x][y - 1];
            // 		if (x > 0 && y > 0) barn[x][y] -= barn[x - 1][y - 1];
            // 		if (barn[x][y] == k) { ans++; }
            // 	}
            // }
        out.println(ans); 
        out.close();
        br.close();
    }
}