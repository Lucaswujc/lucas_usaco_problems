import java.util.*;
public class RedistributingGifts {
    static List<List<Integer>> adj;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] wishlist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = n; j > 0; j--){
                int a = scan.nextInt();
                wishlist[i][a] = j;
            }
        }
        boolean[][] reachable = new boolean[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (wishlist[i][j] >= wishlist[i][i]) {
                    reachable[j][i] = true;
                }
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                for (int k = 1; k <= n; k++) {
                    reachable[i][k] = reachable[i][k] || (reachable[i][j] && reachable[j][k]);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            int best = 0;
            for (int j = 1; j <= n; j++) {
                if (wishlist[i][j] > wishlist[i][best] && reachable[i][j]) {
                    best = j;
                }
            }
            System.out.println(best);
        }
        scan.close();
    }
}
/*
1 2 3 4
1 3 2 4
1 2 3 4
1 2 3 4

for i , the viable exchange path is []
j, the viable exchange path is [(2,3), (2,1)]
co23, the viable exchange path is [(3,2),(3,1)]
i 4 he viable exchange path is [(4,1), (4,2), (4,3)]
for i 2, 
if i 2 wsant to get j 1 , and let assume that it can , whatneeds to happen?
there need to be a anedge that 2-> 1 exists,
if i 1 do have j 2 in its wish lisht, then there should be an edge from 1->2 , 
if i 1 do not have git 2 in its wish list, then i nees to exchange j 2 to another 
i in its wish list, wich means pick an edge from 1 -> ?
if 2 is in the ? i's wish list, then it means ?->2 edge exists, done 
else do ththe same operation at certain point we will find an edge ?? -> 2
using this thinking process, if a i i can get a j j in the final stage, there 
should be a fully connected path from i-> i via j 
can we build a matrix to represent whether a i i can traverse to i i via j 
connected[i][j] = true only if dfs/bfs can traverse from i-> j -> i 

for (node in all nodes){
    wishlist = wislists.get(node)
    for(connectednode :wishlist){
        if connected[node][connectednode] = true 
        print connected node
        break;
    }
}

2-->3
3-->2
4-->3
3-->4
*/