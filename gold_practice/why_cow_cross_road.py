"""
cow cross road
Input : N number of breeds of cows
then N lines of breeds on the left side of the road
then N lines of breeds on the right side of the road
Output: max number of cross walks can build without crossing each other
cross walks can connect breeds from left to right side as long as abs(breed1 - breed2) <= 4
cross walks cannot cross each other, cannot share same start or end point on either side of the road

analysis:
this can be done using dynmaic programing
let dp[i][j] = max number of cross walks can build using first i breeds on left side and first j breeds on right side
base case: dp[0][0] = 0
transition:
to compute dp[i][j], we have two options:
1. do not connect left[i-1] with right[j-1], then dp[i][j] = max(dp[i-1][j], dp[i][j-1])
2. connect left[i-1] with right[j-1] if abs(left[i-1] - right[j-1]) <= 4, then dp[i][j] = dp[i-1][j-1] + 1
we take the maximum of the two options
the answer will be dp[N][N]
"""

N = int(input().strip())
left = [int(input().strip()) for _ in range(N)]
right = [int(input().strip()) for _ in range(N)]
INF = float("inf")
# dp[i][j] = max number of cross walks can build using first i breeds on left side and first j breeds on right side
dp = [[0] * (N + 1) for _ in range(N + 1)]
# Fill the DP table
for i in range(1, N + 1):
    for j in range(1, N + 1):
        # Option 1: do not connect left[i-1] with right[j-1]
        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
        # Option 2: connect left[i-1] with right[j-1] if possible
        if abs(left[i - 1] - right[j - 1]) <= 4:
            dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + 1)
# The answer will be dp[N][N]
result = dp[N][N]
print(result)
