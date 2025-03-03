package USACOSilver;
import java.util.*;
public class Cowntagion {
    static List<Integer>[] adj; 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        adj = new ArrayList[n];
		for (int i = 0; i < n; i++) { 
			adj[i] = new ArrayList<>(); 
		}
		for (int i = 0; i < n - 1; i++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
		}

        int ans = 0;
		boolean[] visited = new boolean[n];
		Queue<Integer> farms = new ArrayDeque<>();
        farms.add(0);
		visited[0] = true;
		while (!farms.isEmpty()) {
			int current = farms.poll();
			int count = 0;
			for (int neighbor : adj[current]) {
				if (!visited[neighbor]) {
					count++;
					visited[neighbor] = true;
					farms.add(neighbor);
				}
            }
			ans += log(count + 1) + count;
		}

		System.out.println(ans);
        scan.close();
	}

	private static int log(int n) {
		int count = 0;
		int curr = 1;
		while (curr < n) {
			curr *= 2;
			count++;
		}
		return count;
	}
}