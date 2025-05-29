package USACOGold;
import java.util.*;
public class PotionFarming {
    static List<Integer>[] adj;
	static boolean[] visited;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        adj = new ArrayList[n];
        int[] potions = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
        for(int i =0; i < n; i++){
            potions[i] = scan.nextInt()-1;
        }
        for(int i =0; i < n-1; i++){
            int a = scan.nextInt()-1;
            int b = scan.nextInt()-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        numleaves = new int[n];
        int[] available = new int[n];
        int leaves = countleaves(0,-1, adj);
        for(int i = 0; i < leaves; i++){
            available[potions[i]]++;
        }
        System.out.println(countPots(0, -1, available, adj));
        scan.close();
    }
    static int countPots(int curr, int parent, int[] available, List<Integer>[] graph){
        int sum = 0;
        for (int node : graph[curr]){
            if (node == parent){
                continue;
            }
            sum += countPots(node, curr, available, graph);
        }
        sum += available[curr];
        return Math.min(sum, numleaves[curr]);
    }
    public static int[] numleaves;
    public static int countleaves(int curr, int parent, List<Integer>[] graph){

        if ((graph[curr].size() == 1 && graph[curr].get(0) == parent)){
            numleaves[curr] = 1;
            return 1;
        }
        int sum = 0;
        for (int node : graph[curr]){
            if (node == parent){
                continue;
            }
            sum += countleaves(node,curr,graph);
        }
        numleaves[curr] = sum;
        return sum;
    }
}