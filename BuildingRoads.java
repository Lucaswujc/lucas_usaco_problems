import java.util.*;
public class BuildingRoads {
    static List<Integer>[] adj;
	static boolean[] visited;

	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        visited = new boolean[n];
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
		for(int i = 0;i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            adj[a-1].add(b-1);
            adj[b-1].add(a-1);
        }
        int connected = 0;
        ArrayList<Integer> disconnectedNodes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
            if (!visited[i]) { 
                dfs(i);
                connected++;
                disconnectedNodes.add(i); 
            }
		}
        System.out.println(connected-1);
        for(int i =0; i < connected-1; i++){
            System.out.println((disconnectedNodes.get(i)+1) + " " + (disconnectedNodes.get(i+1) +1));
        }
        scan.close();
	}

	static void dfs(int currentNode) {
		visited[currentNode] = true;
		for (int neighbor : adj[currentNode]) { 
            if(!visited[neighbor]){
                dfs(neighbor); 
            }
        }
	}
}