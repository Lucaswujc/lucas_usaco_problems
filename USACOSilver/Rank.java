package USACOSilver;
import java.util.*;
public class Rank {
    static List<Integer>[] adj;
	static boolean[] visited;
    static boolean cyclic = false;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        visited = new boolean[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
        for(int i = 0; i < k; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            int score_a = scan.nextInt();
            int score_b = scan.nextInt();
            if(score_a > score_b){
                adj[a-1].add(b-1);
            }
            else if(score_b>score_a){
                adj[b-1].add(a-1);
            }
        }
        //brute force
        int ans = 0;
        for(int v = 0; v < n; v++){
            cyclic = false;
            if(dfs(v, v)){
                ans++;
            }
            Arrays.fill(visited, false);
        }
        System.out.println(ans);
        scan.close();
    }
    static Boolean dfs(int node, int start){
        
        if(node == start && visited[node]){
            cyclic = true;
            return true;
        }
        if(visited[node]){
            return true;
        }
        visited[node]  = true;
        for(int neighbor : adj[node]){
            dfs(neighbor, start);
        }
        return cyclic;
    }
}