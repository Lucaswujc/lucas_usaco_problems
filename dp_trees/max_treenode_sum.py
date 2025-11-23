#
# The first problem we solve is as follows:
# Given a tree T of N nodes, where each node i has Ci coins attached with it.
# You have to choose a subset of nodes such that no two adjacent nodes
# (i.e. nodes connected directly by an edge) are chosen and sum of coins attached
# with nodes in chosen subset is maximum.
# The problem can be solved using dynamic programming.
from typing import List, Tuple


def max_treenode_sum(n: int, edges: List[Tuple[int, int]], coins: List[int]) -> int:
    from collections import defaultdict

    # Build the tree as an adjacency list
    tree = defaultdict(list)
    for u, v in edges:
        tree[u].append(v)
        tree[v].append(u)

    # Dynamic programming arrays
    dp = [
        [0, 0] for _ in range(n)
    ]  # dp[i][0] = max sum excluding i, dp[i][1] = max sum including i

    def dfs(node: int, parent: int):
        dp[node][1] = coins[node]  # Include this node's coins
        for neighbor in tree[node]:
            if neighbor == parent:
                continue
            dfs(neighbor, node)
            dp[node][0] += max(dp[neighbor][0], dp[neighbor][1])  # Exclude this node
            dp[node][1] += dp[neighbor][0]  # Include this node

    dfs(0, -1)  # Start DFS from node 0 with no parent

    return max(
        dp[0][0], dp[0][1]
    )  # Return the maximum of including or excluding the root node
