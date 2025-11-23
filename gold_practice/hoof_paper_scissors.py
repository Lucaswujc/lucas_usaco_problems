"""
line input: N, K, N is number of FJ gesture, K max time of times cow switch gesture,
output: max num of games cow can win
The idea is to use dynamic programming to keep track of the maximum number of wins
the cow can achieve up to each game, given the number of switches used and the current
gesture. We represent gestures as integers: 0 for Hoof (H), 1 for Paper (P), and 2 for Scissors (S).
We create a 3D DP array where dp[i][k][g] represents the maximum number of wins
up to game i, with k switches used, and current gesture g.
We iterate through each game, updating the DP array based on whether the cow wins the game
with the current gesture or switches to a different gesture. Finally, we find the maximum wins possible after all games
considering all possible gestures and switch counts.
1. Initialize a 3D DP array with dimensions (N) x (K+1) x 3, filled with zeros.
2. Iterate through each game, updating the DP array based on whether the cow wins the game
    with the current gesture or switches to a different gesture.
3. Finally, find the maximum wins possible after all games considering all possible gestures and switch counts.
4. Return the maximum wins found.
5. Time complexity is O(N*K), where N is the number of games and K is the maximum number of switches allowed.
6. Space complexity is O(N*K*3) due to the 3D DP array.
"""

line = input().strip()
N = int(line.split()[0])
K = int(line.split()[1])
moves = []


for _ in range(N):
    line = input().strip()
    match line:
        case "H":
            moves.append(0)
        case "P":
            moves.append(1)
        case "S":
            moves.append(2)
# Initialize the DP array, here, we intentially make K+2 and N+1 to avoid index error when k+1 and i+1
# the final check condition is at dp[N][K][g] where g is the gesture from 0 to 2
dp = [[[0 for _ in range(3)] for _ in range(K + 2)] for _ in range(N + 1)]
for i in range(N):
    for k in range(K + 1):
        for g in range(3):
            # winning condition, g - moves[i] == 1 or (g == 0 and moves[i] == 2) or (g == 2 and moves[i] == 1)
            # this can be simplified to: g - moves[i] == 1 or (g == 0 and moves[i] == 2)
            # further simplified to: (g - moves[i] + 3) % 3 == 1
            if (g - moves[i] + 3) % 3 == 1:
                dp[i][k][g] += 1
            dp[i + 1][k + 1][0] = max(dp[i + 1][k + 1][0], dp[i][k][g])
            dp[i + 1][k + 1][1] = max(dp[i + 1][k + 1][1], dp[i][k][g])
            dp[i + 1][k + 1][2] = max(dp[i + 1][k + 1][2], dp[i][k][g])
            dp[i + 1][k][g] = max(dp[i + 1][k][g], dp[i][k][g])

max_win = 0
for g in range(3):
    max_win = max(max_win, dp[N][K][g])
print(max_win)
