package USACOGuide;
import java.util.*;
public class FactoryMachines {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int t = scan.nextInt();
        int[] machines = new int[n];
        for(int i  = 0 ; i < n; i++){
            machines[i] = scan.nextInt();
        }
        long lo = 0;
		long hi = (long)1e18;
		long ans = 0;
		while (lo <= hi) {
			long mid = (lo + hi) / 2;

			long produced = 0;
			for (int m : machines) {
			    produced += mid / m;
				if (produced >= t) { 
                    break; 
                }
			}

			if (produced >= t) {
				ans = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
	    System.out.println(ans);
        scan.close();
    }
}