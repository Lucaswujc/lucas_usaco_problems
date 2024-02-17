package USACOGuide;

import java.io.*;
import java.util.*;
public class SubarrayDivisibility {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n  = scan.nextInt();
        long prefix = 0;
        long[] pmod = new long[n];
        pmod[0] = 1;
        for(int i = 0; i < n ; i++){
            int a  = scan.nextInt();
            prefix += a;
            pmod[((int)(prefix % n) + n) % n]++;
        }
        long ans = 0;
        for(int i = 0; i < n ;i++){
            ans = ans + (pmod[i] * (pmod[i] - 1))/2;
        }
        System.out.println(ans);
        scan.close();
    }
}