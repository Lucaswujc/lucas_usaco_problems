package USACOGold;

import java.util.*;

public class DivGame {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long n = scan.nextLong();
		int ans = 0;
		for (long p = 2; p * p <= n; p++) {
			int exponent = 0;
			while (n % p == 0) {
				exponent++;
				n /= p;
			}
			for (int i = 1; exponent - i >= 0; i++) {
				exponent -= i;
				ans++;
			}
		}
		if (n > 1) {
			ans++;
		}
		System.out.println(ans);
		scan.close();
	}
}