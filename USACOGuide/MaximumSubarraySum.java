package USACOGuide;
import java.util.*;
public class MaximumSubarraySum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n =  scan.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n ;i++){
            arr[i] = scan.nextInt();
        }
        long max = arr[0];
		long prefix = 0;
		long min = 0;
		for (int i = 0; i < n; i++) {
			prefix += arr[i];
            max = Math.max(max, prefix-min);
            min = Math.min(min, prefix);
		}
        
        // Kadane's Algorithm
        // long currentSum = arr[0];
		// long max = arr[0];
		// for (int i = 1; i < n; i++) {
		// 	/*continue the subarray sum or start a new
		// 	 * subarray sum beginning at the current element.
		// 	 */
		// 	currentSum = Math.max(currentSum +arr[i], arr[i]);
		// 	// store the maximum subarray sum at each iteration.
		// 	max = Math.max(max, currentSum);
		// }

        System.out.println(max);
        scan.close();
    }
}