import java.util.*;
public class Journey {
    static int n;
    static List<Integer>[] adj;
    static double ans = 0;
    static boolean[] visited;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++){
            int a = scan.nextInt()-1;
            int b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        visited = new boolean[n];
        dfs(0, 0, 1.0);
        System.out.println(ans);
        scan.close();
    }
    static void dfs(int node, int len, double prob){
        visited[node] = true;
        int moves = 0;
        for (int child : adj[node]) {    
            if(!visited[child]){
                moves++;
            }
        }
        if(moves == 0){
            double len1 = len;
            ans += (len1 * 1/(prob));
        }
        else{
            double moves1 = moves;
            prob *= moves1;
            len++;
            for(int child : adj[node]){
                if(!visited[child]){
                    dfs(child, len, prob);
                }
            }
        }

    }
}