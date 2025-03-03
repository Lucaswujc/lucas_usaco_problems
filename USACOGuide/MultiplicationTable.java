package USACOGuide;
import java.util.*;
public class MultiplicationTable  {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long low = 1; 
        long high = n * n;
        while (low < high) {
			long mid = (low + high) / 2;
			long count = 0;
            for (long i = 1; i <= n; i++) { 
                count += Math.min(n, mid / i); 
            }
			if (count >= (n * n + 1) / 2) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
        System.out.println(high);
        scan.close();
    }
}