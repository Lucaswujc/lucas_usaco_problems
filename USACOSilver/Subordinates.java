package USACOSilver;
import java.util.*;
public class Subordinates {
    static final int MAXN = 200005;
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new ArrayList[MAXN];
    static int[] subordinates = new int[MAXN];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        List<Integer> bosses = new ArrayList<>();
        for(int i = 0; i < n-1 ; i++){
            int a = scan.nextInt();
            bosses.add(a);
        }
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            adj[bosses.get(i)].add(i+2);
        }

        dfs(1);
        for (int i = 1; i <= n; i++) {
            System.out.print(subordinates[i] + " ");
        }
        System.out.println();
        scan.close();
    }
    static void dfs(int node){
        for (int child : adj[node]) {
            dfs(child);
            subordinates[node] += subordinates[child] + 1;
        }
    }
    
}