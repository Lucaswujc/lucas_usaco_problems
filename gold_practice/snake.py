import math
# use a dp[i][r] to keep track of the 
# sun of the min net size for the ith cow with the r changes of net sizes 
# to calculate the waste, we need to track the 
# cumulative sum of wasted space

line = input()
N = int(line.strip().split()[0])
K = int(line.strip().split()[1])
snakes = [ int(s) for s in input().strip().split()]

dp = [[0 for _ in range(K+1)] for _ in range(N)]
i = 0
r = 0
net_size = 0
for snake in snakes:
    net_size = max(net_size, snake)
    dp[i][0] = net_size * (i+1)
    for r in range(1, K+1):
        # now calculate for each r, what would the min net size 
        # by looking back from 0 .. i
        simulated_net_size = snake
        dp[i][r] = 10**6 + 1 # hack for max snake size
        for j in range(i-1, 0, -1):
            dp[i][r] = min (dp[i][r], dp[j][r-1]+ simulated_net_size* (i-j))
            simulated_net_size = max(simulated_net_size, snakes[j])
        # now we need to calculate the min net size for the ith cow
        dp[i][r] = dp[i-1][r-1] + net_size
    i += 1
print(dp[N-1][K] - sum(snakes))
