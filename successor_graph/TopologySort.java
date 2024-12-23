package successor_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TopologySort {
    static HashMap<Integer, List<Integer>> graph;

    /**
     * 
     * 4 --> 1 --> 2 --> 3 -->6 
     * | ^ ^ \---> 5 ____|_____|
     * 
     * Expected sorted out put is 6,3,2,1,5,4 Topology sort is not necessary unique
     */
    public static void init() {
        graph = new HashMap<>();
        int[][] edgeList = new int[][] {{5, 1}, {5, 4}, {1, 2}, {2, 3}, {3, 6}, {4, 2}, {4, 3}};
        // {{4, 1}, {4, 5}, {1, 2}, {2, 3}, {3, 6}, {5, 2}, {5, 3}};
        for (int i = 0; i < 6; i++) {
            int node = i + 1;
            graph.put(node, new ArrayList<Integer>());
        }
        for (int[] edge : edgeList) {
            graph.get(edge[0]).add(edge[1]);
        }
    }

    public static void main(String[] args) {
        init();
        Set<Integer> visisted = new HashSet<>();
        Set<Integer> vertices = graph.keySet();
        List<Integer> sorted = new ArrayList<>();
        for (int v : vertices) {
            // System.out.println(v);
            dfs(graph, v, visisted, sorted);
        }
        System.out.println("=======topological sort dfs =====");
        for (int v : sorted) {
            System.out.println(v);
        }

        System.out.println("======topological sort bfs=======");
        topoSortBFS(graph);
    }

    public static void dfs(Map<Integer, List<Integer>> graph, int v, Set<Integer> visited,
            List<Integer> sorted) {
        if (visited.contains(v))
            return;
        visited.add(v);
        List<Integer> neighbours = graph.get(v);
        for (int neighbor : neighbours) {
            dfs(graph, neighbor, visited, sorted);
        }
        sorted.add(v);
    }

    public static void topoSortBFS(Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> indegrees = new HashMap<>();
        //prepare the indegree dictionary and the process queue
        for (Integer v : graph.keySet()) {
            if (!indegrees.containsKey(v))
                indegrees.put(v, 0);
            for (Integer n : graph.get(v)) {
                if (!indegrees.containsKey(n))
                    indegrees.put(n, 0);
                indegrees.put(n, indegrees.get(n) + 1);
            }
        }
        for (Integer v : indegrees.keySet()) {
            if (indegrees.get(v) == 0)
                queue.add(v);
        }

        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            System.out.println(v);
            List<Integer> edges = graph.get(v);
            for (Integer toVertex : edges) {
                int indegree = indegrees.get(toVertex);
                indegree -= 1;
                if (indegree == 0) {
                    queue.add(toVertex);
                }
                indegrees.put(toVertex, indegree);
            }    
        }
    }
}
