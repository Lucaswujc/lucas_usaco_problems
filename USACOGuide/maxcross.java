package USACOGuide;
import java.util.*;
import java.io.*;
public class maxcross {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        // int n = scan.nextInt();
        // int k = scan.nextInt();
        // int b = scan.nextInt();
        int[] stoplights = new int[n+1];
        Arrays.fill(stoplights, 1);
        stoplights[0] = 0;
        for(int i = 0; i < b; i++){
            st = new StringTokenizer(br.readLine());
            int broken = Integer.parseInt(st.nextToken());
            // int broken = scan.nextInt();
            stoplights[broken] = 0;
        }
        int[] prefix = new int[n+1];
        for(int i = 1; i <= n ; i++){
            prefix[i] = stoplights[i] + prefix[i-1];
        }
        int min = Integer.MAX_VALUE;
        for(int i = k; i <= n; i++){
            int sum = prefix[i] - prefix[i-k];
            min = Math.min(min, k-sum);
        }
        out.println(min);
        out.close();
        br.close();
        scan.close();
    }
}