import math
# use a dp[i][j] to keep track of the
# sum of the min net size for the ith cow with the j changes of net sizes
# then dp[i][0] is the min net size with no changes
# dp[i][0] = (i+1) * max(snakes[0..i])
# dp[i][j] = min over all prev snakes with j-1 changes + sum(net size from prev+1 to i)

line = input()
N = int(line.strip().split()[0])
K = int(line.strip().split()[1])
snakes = [int(s) for s in input().strip().split()]


dp = [[0 for _ in range(K + 1)] for _ in range(N)]
for i in range(N):
    dp[i][0] = max(snakes[0 : i + 1]) * (i + 1)
    for j in range(1, K + 1):
        dp[i][j] = math.inf
        for prev in range(i):
            # python slice is exclusive at the end, hence i+1
            max_snake = max(snakes[prev + 1 : i + 1])
            # size of the team from prev+1 to i
            team_size = i - prev

            dp[i][j] = min(dp[i][j], dp[prev][j - 1] + max_snake * team_size)
print(dp[N - 1][K] - sum(snakes))
