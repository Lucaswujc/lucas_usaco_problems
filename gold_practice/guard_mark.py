"""
Input N , H , N= number of cows in the input, H the height of the catcher
N Lines of input with each line contains three numbers, Height, Weight, Strength
The strenth means the maximum weight the cow can carry on top of itself
Output: by stacking some cows, the total height >= H, and the maximum safety factor,
which is defined as the minimum (strength - weight on top) among all cows in the stack
Analysis:
=========
let dp[mask] be the maximum safety factor when using cows in mask to stack
mask is a bitmask of length N, where bit i is 1 if cow i is included in the mask
Base case: dp[0] = -infinity (no cows, no stack, no safety factor)
Transition:
to compute dp[mask], we can try to add one cow i not in mask to the stack
if adding cow i does not exceed its strength, then
dp[mask] = max(
    dp[mask],
    min(
        dp[mask ^ (1 << i)] - cows[i].weight,
        cows[i].strength
    )
)
The answer will be the maximum dp[mask] among all masks where total height >= H

Notice that the height is directly related to the mask, it does not actually affect the transition
Also to make the program easier to think about and make less mistakes, this program uses two classes
to represent Cow and StackState

In DP programming, it is very easy to make mistakes when trying to track multiple parameters in one DP array
therefore, it is often better to use classes with descriptive names of each parameter
to represent multiple parameters in the DP state, this makes the code more readable and less error-prone

Complexity:
=========
Time: O(N * 2^N)
Space: O(2^N)

"""


class Cow:
    def __init__(self, height, weight, strength):
        self.height = height
        self.weight = weight
        self.strength = strength


class StackState:
    def __init__(self, safety_factor, total_height):
        self.safety_factor = safety_factor
        self.total_height = total_height


N, H = map(int, input().strip().split())
# three parameters for each cow: height, weight, strength
cows = [Cow(*map(int, input().strip().split())) for _ in range(N)]
INF = float("inf")
# dp[mask] = max safety factor using cows in mask to stack
# initialize dp array with no safety factor (-INF) and total height 0
dp = [StackState(safety_factor=-INF, total_height=0) for _ in range(1 << N)]
dp[0] = StackState(
    safety_factor=INF, total_height=0
)  # base case: no cows, infinite safety factor
# Fill the DP table
for mask in range(1, 1 << N):
    for i in range(N):
        if mask & (1 << i) == 0:  # cow i is not in the mask
            continue

        # the mask contains cow i , now find the mask does not contain cow i
        prev_mask = mask ^ (1 << i)
        prev_state = dp[prev_mask]

        # calculate new total height by adding cow i to previous state
        # note that total height is only related to the mask, it does not affect the safety factor transition
        dp[mask].total_height = prev_state.total_height + cows[i].height

        # what if the cow weight is greather than the previos state safety factor?
        # then we cannot add this cow, the min() will be negative or zero
        # the max will ignore this case since dp[mask].safety_factor is initialized to -INF
        new_safety = min(prev_state.safety_factor - cows[i].weight, cows[i].strength)

        if new_safety > dp[mask].safety_factor:
            dp[mask].safety_factor = new_safety


# The answer will be the maximum dp[mask] among all masks where total height >= H
result = -INF
for mask in range(1 << N):
    if dp[mask].total_height >= H:
        result = max(result, dp[mask].safety_factor)
if result == -INF:
    result = -1
print(result)
