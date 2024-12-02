import java.util.*;
public class TreeDistancesII {
    static int n;
    static ArrayList<Integer>[] adj;
    static int[] clocks;
    static int sum0 = 0, sum1 = 0, nodes0 = 0, nodes1 = 0;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
        clocks = new int[n];
        for(int i = 0; i < n; i++){
            clocks[i] = scan.nextInt();
        }
        for(int i = 0; i < n-1; i++){
            int a =scan.nextInt()-1;
            int b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(0, 0, -1);
		if ((sum0 % 12) == (sum1 % 12)) {
			System.out.println(n);
		}
		else if ((sum0 + 1) % 12 == (sum1 % 12)) {
			System.out.println(nodes1);
		}
		else if ((sum0 % 12) == ((sum1 + 1) % 12)) {
			System.out.println(nodes0);
		}
		else {
			System.out.println(0);
		}
		scan.close();
	}

	static void dfs(int i, int color, int parent) {
		if (color == 0) {
			nodes0++;
			sum0 += clocks[i];
		} else {
			nodes1++;
			sum1 += clocks[i];
        }
		for (int neighbor : adj[i]) {
			if (neighbor != parent) {
				dfs(neighbor, 1 - color, i);
			}
		}
	}

}