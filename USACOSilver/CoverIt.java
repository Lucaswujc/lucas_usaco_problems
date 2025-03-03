package USACOSilver;
import java.util.*;
public class CoverIt {
    static List<Integer>[] adj;
	static int[] teams;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int m = scan.nextInt();
            adj = new ArrayList[n];
            teams=  new int[n];
            for (int i = 0; i < n; i++) { 
                adj[i] = new ArrayList<>(); 
            }
            for(int i = 0;i < m; i++){
                int a = scan.nextInt();
                int b = scan.nextInt();
                adj[a-1].add(b-1);
                adj[b-1].add(a-1);
            }
            teams[0] = 1;
            dfs(0);
            int count=  0;
            for(int i  =0 ; i <n ;i++){
                if(teams[i] == 1){
                    count++;
                }
            }
            if(count >= (n - count)){
                System.out.println(count);
                for(int i = 0; i < n; i++){
                    if(teams[i] == 1){
                        System.out.print((i+1) + " ");
                    }
                }
                System.out.println();
            }
            else{
                System.out.println(n -count);
                for(int i = 0; i < n; i++){
                    if(teams[i] == 2){
                        System.out.print((i+1) + " ");
                    }
                }
                System.out.println();
            }
            t--;
        }
        scan.close();
    }
    static void dfs(int node) {
		int curr = teams[node];
		int color = curr == 1 ? 2 : 1;
		for (int neighbor : adj[node]) {
			if (teams[neighbor] == 0) {
				teams[neighbor] = color;
				dfs(neighbor);
			}
		}
	}
}