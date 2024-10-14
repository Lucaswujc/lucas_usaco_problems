package USACOGuide;
import java.util.*;
import java.io.*;
public class CountingHaybales {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] haybales = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i  =0; i < n; i++){
            haybales[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(haybales);
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int leftest =  Arrays.binarySearch(haybales, l);
            if(leftest < 0){
                leftest = Math.abs(leftest+1);
            }
            int rightest =  Arrays.binarySearch(haybales, r);
            if(rightest < 0){
                rightest = Math.abs(rightest+2);
            }
            out.println(rightest-leftest+1);
        }
        out.close();
        br.close();
    }
}