package bronze2023to2024;
import java.util.*;
import java.io.*;
public class FarmerJohnActuallyFarms {
    static long ceilDiv(long a, long b){
        return (a + b - 1)/ b;
    }
    public static void main(String[] args) throws IOException{
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for(int a = 0; a < t; a++){
            int n = scan.nextInt();
            int[] heights = new int[n];
            int[] growths = new int[n];
            int[] want = new int[n];
            for(int i = 0; i < n; i++){
                heights[i] = scan.nextInt();
            }
            for(int i = 0; i < n; i++){
                growths[i] = scan.nextInt();
            }
            for(int i = 0; i < n; i++){
                want[i] = scan.nextInt();
            }
            long count = 0;
            int[] copy = Arrays.copyOf(want, n);
            int[] ordered = new int[n];
            for(int i = 0; i < n; i++){
                ordered[i] = i;
            }
            for(int i =0 ; i <n ;i++){
                for(int j =1; j < (n-i); j++){
                    if(copy[j-1] > copy[j]){
                        int temp = copy[j-1];
                        copy[j-1] = copy[j];
                        copy[j] = temp;
                        temp = ordered[j-1];
                        ordered[j-1] = ordered[j];
                        ordered[j] = temp;
                    }
                }
            }
            for(int i = 0; i <n -1; i++){
                int small = ordered[i];
                int large = ordered[i+1];
                if(heights[small] >= heights[large] && growths[large]>growths[small]){
                    count = Math.max(ceilDiv(heights[small] - heights[large] + 1, growths[large] - growths[small]), count);
                }            
            }
            long[] afterheights = new long[n];
            for(int i = 0; i < n; i++){
                afterheights[i] = heights[i] + growths[i] * count;
            }
            
            for(int i =0 ; i < n-1; i++){
                if(count == -1){
                    break;
                }
                int small = ordered[i];
                int large = ordered[i+1];
                if(afterheights[small] >= afterheights[large]){
                    count = -1;
                }
            }
            out.println(count);
        }
        scan.close();
        out.close();
    }
}