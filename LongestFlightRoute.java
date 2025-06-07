import java.io.*;
import java.util.*;

public class LongestFlightRoute {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(read.readLine());
		int cityNum = Integer.parseInt(st.nextToken());
		int flightNum = Integer.parseInt(st.nextToken());

		List<Integer>[] flights = new ArrayList[cityNum];
		List<Integer>[] backEdge = new ArrayList[cityNum];
		for (int i = 0; i < cityNum; i++) {
			flights[i] = new ArrayList<>();
			backEdge[i] = new ArrayList<>();
		}
		for (int i = 0; i < flightNum; i++) {
			st = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			flights[a].add(b);
			backEdge[b].add(a);
		}

		int[] inDegree = new int[cityNum];
		for (List<Integer> nodes : flights) {
			for (int node : nodes) { 
                inDegree[node]++;
            }
		}

		ArrayDeque<Integer> queue = new ArrayDeque<>();
		List<Integer> topSort = new ArrayList<>();
		for (int i = 0; i < cityNum; i++) {
			if (inDegree[i] == 0) { queue.add(i); }
		}
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			topSort.add(curr);
			for (int next : flights[curr]) {
				if (--inDegree[next] == 0) { queue.add(next); }
			}
		}

		int[] parent = new int[cityNum];
		Arrays.fill(parent, -1);
		int[] dist = new int[cityNum];
		Arrays.fill(dist, Integer.MIN_VALUE);
		dist[0] = 1;
		for (int i = 0; i < topSort.size(); i++) {
			int b = topSort.get(i);
			for (int prev : backEdge[b]) {
				if (dist[prev] + 1 > dist[b]) {
					dist[b] = dist[prev] + 1;
					parent[b] = prev;
				}
			}
		}

		if (dist[cityNum - 1] < 0) {
			System.out.println("IMPOSSIBLE");
		} else {
			// dist[city_num - 1] denotes the length of the longest path
			// ending at the final city. i.e. Lehmälä
			System.out.println(dist[cityNum - 1]);

			// Begin from the final city, trace the parent pointer
			// to construct the entire path backward
			int at = cityNum - 1;
			List<Integer> route = new ArrayList<>();
			while (parent[at] != -1) {
				route.add(at);
				at = parent[at];
			}
			route.add(0);

			// Print the route in the correct order
			Collections.reverse(route);
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < route.size() - 1; i++) {
				ans.append(route.get(i) + 1).append(' ');
			}
			ans.append(route.get(route.size() - 1) + 1);
			System.out.println(ans);
		}
	}
}