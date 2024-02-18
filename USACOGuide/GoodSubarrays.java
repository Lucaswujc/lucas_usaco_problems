package USACOGuide;

import java.util.*;
public class GoodSubarrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int[] arr = new int[n+1];
            String s = scan.next();
            for(int i = 1; i <= n ; i++){
                int a = Integer.parseInt(s.substring(i-1,i));
                arr[i] = arr[i-1] + a;
            }
            long ans = 0;
            Map<Integer, Long> sums = new HashMap<>();
            for(int i = 0; i <= n; i++){
                int val = arr[i] - i;
                if (sums.containsKey(val)) {
                    sums.put(val, sums.get(val)+1);
                } 
                else{
                    sums.put(val, (long) 1);
                }
            }
            for(long i : sums.values()){
                ans += (i * (i-1))/2;
            }
            System.out.println(ans);
            t--;
        }   
        scan.close();
    }
}