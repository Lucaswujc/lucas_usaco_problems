"""
Taming the Herd
================
FJ start log when first break out happens, he recorded the counter on each day the number of days since last break out.
for example
day         0, 1, 2, 3, 4, 5
break       T, F, F, T, T, T
counter     0, 1, 2, 3, 1, 1
Cows can temper the log entry,
If the log is 1 1 2 0 0 1
the number of tempered entries is 2

Find the minimum number of tampered entries if we know the total break outs is K where K is between 1 and N inclusive
Input format
N = number of days log recorded
log entries for N days
Output format N lines, each line i is the minimum number of tampered entries if there are i break outs , i between 1 and N inclusive

"""

"""
Analysis : 
for each possible number of break outs k from 1 to N do the following : 

Define dp[i][j] as the minimum number of tampered entries in the first i days with j break outs
To compute dp[i][j], we try placing the j-th break out at different positions before i.
All days from position prev to i-1 will use this break out at position prev.
The number of tampered entries for days from prev to i-1 using break out at prev is calculated as follows:
tampered = number of days x in [prev, i-1] where log_entries[x] != (x - prev)
The answer will be dp[n][k], where n is the total number of days.
"""

N = int(input().strip())
log_entries = [int(x) for x in input().strip().split()]
INF = float("inf")


def solve_for_k_breakouts(n, k, log_entries):
    # dp[i][j] = minimum tampered entries in first i days with j break outs
    dp = [[INF] * (k + 1) for _ in range(n + 1)]
    dp[0][0] = 0

    for i in range(1, n + 1):
        for j in range(1, k + 1):
            for prev in range(i):
                tampered = 0
                for day in range(prev, i):
                    if log_entries[day] != (day - prev):
                        tampered += 1
                dp[i][j] = min(dp[i][j], dp[prev][j - 1] + tampered)

    return dp[n][k]


for k in range(1, N + 1):
    result = solve_for_k_breakouts(N, k, log_entries)
    print(result)
