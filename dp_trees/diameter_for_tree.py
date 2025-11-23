# Given a tree T of N nodes, calculate longest path between any two nodes(also known as diameter of tree).
# The problem can be solved using dynamic programming.
# he diameter of a tree is the length of the longest path between any two nodes in the tree.
# The path may or may not pass through the root node.
# The diameter can be found using a depth-first search (DFS) approach.
# The reason for tracking the top 2 largest distances (max1 and max2) from each node in the DFS
# is that the diameter of a tree may pass through any node, not just the root.
# Here's why this works:
# For any node, the longest path passing through it is formed by taking the two longest paths
# from that node to its descendants (i.e., the two deepest subtrees), and connecting them through
# the node itself.
# By keeping the top 2 largest distances from each node to its descendants, you can compute the
# longest path that passes through that node as max1 + max2.
# The overall diameter is the maximum of these values across all nodes.


from typing import List, Tuple
from collections import defaultdict


def diameter_of_tree(n: int, edges: List[Tuple[int, int]]) -> int:
    # Build the tree as an adjacency list
    tree = defaultdict(list)
    for u, v in edges:
        tree[u].append(v)
        tree[v].append(u)

    # To store the maximum distance from a node
    def dfs(node: int, parent: int) -> Tuple[int, int]:
        max1, max2 = 0, 0  # Two largest distances
        for neighbor in tree[node]:
            if neighbor == parent:
                continue
            dist = dfs(neighbor, node)[0] + 1  # Distance to this neighbor
            if dist > max1:
                max2 = max1
                max1 = dist
            elif dist > max2:
                max2 = dist
        return max1, max2

    # Start DFS from any node (0 in this case)
    max_distance = 0
    for i in range(n):
        d1, d2 = dfs(i, -1)
        max_distance = max(max_distance, d1 + d2)

    return max_distance


# First DFS: Start from any node (e.g., node 0) to find the farthest node from it (let's call
# this node u).
# Second DFS: Start from u to find the farthest node from u. The distance between u and this
# farthest node is the diameter.
# This works because the longest path in a tree always connects two leaf nodes, and starting
# from any node, the farthest node you reach is one end of the diameter.
from typing import List, Tuple
from collections import defaultdict


def diameter_of_tree2(n: int, edges: List[Tuple[int, int]]) -> int:
    tree = defaultdict(list)
    for u, v in edges:
        tree[u].append(v)
        tree[v].append(u)

    def dfs(node: int, parent: int, depth: int) -> Tuple[int, int]:
        farthest_node, max_depth = node, depth
        for neighbor in tree[node]:
            if neighbor == parent:
                continue
            next_node, next_depth = dfs(neighbor, node, depth + 1)
            if next_depth > max_depth:
                farthest_node, max_depth = next_node, next_depth
        return farthest_node, max_depth

    # First DFS to find one end of the diameter
    far_node, _ = dfs(0, -1, 0)
    # Second DFS from far_node to find the diameter
    _, diameter = dfs(far_node, -1, 0)
    return diameter
