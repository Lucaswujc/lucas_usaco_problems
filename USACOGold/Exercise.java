package USACOGold;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public final class Exercise {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("exercise.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cowNum = Integer.parseInt(st.nextToken());
		BigInteger mod = new BigInteger(st.nextToken());

		BigInteger[] totalWithSum = new BigInteger[cowNum + 1];
		Arrays.fill(totalWithSum, BigInteger.ZERO);

		totalWithSum[0] = new BigInteger("1");
		for (int i = 2; i <= cowNum; i++) {
			if (!prime(i)) { continue; }
			BigInteger[] updatedTotal = totalWithSum.clone();
			for (int p = i; p <= cowNum; p *= i) { 
				for (int from = 0; from + p <= cowNum; from++) {
					updatedTotal[from + p] = updatedTotal[from + p].add(
					    totalWithSum[from].multiply(new BigInteger(String.valueOf(p))));
				}
			}
			totalWithSum = updatedTotal;
		}

		BigInteger total = BigInteger.ZERO;
		for (BigInteger prod : totalWithSum) { total = total.add(prod); }
		total = total.mod(mod);

		PrintWriter written = new PrintWriter("exercise.out");
		written.println(total);
		written.close();
	}
	private static boolean prime(int n) {
		if (n <= 1) return false;
		if (n <= 3) return true;
		if (n % 2 == 0 || n % 3 == 0) return false;

		for (int i = 5; i * i <= n; i = i + 6) {
			if (n % i == 0 || n % (i + 2) == 0) { return false; }
		}
		return true;
	}
}