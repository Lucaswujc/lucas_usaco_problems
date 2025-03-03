package USACOSilver;
import java.util.*;
public class Badge {
	public static int[] p;
	public static int[] ans;
	public static boolean in_cycle;

	public static void dfs(int n) {
		if (ans[n] != -2) {
			if (ans[n] == -1) {
				in_cycle = true;
				ans[n] = n;
			}
			return; 
		}

		ans[n] = -1; 

		dfs(p[n]);
		if (ans[n] != -1) {
			in_cycle = false;
		} else {
			ans[n] = in_cycle ? n : ans[p[n]];
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		p = new int[n];
		for (int i = 0; i < n; i++) { 
            p[i] = scan.nextInt() - 1; 
        }

		ans = new int[n];
		Arrays.fill(ans, -2);
		for (int i = 0; i < n; i++) {
			dfs(i);
		}

		for (int i = 0; i < n - 1; i++) { System.out.print((ans[i] + 1) + " "); }
		System.out.println(ans[n - 1] + 1);
        scan.close();
	}
}