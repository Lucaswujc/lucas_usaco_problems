package usaco_silver_basic;

import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;

public class BFS {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            graph.put(i + 1, new ArrayList<Integer>());
        }
        graph.get(1).add(2);
        graph.get(1).add(4);
        graph.get(4).add(10);
        graph.get(4).add(11);
        graph.get(11).add(4);
        graph.get(10).add(4);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(5);
        graph.get(3).add(2);
        graph.get(3).add(5);
        graph.get(5).add(2);
        graph.get(5).add(3);
        graph.put(0, new ArrayList<Integer>());

        Set<Integer> visited = new HashSet<Integer>();
        for (Integer n : graph.keySet()) {
            bfs(n, graph, visited);
        }
    }

    /**
     * 
     * fifo  : 1,2,3,4,5,6,7
     * queue.pop() === 1
     * queue.add(8)
     * 1,2,3,4,5,6,7,8
     * @param node
     * @param graph
     * @param visited
     */
    public static void bfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        Queue<Integer> tobevisited = new LinkedList<>();
        tobevisited.add(node);
        while (!tobevisited.isEmpty()) {
            int n = tobevisited.poll();
            if (!visited.contains(n)) {
                System.out.println("visiting: " + n);
                tobevisited.addAll(graph.get(n));
                visited.add(n);
            }
        }
    }
}
