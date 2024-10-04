package usaco_silver_basic;

import java.util.*;

public class DFS {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            graph.put(i + 1, new ArrayList<Integer>());
        }
        graph.get(1).add(2);
        graph.get(1).add(4);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(5);
        graph.get(3).add(2);
        graph.get(3).add(5);
        graph.get(5).add(2);
        graph.get(5).add(3);
        graph.put(0, new ArrayList<Integer>());

        Set<Integer> visisted = new HashSet<Integer>();
        // for (Integer node : graph.keySet()) {
        //     if (!visisted.contains(node))
        //         dfs(node, graph, visisted);
        // }
        for (Integer node : graph.keySet()) {
            if(!visisted.contains(node))
                dfsIterative(node, graph, visisted);
        }

    }


    public static void dfsIterative(int i, Map<Integer, List<Integer>> graph,
            Set<Integer> visisted) {
        Stack<Integer> tobevisisted = new Stack<>();
        tobevisisted.add(i);
        System.out.println("visiting: " + i);
        while (!tobevisisted.isEmpty()) {
            int topofstack = tobevisisted.peek();
            visisted.add(topofstack);
            boolean topofstackVisit = true;
            List<Integer> neighbours = graph.get(topofstack);
            for (Integer neighbor : neighbours) {
                if (!visisted.contains(neighbor)) {
                    tobevisisted.add(neighbor);
                    System.out.println("visiting: " + neighbor);
                    topofstackVisit = false;
                    break;
                }
            }
            if (topofstackVisit) {
                tobevisisted.pop();
            }
        }
        
    }
    
    public static void dfs(int i, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(i);
        System.out.println("visiting: " + i);
        List<Integer> neighborsList = graph.get(i);
        for (Integer neighbour : neighborsList) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, graph, visited);
            } else {
                continue;
            }
        }
    }

}
