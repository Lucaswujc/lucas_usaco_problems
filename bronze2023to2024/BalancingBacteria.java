package bronze2023to2024;
import java.io.*;
import java.util.*;
public class BalancingBacteria {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] bacteria = new long[n];
        long[] current = new long[n];
        for (int i = 0; i < n; i++) {
            bacteria[i] = scan.nextLong();
            current[i] = 0;
        }
        long counter = 0;
        for (long i = 0; i < n; i++){
            counter = counter + Math.abs(bacteria[(int) i] - current[(int) i]);
            if (bacteria[(int) i] > current[(int) i]){
                long diff = Math.abs(bacteria[(int) i] - current[(int) i]);
                for (long j = i; j < n; j++){
                    current[(int) j] = current[(int) j] + diff * (j-i+1);
                }
            } 
            else if (bacteria[(int) i] < current[(int) i]){
                long diff = Math.abs(bacteria[(int) i] - current[(int) i]);
                for (long j = i; j < n; j++){
                    current[(int) j] = current[(int) j] - diff * (j-i+1);
                }
            }
        
        }
        out.println(counter);
        out.close();
        scan.close();
    }
}
