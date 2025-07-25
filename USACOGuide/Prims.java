package USACOGuide;

import java.io.*;
import java.util.*;

public class Prims {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer initial = new StringTokenizer(read.readLine());
		int v = Integer.parseInt(initial.nextToken());
		int e = Integer.parseInt(initial.nextToken());

		List<int[]>[] neighbors = new ArrayList[v];
		for (int c = 0; c < v; c++) {
			neighbors[c] = new ArrayList<>();
		}
		for (int r = 0; r < e; r++) {
			StringTokenizer road = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(road.nextToken()) - 1;
			int b = Integer.parseInt(road.nextToken()) - 1;
			int cost = Integer.parseInt(road.nextToken());
			neighbors[a].add(new int[] { b, cost });
			neighbors[b].add(new int[] { a, cost });
		}

		long minCost = prim(neighbors);
		if (minCost == -1) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(minCost);
		}
	}

	static long prim(List<int[]>[] neighbors) {
		final int n = neighbors.length; // just a shorthand

		long minCost = 0;
		long[] dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		PriorityQueue<LongIntPair> q = new PriorityQueue<>(); // {cost, vertex}
		q.add(new LongIntPair(0, 0));
		boolean[] visited = new boolean[n];
		int added = 0;
		while (added < n) {
			if (q.isEmpty()) {
				return -1;
			}

			LongIntPair curr = q.poll();
			if (dist[curr.second] < curr.first) {
				continue;
			}

			added++;
			visited[curr.second] = true;
			minCost += curr.first;
			for (int[] next : neighbors[curr.second]) {
				if (!visited[next[0]] && next[1] < dist[next[0]]) {
					dist[next[0]] = next[1];
					q.add(new LongIntPair(next[1], next[0]));
				}
			}
		}

		return minCost;
	}
}

class LongIntPair implements Comparable<LongIntPair> {
	long first;
	int second;

	public LongIntPair(long first, int second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(LongIntPair other) {
		if (first != other.first) {
			return (int) (first - other.first);
		}
		return second - other.second;
	}
}