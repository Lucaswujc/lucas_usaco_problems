"""
Input N = number of cows
Then N line of cow position in the straight line
Output: minimal total cost of visiting all cows starting from position 0
starting position is 0
travel speed is 1 unit per second
cow cost 1 dollar per second before being visited
Analysis:
cows are on a straight line, therefore, if fj visit cow from i to j (assuming i <= j),  it will
cover all cows between i and j, and the cost to visit in this trip is j-i * (number of unvisited cows)
the number of unvisited cows is N - (j - i)
then to get minimum, it matters whether FJ visited cow at position i first or cow at position j first.
i.e. whether FJ is on the left or right end of the segment when covering cows from i to j

let dp[i][j][k] be the minimum cost to visit cows from position i to position j, (assuming i <= j)
where k = 0 means FJ is at position j after visiting cows from i to j, i.e. FJ is on the left side outside the segment
k = 1 means FJ is at position i after visiting cows from j to i, i.e. FJ is on the right side outside the segment

then to compute dp[i][j][k], we have two options:
let the remaining unvisited cows be N - (j - i) = R
1. visit cow at position i first, then FJ will be at position j after visiting cows from i to j, i.e. FJ is on the left side outside the segment
   dp[i][j][0] = min(
       #  both on the left side of interval
       dp[i+1][j][0] + R * abs(cows[i+1] - cows[i]),
       # the transition to the right side of interval
       dp[i+1][j][1] + R * abs(cows[j] - cows[i])
2. visit cow at position j first, then FJ will be at position i after visiting cows from j to i, i.e. FJ is on the right side outside the segment
   dp[i][j][1] = min(
       dp[i][j-1][0] + R * abs(cows[j-1] - cows[j]),
       dp[i][j-1][1] + R * abs(cows[j] - cows[i]))
The answer will be min(dp[0][N-1][0], dp[0][N-1][1])

"""

N = int(input().strip())
# starting postion is zero, fill in position 0 at index 0
cows = sorted([int(input().strip()) for _ in range(N)])

INF = float("inf")
# dp[i][j][k] = minimum cost to cover cows from position i to position j
dp = [[[INF] * 2 for _ in range(N + 1)] for _ in range(N + 1)]


# Fill the DP table
for i in range(N - 1, -1, -1):
    for j in range(i, N):
        if i == j:
            dp[i][i][0] = abs(cows[i] - 0) * N
            dp[i][i][1] = abs(cows[i] - 0) * N
            continue
        R = N - (j - i)
        # Option 1: visit cow at position i first
        dp[i][j][0] = min(
            dp[i + 1][j][0] + R * abs(cows[i + 1] - cows[i]),
            dp[i + 1][j][1] + R * abs(cows[j] - cows[i]),
        )
        # Option 2: visit cow at position j first
        dp[i][j][1] = min(
            dp[i][j - 1][0] + R * abs(cows[j] - cows[i]),
            dp[i][j - 1][1] + R * abs(cows[j - 1] - cows[j]),
        )
# The answer will be min(dp[0][N-1][0], dp[0][N-1][1])
result = min(dp[0][N - 1][0], dp[0][N - 1][1])
print(result)
