import java.io.*;
import java.util.*;
public class ClosingTheFarm {
    static Map<Integer, List<Integer>> adj;
	static boolean[] visited;
    static boolean[] left;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter("closing.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
		adj = new HashMap<>();
		for (int i = 0; i < n; i++) { 
            adj.put(i, new ArrayList<>()); 
        }
		for(int i = 0;i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a-1).add(b-1);
            adj.get(b-1).add(a-1);
        }
        left = new boolean[n];
        Arrays.fill(left, true);
        dfs(0);
        if(checkTrue()){
            out.println("YES");
        }
        else{
            out.println("NO");
        }
		for (int i = 0; i < n-1; i++) {
            Arrays.fill(visited, false);
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            left[a-1] = false;
            adj.remove(a-1);
            for(int j = 0; j < n; j++){
                if(left[j]){
                    dfs(j);
                    break;
                }
            }
            if(checkTrue()){
                out.println("YES");
            }
            else{
                out.println("NO");
            }
		}
        out.close();
        br.close();
	}

	static void dfs(int currentNode) {
        if(!left[currentNode] || visited[currentNode]){
            return;
        }
		visited[currentNode] = true;
		for (int neighbor : adj.get(currentNode)) { 
            dfs(neighbor); 
        }
	}
    static Boolean checkTrue(){
        for(int i = 0; i < visited.length; i++){
            if(visited[i] == false && left[i] == true){
                return false;
            }
        }
        return true;
    }
}