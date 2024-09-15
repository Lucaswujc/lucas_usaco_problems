import java.util.*;
public class ConnectedComponents {
    static List<Integer>[] adj;
	static boolean[] visited;
    static int track;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        adj = new ArrayList[n];
        visited =new boolean[n];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>();
            for(int j = 0; j < n; j++){
                adj[i].add(j);
            } 
        }
		for(int i = 0;i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            adj[a-1].set(b-1, -1);
            adj[b-1].set(a-1, -1);
        }
        int count = 0;
        ArrayList<Integer> components = new ArrayList<>();
        for(int i =0; i <n; i++){
            track = 0;
            if(!visited[i]){
                dfs(i);
                count++;
                components.add(track);
            }
        }
        Collections.sort(components);
        System.out.println(count);
        for(int i = 0; i < components.size(); i++){
            System.out.print(components.get(i) + " ");
        }
        scan.close();
    }
    static void dfs(int currentNode) {
		visited[currentNode] = true;
        track++;
		for (int neighbor : adj[currentNode]) { 
            if(neighbor != -1){
                if(!visited[neighbor]){
                    dfs(neighbor); 
                }
            }
        }
	}
}