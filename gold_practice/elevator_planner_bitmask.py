"""
Elevator planning problem
N = number of people
X = Max weight capacity of the elevator
List of weights of each person
Output: minimum number of elevator rides to get all people to the top floor
Analysis:
=========
let dp[mask] be the minimum number of rides needed to get all people in mask to the top floor
mask is a bitmask of length N, where bit i is 1 if person i is included in the mask
Base case: dp[0] = 0
Transition:
to compute dp[mask], we can try to add one person i not in mask to the elevator ride
if adding person i does not exceed weight limit X, then
dp[mask | (1 << i)] = min(dp[mask | (1 << i)], dp[mask])
else
dp[mask | (1 << i)] = min(dp[mask | (1 << i)], dp[mask] + 1)
The answer will be dp[(1 << N) - 1]
Complexity:
==========
Time: O(N * 2^N)
Space: O(2^N)
"""

N, X = map(int, input().strip().split())
weights = list(map(int, input().strip().split()))
INF = float("inf")
# dp[mask] = (min number of rides to get all people in mask to top floor, current weight in the last ride)
dp = [(INF, 0)] * (1 << N)
dp[0] = (
    1,
    0,
)
# base case: no people, 1 ride with 0 weight, the elevator is empty, it is ready to be filled by adding next person,
# if we set zero as initial state, then the result will be offset by -1, initial state is that elevator can take 1 ride, untill it needs to
# be transitioned to +1
# use tuple here to track the current weight in the last ride, python min compare tuple lex order

for mask in range(1 << N):
    rides, curr_weight = dp[mask]
    for i in range(N):
        if not (mask & (1 << i)):  # person i not in mask
            new_mask = mask | (1 << i)
            if curr_weight + weights[i] <= X:
                # add person i to current ride
                new_state = (rides, curr_weight + weights[i])
            else:
                # start a new ride with person i
                new_state = (rides + 1, weights[i])
            dp[new_mask] = min(dp[new_mask], new_state)
# The answer will be dp[(1 << N) - 1][0]
result = dp[(1 << N) - 1][0]
print(result)


"""


"""
