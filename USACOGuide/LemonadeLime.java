package USACOGuide;
import java.io.*;
import java.util.*;
public class LemonadeLime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Integer[] cows = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for(int  i =0 ;i < n ;i++){
            cows[i] = Integer.parseInt(st.nextToken());
        }
        int a = 0;
        Arrays.sort(cows, Collections.reverseOrder());
        int line = 0;
        for(int i = 0; i < n; i++){
            if(cows[i] >= line){
                a++;
                line++;
            }
        }
        out.println(a);
        br.close();
        out.close();  
    }
}