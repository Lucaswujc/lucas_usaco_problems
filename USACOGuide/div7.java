package USACOGuide;
import java.io.*;
import java.util.*;
public class div7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        long[] prefix = new long[n+1];
        for(int i =1 ; i<= n; i++){
            long a = Integer.parseInt(br.readLine());
            prefix[i] = prefix[i-1] + a;
        }
        int count = 0;
        for(int i = 1; i <= n ;i++){
            for(int j = i; j <= n; j++){
                long a = prefix[j] - prefix[i-1];
                if(a % 7 == 0 && j-i+1 > count){
                    count = j-i+1;
                }
            }
        }
        out.println(count);
        out.close();
        br.close();
    }
}