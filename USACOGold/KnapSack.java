package USACOGold;
import java.io.*;
import java.util.*;

public class KnapSack {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer initial = new StringTokenizer(read.readLine());
		int s = Integer.parseInt(initial.nextToken());
		int n = Integer.parseInt(initial.nextToken());
		HashMap<Integer, ArrayList<int[]>> byWeight = new HashMap<>();
		for (int t = 0; t < n; t++) {
			StringTokenizer st = new StringTokenizer(read.readLine());
			int value = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			int amt = Integer.parseInt(st.nextToken());
			if (weight <= s && amt > 0) {
				if (!byWeight.containsKey(weight)) {
					byWeight.put(weight, new ArrayList<>());
				}
				byWeight.get(weight).add(new int[] {value, amt});
			}
		}


		long[][] best = new long[byWeight.size() + 1][s + 1];
		for (long[] row : best) { Arrays.fill(row, Integer.MIN_VALUE); }
		best[0][0] = 0;
		int at = 1;
		for (var pair : byWeight.entrySet()) {
			int w = pair.getKey();
			ArrayList<int[]> items = pair.getValue();
			items.sort(Comparator.comparingInt(i -> - i[0]));
			for (int i = 0; i <= s; i++) {
				best[at][i] = best[at - 1][i];
				int copies = 0;
				int typeAt = 0;
				int currUsed = 0;
				long profit = 0;
				while ((copies + 1) * w <= i && typeAt < items.size()) {
					copies++;
					profit += items.get(typeAt)[0];
					if (best[at - 1][i - copies * w] != Integer.MIN_VALUE) {
						best[at][i] = Math.max(best[at][i],
						                       best[at - 1][i - copies * w] + profit);
					}
					currUsed++;
					if (currUsed == items.get(typeAt)[1]) {
						currUsed = 0;
						typeAt++;
					}
				}
			}
			at++;
		}

		long mostValue = 0;
		for (int i = 0; i <= s; i++) {
			mostValue = Math.max(mostValue, best[byWeight.size()][i]);
		}
		System.out.println(mostValue);
	}
}