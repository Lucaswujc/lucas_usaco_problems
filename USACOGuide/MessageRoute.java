package USACOGuide;

import java.util.*;

public class MessageRoute {

	private static Map<Integer, LinkedList<Integer>> adj = new HashMap<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();
		for (int i = 0; i < m; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			if (adj.get(a) == null) {
				adj.put(a, new LinkedList<>());
			}

			if (adj.get(b) == null) {
				adj.put(b, new LinkedList<>());
			}

			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		int[] prev = new int[n + 1], dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 1;

		Queue<Integer> bfs = new LinkedList<>();
		bfs.add(1);

		while (!bfs.isEmpty()) {
			int top = bfs.poll();
			if (dist[top] == Integer.MAX_VALUE)
				continue;
			if (adj.get(top) != null) {
				for (int e : adj.get(top)) {
					if (dist[e] == Integer.MAX_VALUE) {
						dist[e] = dist[top] + 1;
						prev[e] = top;
						bfs.add(e);
					}
				}
			}
		}

		if (dist[n] == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(dist[n]);
			int[] res = new int[dist[n]];
			int i = dist[n] - 1;
			for (int x = n; x != 0; x = prev[x]) {
				res[i--] = x;
			}
			for (int a : res)
				System.out.print(a + " ");
		}
		scan.close();
	}
}