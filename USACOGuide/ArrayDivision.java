package USACOGuide;
import java.util.*;
public class ArrayDivision {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k =scan.nextInt();
        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextLong();
        }
        long l = 1, r = Long.MAX_VALUE;
		while (l < r) {
			long mid = (l + r) / 2;
			int count = 0;
		    long current = 0;
		    for (long x : arr) {
                if (x > mid) { 
                    count = k +1;
                    break; 
                }

                if (current + x > mid) {
                    count++;
                    current = 0;
                }
                current += x;
            }
            if (current > 0) { 
                count++; 
            }
            if(count <= k){
				r = mid;
			} 
            else {
				l = mid + 1;
			}
		}
        System.out.println(l);
        scan.close();
    }
}