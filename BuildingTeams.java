import java.util.*;
public class BuildingTeams {
    static List<Integer>[] adj;
	static int[] teams;

	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
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
        teams = new int[n];
        Boolean work = true;
		for (int i = 0; i < n; i++) {
            if (teams[i] == 0) { 
                teams[i] = 1;
                if(!dfs(i)){
                    work = false;
                    break;
                }
            }
		}
        if(work){
            for(int i =0; i < n; i++){
                System.out.print(teams[i] + " ");
            }
        }
        else{
            System.out.println("IMPOSSIBLE");
        }
        scan.close();
	}

	static Boolean dfs(int node) {
		int curr = teams[node];
		int color = curr == 1 ? 2 : 1;
		for (int neighbor : adj[node]) {
			if (teams[neighbor] != 0) {
				if (teams[neighbor] != color) { 
                    return false; 
                }
			} else {
				teams[neighbor] = color;
				if (!dfs(neighbor)) {
					return false; 
				}
			}
		}
		return true;
	}
}