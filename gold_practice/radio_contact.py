"""
Input : N, M, number of moves for Farmer John and Bessie
2 lines, each line is (x,y) as the starting position of FJ and Bessie
String length of N and M respectively, consisting of characters 'N', 'E', 'S', 'W'
Output: minimum energy required for FJ and Bessie can use during their moves
Energy is defined as the sum of squared distance between FJ and Bessie after each move
Analysis:
=========
let dp[i][j] be the minimum energy used when FJ has made i moves and Bessie has made j moves
Base case: dp[0][0] = 0
Transition:
to compute dp[i][j], we have three options:
1. FJ makes the i-th move, Bessie stays at j-th position
   dp[i][j] = dp[i-1][j] + cost(FJ[i], Bessie[j])
2. Bessie makes the j-th move, FJ stays at i-th position
   dp[i][j] = dp[i][j-1] + cost(FJ[i], Bessie[j])
3. both FJ and Bessie make their respective moves
   dp[i][j] = dp[i-1][j-1] + cost(FJ[i], Bessie[j])
The answer will be dp[N][M]
"""

N, M = map(int, input().strip().split())
FJ_start = tuple(map(int, input().strip().split()))
Bessie_start = tuple(map(int, input().strip().split()))
FJ_moves = input().strip()
Bessie_moves = input().strip()
INF = float("inf")
# Precompute positions after each move
FJ_positions = [FJ_start]
Bessie_positions = [Bessie_start]
move_map = {"N": (0, 1), "E": (1, 0), "S": (0, -1), "W": (-1, 0)}
for move in FJ_moves:
    last_pos = FJ_positions[-1]
    delta = move_map[move]
    new_pos = (last_pos[0] + delta[0], last_pos[1] + delta[1])
    FJ_positions.append(new_pos)
for move in Bessie_moves:
    last_pos = Bessie_positions[-1]
    delta = move_map[move]
    new_pos = (last_pos[0] + delta[0], last_pos[1] + delta[1])
    Bessie_positions.append(new_pos)
# Initialize DP table
dp = [[INF] * (M + 1) for _ in range(N + 1)]
dp[0][0] = 0


# Function to compute squared distance
def cost(pos1, pos2):
    return (pos1[0] - pos2[0]) ** 2 + (pos1[1] - pos2[1]) ** 2


# Fill the DP table
for i in range(N + 1):
    for j in range(M + 1):
        if i > 0:
            dp[i][j] = min(
                dp[i][j],
                dp[i - 1][j] + cost(FJ_positions[i], Bessie_positions[j]),
            )
        if j > 0:
            dp[i][j] = min(
                dp[i][j],
                dp[i][j - 1] + cost(FJ_positions[i], Bessie_positions[j]),
            )
        if i > 0 and j > 0:
            dp[i][j] = min(
                dp[i][j],
                dp[i - 1][j - 1] + cost(FJ_positions[i], Bessie_positions[j]),
            )
print(dp[N][M])
