package USACOSilver;
import java.io.*;
import java.util.*;
public class AngryCows {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] haybales = new int[n];
        for(int i = 0; i < n; i++ ){
            haybales[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(haybales);
        out.close();
        br.close();
    }
}