package USACOGold;
import java.io.*;
import java.util.*;

public class Odometer {
	static long[][][][] dp = new long[19][50][2][2];
	static String num;

	public static void reset() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 50; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) { dp[i][j][k][l] = -1; }
				}
			}
		}
	}

	public static long solveDP(int pos, int k, int under, int started, int targ, int targ2) {
		if (pos == num.length()) {
			if (started == 0) { return 0; }

			if (targ2 != -1) {
				if (k == 20) {
					return 1;
				} else {
					return 0;
				}
			}
			if (k >= 20) {
				return 1;
			} else {
				return 0;
			}
		}

		if (dp[pos][k][under][started] != -1) { return dp[pos][k][under][started]; }

		long ans = 0;
		for (int i = 0; i <= 9; i++) {
			int digit_diff = num.charAt(pos) - '0';

			if (under == 0 && i > digit_diff) { break; }

			int isUnder = under;
			if (i < digit_diff) { 
                isUnder = 1; 
            }

			int isStarted = started | (i != 0 ? 1 : 0);

			if (isStarted == 1 && targ2 != -1 && i != targ && i != targ2) { 
                continue; 
            }

			int newK = k;
			if (isStarted == 1) {
				if (targ == i) {
					newK = k + 1;
				} else {
					newK = k - 1;
				}
			}
			ans += solveDP(pos + 1, newK, isUnder, isStarted, targ, targ2);
		}
		return dp[pos][k][under][started] = ans;
	}

	public static long countInterestingTo(long ubound) {
		num = String.valueOf(ubound);
		long ans = 0;
		for (int i = 0; i <= 9; i++) {
			reset();
			ans += solveDP(0, 20, 0, 0, i, -1);
		}


		long duplicates = 0;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				reset();
				duplicates += solveDP(0, 20, 0, 0, i, j);
			}
		}
		return ans - (duplicates / 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("odometer.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();

		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());

		PrintWriter pw = new PrintWriter("odometer.out");
		pw.println(countInterestingTo(Y) - countInterestingTo(X - 1));
		pw.close();
	}
}