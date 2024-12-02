import java.util.*;
public class TreeDistancesI {
	static ArrayList<Integer>[] adj;
	static int MAX_N = 200000;
	static int[] firstMax = new int[MAX_N + 1]; 
	static int[] secondMax = new int[MAX_N + 1]; 
	static int[] c = new int[MAX_N + 1]; 

	public static void dfs(int v, int p) {
		firstMax[v] = 0;
		secondMax[v] = 0;
		for (int x : adj[v]) {
			if (x == p) continue;
			dfs(x, v);
			if (firstMax[x] + 1 > firstMax[v]) {
				secondMax[v] = firstMax[v];
				firstMax[v] = firstMax[x] + 1;
				c[v] = x;
			} else if (firstMax[x] + 1 > secondMax[v]) {
				secondMax[v] = firstMax[x] + 1;
			}
		}
	}

	public static void dfs2(int v, int p) {
		for (int x : adj[v]) {
			if (x == p) continue;
			if (c[v] == x) {
				if (firstMax[x] < secondMax[v] + 1) {
					secondMax[x] = firstMax[x];
					firstMax[x] = secondMax[v] + 1;
					c[x] = v;
				} else {
					secondMax[x] = Math.max(secondMax[x], secondMax[v] + 1);
				}
			} else {
				secondMax[x] = firstMax[x];
				firstMax[x] = firstMax[v] + 1;
				c[x] = v;
			}
			dfs2(x, v);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, u, v;
		n = scan.nextInt();
		adj = new ArrayList[n + 1];
		for (int x = 0; x < adj.length; x++) { 
            adj[x] = new ArrayList(); 
        }

		for (int i = 0; i < n - 1; i++) {
			u = scan.nextInt();
			v = scan.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1, -1);
		dfs2(1, -1);

		for (int i = 1; i <= n; i++) { 
            System.out.println(firstMax[i] + " "); 
        }

		scan.close();
	}
}