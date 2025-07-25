package USACOSilver;
import java.util.*;
public class PolandBallandForest {
    public static long mod = (long)1e9+7;
    public static ArrayList<Integer>[] graph;
    public static void sort(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        for(int u:arr) list.add(u);
        Collections.sort(list);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }
    public static void sort(long[] arr){
        ArrayList<Long> list = new ArrayList<>();
        for(long u:arr) list.add(u);
        Collections.sort(list);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = 1;
        for(int i = 1;i<=t ;i++){
            solve(in,out);
        }
        scan.close();
    }
    static boolean[] visited;
    static StringBuilder res;
    private static void solve() {
        int n = scan.nextInt();
        int[] endpoints = scan.readIntArray(n);
        HashSet<Integer> hs = new HashSet<>();
        int alone = 0;
        int normal = 0;
        for(int i = 0;i<endpoints.length;i++){
            if(endpoints[i]==(i+1)) alone++;
            else{
                if(hs.contains(endpoints[i])) continue;
                else{
                    hs.add(endpoints[i]);
                    normal++;
                }
            }
        }
        System.out.println(normal/2 + alone);

    }
    private static void dfs(int i) {
        visited[i] = true;
        for(int u:graph[i]){
            if(visited[u]) continue;
            res.append(i+" "+u);
            dfs(u);
        }
    }
}