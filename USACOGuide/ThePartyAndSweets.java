package USACOGuide;
import java.util.*;
public class ThePartyAndSweets {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
		int m = scan.nextInt();

		int[] b = new int[n];
		int[] g = new int[m];
		long ans = 0;

		for (int x = 0; x < n; x++) {
			b[x] = scan.nextInt();
			ans += b[x];
		}

		for (int x = 0; x < m; x++) { g[x] = scan.nextInt(); }
		ans *= m;

		Arrays.sort(b);
		Arrays.sort(g);

		if (b[n - 1] > g[0]) {
			System.out.println(-1);
			scan.close();
			System.exit(0);
		}

		for (int i = 1; i < m; i++) { 
            ans += (g[i] - b[n - 1]); 
        }

		if (g[0] != b[n - 1]) { 
            ans += (g[0] - b[n - 2]); 
        }
        System.out.println(ans);
        scan.close();
    }
}