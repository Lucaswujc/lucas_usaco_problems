package USACOGold;
import java.util.*;

public class BinomialCoefficients {
	static final int MAXN = (int) 1e6;
	static final int MOD = (int) 1e9 + 7;
	static long[] fac = new long[MAXN + 1];
	static long[] inv = new long[MAXN + 1];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		factorial();
		inverses();
		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			System.out.println(choose(a, b));
		}
		scan.close();
	}

	private static long exp(long x, long n, long m) {
		x %= m;
		long res = 1;
		while (n > 0) {
			if (n % 2 == 1) {
				res = (res * x) % m;
			}
			x = (x * x) % m;
			n /= 2;
		}
		return res;
	}

	private static void factorial() {
		fac[0] = 1;
		for (int i = 1; i <= MAXN; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
	}

	private static void inverses() {
		inv[MAXN] = exp(fac[MAXN], MOD - 2, MOD);
		for (int i = MAXN; i >= 1; i--) {
			inv[i - 1] = (inv[i] * i) % MOD;
		}
	}

	private static long choose(int n, int r) {
		return (((fac[n] * inv[r]) % MOD) * inv[n - r]) % MOD;
	}
}