"""
Cow Checklistq
================
FJ visit all H and G cows in incremental order,start with H cow 0 , end with H cow H-1
cost of travel a distaince D is D^2 energy(distance squared) D^2 = (x2 - x1)^2 + (y2 - y1)^2
input H and G, number of H and G cows
then H lines of x, y coordinate of H cow
then G lines of x, y coordinate of G cow
output: minimum total energy needed to visit all H and G cows
================

Analysis:
==========
3 2
0 0
1 0
2 0
0 3
1 3

define dp[i][j] as the minimum energy needed to visit first i H cows and first j G cows
result is in dp[H][G][last], where last is either H or G cow
base case: dp[0][0] = 0
transition:
to compute dp[i][j], we have two options:
1. last visited cow is H cow i-1, then we must have visited first i-1 H cows and first j G cows
   dp[i][j] = dp[i-1][j] + cost(H[i-2], H[i-1]) if i > 0
2. last visited cow is G cow j-1, then we must have visited first i H cows and first j-1 G cows
   dp[i][j] = dp[i][j-1] + cost(G[j-2], G[j-1]) if j > 0
we take the minimum of the two options
"""

H, G = map(int, input().strip().split())
H_cows = [tuple(map(int, input().strip().split())) for _ in range(H)]
G_cows = [tuple(map(int, input().strip().split())) for _ in range(G)]
INF = float("inf")

# dp[i][j][0] = min energy after visiting H cows [0..i-1] and G cows [0..j-1], currently at H cow i-1
# dp[i][j][1] = min energy after visiting H cows [0..i-1] and G cows [0..j-1], currently at G cow j-1
dp = [[[INF, INF] for _ in range(G + 1)] for _ in range(H + 1)]

# FJ starts at H cow 0
dp[1][0][0] = 0  # visited H[0], no G cows yet, currently at H[0]


def cost(cow1, cow2):
    return (cow1[0] - cow2[0]) ** 2 + (cow1[1] - cow2[1]) ** 2


# Fill the DP table
for i in range(H + 1):
    for j in range(G + 1):
        # Transition 1: Move to next H cow (visit H[i])
        if i < H:
            # From H cow i-1 to H cow i
            if i > 0 and dp[i][j][0] < INF:
                dp[i + 1][j][0] = min(
                    dp[i + 1][j][0], dp[i][j][0] + cost(H_cows[i - 1], H_cows[i])
                )
            # From G cow j-1 to H cow i
            if j > 0 and dp[i][j][1] < INF:
                dp[i + 1][j][0] = min(
                    dp[i + 1][j][0], dp[i][j][1] + cost(G_cows[j - 1], H_cows[i])
                )

        # Transition 2: Move to next G cow (visit G[j])
        if j < G:
            # From H cow i-1 to G cow j
            if i > 0 and dp[i][j][0] < INF:
                dp[i][j + 1][1] = min(
                    dp[i][j + 1][1], dp[i][j][0] + cost(H_cows[i - 1], G_cows[j])
                )
            # From G cow j-1 to G cow j
            if j > 0 and dp[i][j][1] < INF:
                dp[i][j + 1][1] = min(
                    dp[i][j + 1][1], dp[i][j][1] + cost(G_cows[j - 1], G_cows[j])
                )

# Must end at the last H cow (H[H-1])
result = dp[H][G][0]
print(result)
