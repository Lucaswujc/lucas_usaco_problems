"""
Given a permutation A of length N, the cows change their order such that the i-th cow from the
left before the change is Ai-th from the left after the change.
For example, if A=(1,2,3,4,5) then the cows perform one step.
If A=(2,3,1,5,4), then the cows perform six steps.
The order of the cows from left to right after each step is as follows:

0 steps: (1,2,3,4,5)
1 step: (3,1,2,5,4)
2 steps: (2,3,1,4,5)
3 steps: (1,2,3,5,4)
4 steps: (3,1,2,4,5)
5 steps: (2,3,1,5,4)
6 steps: (1,2,3,4,5)

Find the sum of all positive integers K such that there exists a permutation
of length N that requires the cows to take exactly K steps.


"""

"""
Analysis : 
in the above example, we can see that the permutation A = (2,3,1,5,4) can be decomposed into two cycles
(1,2,3) and (4,5)
the first cycle has length 3, the second cycle has length 2.
to return to the original order, the first cycle needs 3 steps, the second cycle needs 2 steps.
the overall permutation needs lcm(3, 2) = 6 steps to return to original order.
Therefore, the problem reduces to finding all possible lcm values of cycle lengths that sum up to N.
for example, for N = 5, the possible cycle length partitions are:
5 -> lcm(5) = 5
4 + 1 -> lcm(4, 1) = 4
3 + 2 -> lcm(3, 2) = 6
3 + 1 + 1 -> lcm(3, 1, 1) = 3
2 + 2 + 1 -> lcm(2, 2, 1) = 2
2 + 1 + 1 + 1 -> lcm(2, 1, 1, 1) = 2
1 + 1 + 1 + 1 + 1 -> lcm(1, 1, 1, 1, 1) = 1
hence the possible K values are 1, 2, 2, 3, 4, 5, 6
sum is 21

we can use dynamic programming to find all possible lcm values of cycle lengths that sum up to N.
define dp[i] as the set of all possible lcm values of cycle lengths that sum up to i.
to compute dp[i], we can try adding a cycle of length j (1 <= j <= i) to all partitions in dp[i-j].
the new lcm value will be lcm(old_lcm, j)
the base case is dp[0] = {1} (the lcm of an empty set is defined as 1)
the answer will be sum of all values in dp[N]  

"""
from math import lcm

N, MOD = map(int, input().strip().split())
dp = [set() for _ in range(N + 1)]
# intiial case, when there is zero elements, lcm is 1 step to return to original
dp[0] = set([1])
for i in range(1, N + 1):
    dp[i] = set()
    for j in range(0, i):
        dpprev = dp[j].copy()
        diff = i - j
        new_lcm = set([lcm(x, diff) for x in dpprev])
        dp[i].update(new_lcm)
result = sum(dp[N]) % MOD
print(result)
