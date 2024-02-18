package USACOGuide;
import java.util.*;

class GCDOnBlackboard {
    static int gcd(int a, int b) { 
        return b == 0 ? a : gcd(b, a % b); 
    }
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
        int n =  scan.nextInt();
        int[] arr = new int[n];
        for(int  i = 0; i <n ;i++ ){
            arr[i] = scan.nextInt();
        }
        int[] prefGCD = new int[n + 1];
		int[] suffGCD = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefGCD[i] = gcd(prefGCD[i - 1], arr[i - 1]);
		}
		for (int i = n - 1; i >= 0; i--) {
			suffGCD[i] = gcd(suffGCD[i + 1], arr[i]);
		}
        int ans = 0;
		for (int i = 0; i < n; i++) {
			int g = gcd(suffGCD[i + 1], prefGCD[i]);
			ans = Math.max(ans, g);
		}
		System.out.println(ans);
		scan.close();
	}
}