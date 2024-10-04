package usaco_silver_basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Integer, List<Integer>> edgeLists;

    public Graph() {
        edgeLists = new HashMap<>();
    }
    
    public void addEdge(int from, int to) {
        if (!edgeLists.containsKey(from)) {
            edgeLists.put(from, new ArrayList<Integer>());
        }
            edgeLists.get(from).add(to);
    }

    public Collection<? extends Integer> getConnectedEdgesForNode(int node) {
        return edgeLists.get(node);
    }
}
