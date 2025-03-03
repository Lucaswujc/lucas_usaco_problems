package USACOGuide;
import java.util.*;

public class MaximumMedian {
    static int size;
	static int maxOps;
	static int[] arr;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		size = scan.nextInt();
		maxOps = scan.nextInt();
		arr = new int[size];
		for (int i = 0; i < size; i++) { arr[i] = scan.nextInt(); }
		Arrays.sort(arr);

		System.out.println(lastTrue(1, (int)2e9));
		scan.close();
	}

	public static boolean medReachable(int x) {
		long opsNeeded = 0;
		for (int i = (size - 1) / 2; i < size; i++)
			opsNeeded += Math.max(0, x - arr[i]);
		return opsNeeded <= maxOps;
	}

	public static int lastTrue(int lo, int hi) {
		lo--;
		while (lo < hi) {
			int mid = lo + (hi - lo + 1) / 2;
			if (medReachable(mid)) {
				lo = mid;
			} else {
				hi = mid - 1;
			}
		}
		return lo;
	}
}
