package USACOGuide;
import java.io.*;
import java.util.*;
public class Moocast {
    static Map<Integer, List<Integer>> adj;
	static boolean[] visited;
    static boolean[][] connected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
		int[] y = new int[n];
		int[] power = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			power[i] = Integer.parseInt(st.nextToken());
		}

		connected = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int dist = ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
				connected[i][j] = dist <= power[i] * power[i];
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			visited = new boolean[n];
			max = Math.max(max, dfs(i));
		}
        out.println(max);
        br.close();
        out.close();
	}

	static int dfs(int c) {
		visited[c] = true;
		int reached = 1;
		for (int i = 0; i < connected.length; i++) {
			if (!visited[i] && connected[c][i]) {
				visited[i] = true;
				reached += dfs(i);
			}
		}
		return reached;
	}
}