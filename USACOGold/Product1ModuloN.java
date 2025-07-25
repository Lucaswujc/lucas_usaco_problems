package USACOGold;

import java.util.*;

public class Product1ModuloN {
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		ArrayList<Long> coprimes = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			if (gcd(n, i) == 1) {
				coprimes.add((long) i);
			}
		}

		int numberOfCoprimes = coprimes.size();
		long[] prefixMod = new long[numberOfCoprimes];
		prefixMod[0] = 1;
		for (int i = 1; i < numberOfCoprimes; i++) {
			prefixMod[i] = (prefixMod[i - 1] * coprimes.get(i)) % n;
		}

		int len = 1;
		for (int i = 0; i < numberOfCoprimes; i++) {

			if (prefixMod[i] == 1) {
				len = i + 1;
			}
		}

		System.out.println(len);

		for (int i = 0; i < len; i++) {
			System.out.print(coprimes.get(i) + " ");
		}
		System.out.println();
		scan.close();
	}
}