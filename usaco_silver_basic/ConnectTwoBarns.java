package usaco_silver_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Farmer John's farm consists of a set of N fields (1≤N≤105), conveniently numbered 1…N. Between
 * these fields are M bi-directed paths (0≤M≤105), each connecting a pair of fields. The farm
 * contains two barns, one in field 1 and the other in field N. Farmer John would like to ensure
 * that there is a way to walk between the two barns along some series of paths. He is willing to
 * build up to two new paths to accomplish this goal. Due to the way the fields are situated, the
 * cost of building a new path between fields i and j is (i−j)2.
 * 
 * Please help Farmer John determine the minimum cost needed such that barns 1 and N become
 * reachable from each-other.
 * 
 * INPUT FORMAT (input arrives from the terminal / stdin):
 * 
 * Each input test case contains T sub-cases (1≤T≤20), all of which must be solved correctly to
 * solve the input case. The first line of input contains T, after which T sub-test cases follow.
 * 
 * Each sub-test case starts with two integers, N and M. Next, M lines follow, each one containing
 * two integers i and j, indicating a path between two different fields i and j. It is guaranteed
 * that there is at most one path between any two fields, and that the sum of N+M over all sub-test
 * cases is at most 5⋅105.
 * 
 * OUTPUT FORMAT (print output to the terminal / stdout):
 * 
 * Output T lines. The ith line should contain a single integer giving the minimum cost for the ith
 * sub-test case. SAMPLE INPUT:
 * 
 * 2 5 2 1 2 4 5 5 3 1 2 2 3 4 5
 * 
 * SAMPLE OUTPUT:
 * 
 * 2 1 In the first sub-test case, it is optimal to connect fields 2 and 3 with a path, and fields 3
 * and 4 with a path.
 * 
 * In the second sub-test case, it is optimal to connect fields 3 and 4 with a path. No second path
 * is needed,
 */
public class ConnectTwoBarns {
    public static void main(String[] args) {
        Integer[] a = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9,20,30,40};
        int target = 25;
        System.out.println(minDistinaceFromNodeToGroup(target, a));
    }

    /**
     * for a given graph, we can use DFS or BFS to color each connected subgraph. once each subgraph
     * is identified, all nodes within the same subgraph will share the same "color" , color node 1
     * and node N individually, marked as color[0] and color[N-1], then iterate through rest of the
     * nodes to build other color groups Since we can build up to two path, there can be three
     * possibilities 1. node 1 is directly connected to node N, hence no path is needed, cost is
     * zero 2. a path can be build between the a node in color 1's subgraph and node_i, and node_i
     * is connected to N directly, the cost is the min_distance among i and nodes in color 1 group
     * (binary search?) 3. two paths are needed, if this is the case, the connection should be in a
     * color group c, within this group, a node i is providing the shortest distance to color group
     * 1 another node j(possibly the same node) is providing the shortest distinacne to color group
     * N essentially we need to figure out for color group C what is the min distance between C -->
     * 1 and C-->N
     * 
     * @param g
     */
    public static int solution(Graph g, int N) {
        int[] vertexColors = new int[N];
        for (int i = 0; i < N; i++)
            vertexColors[i] = -1;
        bfs_color(g, 0, vertexColors);
        if (vertexColors[N - 1] != 0) {
            bfs_color(g, N - 1, vertexColors);
        } else
            // fully connected already, return 0
            return 0;
        for (int i = 0; i < N; i++) {
            if (vertexColors[i] == -1) {
                bfs_color(g, i, vertexColors);
            }
        }
        // build a color : sortedArrayOfNodes to facilitate binary search when compute min
        Map<Integer, Integer[]> colorGroups = buildColorGroups(vertexColors);
        int[] minDistanceToOne = new int[colorGroups.keySet().size()];
        int[] minDistanceToN = new int[colorGroups.keySet().size()];
        for (int i = 0; i < colorGroups.keySet().size(); i++) {
            minDistanceToOne[i] = computeMinBetweenTwoColors(colorGroups, i, 0);
            minDistanceToN[i] = computeMinBetweenTwoColors(colorGroups, i, N - 1);
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < colorGroups.keySet().size(); i++) {
            minDistance = Math.min(minDistance, minDistanceToOne[i] + minDistanceToN[i]);
        }
        return minDistance;
    }

    public static void bfs_color(Graph g, int color, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(color);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (colors[node] == -1) {
                colors[node] = color;
                queue.addAll(g.getConnectedEdgesForNode(node));
            }
        }
    }

    public static Map<Integer, Integer[]> buildColorGroups(int[] vertexColors) {
        Map<Integer, List<Integer>> tmp = new HashMap<>();
        for (int i = 0; i < vertexColors.length; i++) {
            int color = vertexColors[i];
            if (!tmp.containsKey(color)) {
                tmp.put(color, new ArrayList<Integer>());
            }
            tmp.get(color).add(i);
        }
        Map<Integer, Integer[]> ret = new HashMap<>();
        for (Integer k : tmp.keySet()) {
            List<Integer> v = tmp.get(k);
            Collections.sort(v);
            Integer[] sortedV = v.toArray(new Integer[0]);
            ret.put(k, sortedV);
        }
        return ret;
    }

    public static int computeMinBetweenTwoColors(Map<Integer, Integer[]> colorGroups, int fromColor,
            int toColor) {
        if (fromColor == toColor)
            return 0;
        int minDistance = Integer.MAX_VALUE;
        Integer[] fromColorGroupNodes = colorGroups.get(fromColor);
        Integer[] toColorGroupNodes = colorGroups.get(toColor);
        for (int i = 0; i < fromColorGroupNodes.length; i++) {
            int min = minDistinaceFromNodeToGroup(fromColorGroupNodes[i], toColorGroupNodes);
            minDistance = minDistance < min ? minDistance : min;

        }
        return minDistance;
    }
    
    /**
     * assume sorted value in toGroup use binary search to achieve log(n) complexity
     * @param form
     * @param toGroup
     * @return
     */
    public static int minDistinaceFromNodeToGroup(int from, Integer[] toGroup) {
        int left = 0;
        int right = toGroup.length - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int leftd = Math.abs(from - toGroup[left]);
            int rightd = Math.abs(from - toGroup[right]);
            int midd = Math.abs(from - toGroup[mid]);
            min = Math.min(leftd, rightd);
            min = Math.min(min,midd);
            if (midd == 0)
                return 0;
        
            if (from < toGroup[mid]) {
                //ignore right half of the list
                right = mid;
            } else {
                //ignore left half of the list
                left = mid+1;
            }

        }
        return min;
        
    }

}
