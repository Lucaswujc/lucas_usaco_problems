import java.io.*;
import java.util.*;

public class CourseSchedule {
	private static List<Integer>[] graph;
	private static List<Integer> topSort = new ArrayList<>();
	private static boolean[] visited;

	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(read.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken()); 

		graph = new ArrayList[n];
		for (int i = 0; i < n; i++) { 
            graph[i] = new ArrayList<>(); 
        }
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
		}

		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		Collections.reverse(topSort);

		int[] ind = new int[n];
		for (int i = 0; i < n; i++) { 
            ind[topSort.get(i)] = i; 
        }

		boolean valid = true;
		for (int i = 0; i < n; i++) {
			for (int j : graph[i]) {
				if (ind[j] <= ind[i]) {
					valid = false;
					break;
				}
			}
            if(!valid){
                break;
            }
		}

		if (!valid) {
			System.out.println("IMPOSSIBLE");
		} else {
			for (int i = 0; i < topSort.size(); i++) {
                System.out.print((topSort.get(i) + 1) + " ");
            }
            System.out.println();
		}
	}

	private static void dfs(int node) {
		for (int next : graph[node]) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
		topSort.add(node);
	}
}