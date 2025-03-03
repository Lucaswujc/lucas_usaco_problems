import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class CountingDivisors {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        StringBuilder print = new StringBuilder();
        while(t > 0){
            int n = Integer.parseInt(read.readLine());
            int ans = 1;
            for(int i = 2; i*i <= n; i++){
                int count = 0;
                while (n % i == 0) {
                    n /= i;
                    count++;
                }
                ans *= (count+1);
            }
            if(n != 1){
                ans *= 2;
            }
            print.append(ans).append('\n');
            t--;
        }
        System.out.println(print);
    }
}

