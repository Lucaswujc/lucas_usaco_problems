import java.io.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoadII {
	private static class Road {
		int x1, y1;
		int x2, y2;

		public Road(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x1, y1, x2, y2);
		}
		@Override
		public boolean equals(Object o) {
			Road r = (Road) o;
			return x1 == r.x1 && y1 == r.y1 && x2 == r.x2 && y2 == r.y2;
		}
	}
	static int[] changex = {0, 0, 1, -1};
	static int[] changey = {1, -1, 0, 0};
	static int n;
	static Set<Road> roads = new HashSet<>();
	static boolean[][] cows;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
		int cowNum = Integer.parseInt(st.nextToken());
		int roadNum = Integer.parseInt(st.nextToken());

		for (int r = 0; r < roadNum; r++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			roads.add(new Road(x1, y1, x2, y2));
			roads.add(new Road(x2, y2, x1, y1));
		}

		cows = new boolean[n][n];
		for (int c = 0; c < cowNum; c++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			cows[a][b] = true;
		}

		visited = new boolean[n][n];
		List<Integer> components = new ArrayList<>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c]) {
					int comp = floodfill(r, c, r, c);
					if (comp != 0) { 
						components.add(comp); 
					}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < components.size(); i++) {
			for (int j = i + 1; j < components.size(); j++) {
				ans += components.get(i) * components.get(j);
			}
		}

		out.println(ans);
		out.close();
		br.close();
	}

	private static int floodfill(int r, int c, int prevR, int prevC) {
		if (r < 0 || c < 0 || r >= n ||c >= n || visited[r][c]|| roads.contains(new Road(r, c, prevR, prevC))) {
			return 0;
		}

		visited[r][c] = true;
		int cowNum = cows[r][c] ? 1 : 0;
		for (int i = 0; i < 4; i++) { 
			cowNum += floodfill(r + changex[i], c + changey[i], r, c); 
		}
		return cowNum;
	}

}