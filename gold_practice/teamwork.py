"""
input N , K , N is numer of cows, K is max members in a single team
, followed by N lines each containing the skill level of a cow.
output: maxium sum of total skills for all teams formed such that no team has more than K members.
and team has to be formed by consecutive cows in the input list.


"""

line = input().strip().split()
N, K = int(line[0]), int(line[1])
skills = []
for _ in range(N):
    skills.append(int(input().strip()))

# dp[i] = maximum sum of skills up to the  ith cow
# then for each cow i, we consider forming a team ending at i with size j (1 <= j <= K)
# and calculate the contribution of that team
# the max skill will be max(dp[i], dp[i-j] + max_skill * j)

dp = [0] * (N + 1)

for i in range(N):
    # Try forming a team ending at position i with size j
    for j in range(min(K, i + 1)):
        # Team includes cows from position (i-j) to i
        # Find the max skill in this team, python slice is exclusive at the end
        max_skill = max(skills[i - j : i + 1])
        # Total contribution is max_skill * team_size
        dp[i] = max(dp[i], dp[i - j - 1] + max_skill * (j + 1))

print(dp[N - 1])
