"""
input N and M , N is number of cows, M is number of edges
next M lines contain two integers u and v representing an undirected edge between cow u and cow v
output: number of operations (add an edge or remove an edge) needed to ensure if a and b are connected directly, a or b
is also connected with any other cow c in the graph
Sample Input:
3 1
1 2
Output:
1
Explanation:
we need one more edge connect cow 3 to either cow 1 or cow 2

Sample Input:
3 2
3 1
2 3
Output:0
Explanation:the graph is already connected

Sample Input:
4 4
1 2
1 3
1 4
2 3
Output:1
Explanation:
we can remove one edge, for example edge (2,3) to ensure the property holds
or
since 2 and 3 are connected directly and both connected to 1, we can add an edge between 2 and 4 or 3 and 4

Analysis:
=========
The problem requires us to ensure that if two cows are directly connected,
then at least one of them is also connected to any other cow in the graph.
let dp[mask] be the minimum number of operations needed to ensure the property holds
for the subset of cows represented by mask
base case: dp[0] = 0
transition:
to compute dp[mask],
if we want to add cow i to the subset, we need to check how many operations to connect i to all other cows
or remove the cow i from the mask , the result is the minimum of these two operations
let's say prev_mask = mask ^ (1 << i) , i.e. exclude cow i from mask
then to add i to the subset represented by prev_mask, we need to do the following operations:
1. for each cow j in prev_mask that is directly connected to i, we need to ensure
   that either i or j is connected to all other cows in prev_mask
   this can be done by adding an edge between i and any other cow in prev_mask that is not connected to j
   or removing the edge between i and j
   the cost of this operation is min(k, l - k) where k is the number of cows in prev_mask that are directly connected to i
   and l is the total number of cows in prev_mask
Therefore, for each cow i in mask, we can compute the cost of adding i to prev_mask
and update dp[mask] = min(dp[mask], dp[prev_mask] + cost)
The final answer will be dp[(1 << N) - 1] , i.e. the minimum number of operations needed for all cows

Now to quickly compute the number of cows in prev_mask that are directly connected to i,
we can use a bitmask to represent the connections of each cow
we can encode the connections of each cow i as a bitmask conn[i] = sum(1 << j) for each cow j that is directly connected to i
then to compute the number of cows in prev_mask that are directly connected to i,
we can use the bitwise AND operation: connected_cows = conn[i] & prev_mask
then the number of connected cows is simply the number of set bits in connected_cows
"""

N, M = map(int, input().split())
connections = [0] * N
for _ in range(M):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    # build the bitmask for connections
    connections[u] |= 1 << v
    connections[v] |= 1 << u

INF = 10**9
dp = [INF] * (1 << N)
# base case, no cows, no operations needed
dp[0] = 0
for mask in range(1 << N):
    for i in range(N):
        if (mask & (1 << i)) != 0:
            prev_mask = mask ^ (1 << i)
            # count number of cows in prev_mask that are directly connected to i
            connected_cows = connections[i] & prev_mask
            k = bin(connected_cows).count("1")
            l = bin(prev_mask).count("1")
            cost = min(k, l - k)
            dp[mask] = min(dp[mask], dp[prev_mask] + cost)
print(dp[(1 << N) - 1])
