package USACOGuide;

import java.util.*;
public class SubarraySumsII {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = scan.nextInt();;
        }
        long prefixSum = 0;
		long ans = 0;
		Map<Long, Integer> sums = new HashMap<>();
		sums.put((long)0, 1);
        for(int a: arr){
            prefixSum += a;
            if(sums.containsKey(prefixSum - x)){
                ans += sums.get(prefixSum-x);
            }
            if (sums.containsKey(prefixSum)) {
				sums.put(prefixSum, sums.get(prefixSum)+1);
			} 
            else {
				sums.put(prefixSum, 1);
			}
        }
        System.out.println(ans);
        scan.close();
    }
}