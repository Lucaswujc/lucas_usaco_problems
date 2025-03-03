package USACOSilver;
import java.util.*;
public class TreeDistancesII {
    static int n;
    static ArrayList<Integer>[] adj;
    static int[] subtree;
    static int[] ans;
    static void dfs1(int node, int parent, long dist) {
        ans[0] += dist;
        subtree[node] = 1;
        for (int i : adj[node]){
            if (i != parent) {
                dfs1(i, node, dist + 1);
                subtree[node] += subtree[i];
            }
        }
    }
    static void dfs2(int node, int parent) {
        for (int i : adj[node]){
            if (i != parent) {
                ans[i] = ans[node] + n - 2 * subtree[i];
                dfs2(i, node);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
        subtree = new int[n];
        ans = new int[n];
        for(int i = 0; i < n-1; i++){
            int a =scan.nextInt()-1;
            int b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs1(0,0,0);
        dfs2(0,0);
        for(int i = 0; i < n; i++){
            System.out.print(ans[i] + " ");
        }
        scan.close();
    }
}