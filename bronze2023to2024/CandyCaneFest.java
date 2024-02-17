package bronze2023to2024;

import java.io.*;
import java.util.*;
public class CandyCaneFest {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long m = scan.nextLong();
        long[] cows = new long[(int) n];
        for(long i = 0 ;i < n;i++){
            cows[(int) i] = scan.nextLong();
        }
        for(long i = 0 ;i < m;i++){
            long candy = scan.nextLong();
            long curheight = 0;
            for(long j = 0; j < n; j++){
                if(cows[(int) j] > curheight){
                    long temp = curheight;
                    curheight += cows[(int) j] > candy ? candy - curheight : cows[(int) j] - curheight;
                    cows[(int) j] += cows[(int) j] > candy ? candy - temp: cows[(int) j] - temp;
                }
                if(curheight >= candy){
                    break;
                }
            }
        }
        for(long i = 0; i <n; i++){
            out.println(cows[(int) i]);
        }
        out.close();
        scan.close();
    }
}
