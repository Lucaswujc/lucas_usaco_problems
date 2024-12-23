package successor_graph;

import java.util.HashSet;
import java.util.Set;


/**
 * two pointer floyd algorithm to detect cycles in the graph
 * 
 */
public class FloydHareTutoris {



    public static void main(String[] args) {
        /**
         * init the graph 1-->2-->3-->4-->5-->6-->4
         */
        int[] g = new int[6];
        for (int i = 0; i < g.length - 1; i++) {
            g[i] = i + 1;
        }
        g[5] = 3;
        Set<Integer> visited = new HashSet<>();
        for (int node = 0; node < g.length; node++) {
            if (visited.contains(node))
                continue;
            //visited.add(node);
            findCycleByWalkFromNode(g, node, visited);
        }

    }

    private static void findCycleByWalkFromNode(int[] graph, int node, Set<Integer> visited) {
        int tortoise = graph[node];
        int hare = graph[graph[node]];
        // hare walks twice faster comparing to tortois
        while (tortoise != hare) {
            hare = graph[graph[hare]];
            tortoise = graph[tortoise];
            visited.add(hare);
            visited.add(tortoise);
        }
        //move tortois to begining
        tortoise = node;
        while (tortoise != hare) {
            hare = graph[hare];
            tortoise = graph[tortoise];
            visited.add(hare);
            visited.add(tortoise);
        }
        int head = tortoise;
        System.out.println(String.format("head of cycle = %d", head));
    }
}
