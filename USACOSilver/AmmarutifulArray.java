package USACOSilver;
import java.util.*;
public class AmmarutifulArray {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}
		int[] color = new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = scan.nextInt();
		}

		Map<Integer, List<Long>> colPrefs = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int c = color[i];
			if (!colPrefs.containsKey(c)) {
				colPrefs.put(c, new ArrayList<>(Arrays.asList(0L)));
			}
			List<Long> pref = colPrefs.get(c);
			pref.add(pref.get(pref.size() - 1) + arr[i]);
		}

		long total = 0;
		Map<Integer, Long> colExclude = new HashMap<>();

		StringBuilder ans = new StringBuilder();
		int queryNum = scan.nextInt();
		for (int q = 0; q < queryNum; q++) {
			int type = scan.nextInt();
			int col = scan.nextInt();
			long arg = scan.nextLong();

			if (type == 1) {
				total += arg;
				colExclude.put(col, colExclude.getOrDefault(col, 0L) + arg);
			} else if (type == 2) {
				int lo = 0;
				int hi = colPrefs.get(col).size() - 1;
				int valid = -1;
				while (lo <= hi) {
					int mid = (lo + hi) / 2;
					long initVal = colPrefs.get(col).get(mid);
					long toAdd =
					    (total - colExclude.getOrDefault(col, 0L)) * mid;
					if (initVal + toAdd <= arg) {
						valid = mid;
						lo = mid + 1;
					} else {
						hi = mid - 1;
					}
				}

				ans.append(valid).append('\n');
			}
		}

		System.out.print(ans);
        scan.close();
    }
}