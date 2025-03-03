package USACOSilver;
import java.util.*;
public class FlightRoutesCheck {
    static List<Integer>[] adj1;
    static List<Integer>[] adj2;
	static boolean[] visited;
   @SuppressWarnings("unchecked")
 public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n =scan.nextInt();
        int m = scan.nextInt();
        adj1 = new ArrayList[n];
        adj2 = new ArrayList[n];
        for (int i = 0; i < n; i++) { 
            adj1[i] = new ArrayList<>(); 
            adj2[i] = new ArrayList<>(); 
        }
        for(int i = 0; i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            adj1[a-1].add(b-1);
            adj2[b-1].add(a-1);
        }
        visited = new boolean[n];
        dfs(0, adj1);
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				System.out.println("NO");
				System.out.println(1 + " " + (i + 1));
                scan.close();
				return;
			}
		}
		visited = new boolean[n];
		dfs(0, adj2);
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				System.out.println("NO");
				System.out.println((i + 1) + " " + 1);
				scan.close();
				return;
			}
		}
        System.out.println("YES");
        scan.close();
    }
    static void dfs(int node, List<Integer>[] adj){
        visited[node]  = true;
        for(int neighbor : adj[node]){
            if(!visited[neighbor]){
                dfs(neighbor, adj);
            }
        }
    }
}