// Created by Qi Wang
import java.io.*;
import java.util.*;

public class MilkPumping {
	static int N;
	static int M;
	static List<Node>[] adjList;
	static boolean[] vist;
	static int[] costs;
	static int max = Integer.MIN_VALUE;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pump.in"));
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());

		adjList = new List[N];

		for (int i = 0; i < N; i++) { adjList[i] = new ArrayList<>(); }

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			int flow = Integer.parseInt(st.nextToken());

			adjList[from].add(new Node(to, cost, flow));
			adjList[to].add(new Node(from, cost, flow));
		}

		for (int minF = 0; minF <= 1000; minF++) {
			costs = new int[N];
			Arrays.fill(costs, Integer.MAX_VALUE);
			vist = new boolean[N];
			int[] res = bfs(minF);
			if (res[0] < 0) continue;
			double frac = (double)res[1] / res[0];
			max = Math.max(max, (int)Math.floor(frac * 1e6));
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
		out.println(max);
		out.close();
		in.close();
	}

	public static int[] bfs(int minF) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int min = Integer.MAX_VALUE;
		pq.add(new Node(0, 0, 0));
		costs[0] = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int n = cur.No;

			if (vist[n]) continue;
			vist[n] = true;

			for (int i = 0; i < adjList[n].size(); i++) {
				int t = adjList[n].get(i).No;
				int c = adjList[n].get(i).c + cur.c;

				if (adjList[n].get(i).f < minF) continue;
				if (vist[t]) continue;

				if (costs[t] > c) {
					costs[t] = c;
					min = Math.min(min, adjList[n].get(i).f);
					pq.add(new Node(t, c, adjList[n].get(i).f));
				}
			}
		}
		return new int[] {costs[N - 1] == Integer.MAX_VALUE ? -1 : costs[N - 1], min};
	}

	@SuppressWarnings("rawtypes")
	private static class Node implements Comparable {
		int No;
		int c;
		int f;
		public Node(int n, int c, int f) {
			No = n;
			this.c = c;
			this.f = f;
		}

		@Override
		public int compareTo(Object o) {
			return c - ((Node)o).c;
		}

		@Override
		public String toString() {
			return No + " " + c + " " + f;
		}
	}
}