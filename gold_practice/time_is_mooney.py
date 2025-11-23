"""
input : N, M, C, N is number of citiies, M is nuimboer of oneway roads, C is the factor in cost to travel
cost to travel is C * T^2

earning money by traveling to each city, money earned is in an array m[], size of m is N, m[i] is money earned when travel to city i
m[0] is always zero
output: max money can be earned when travel from city 1 back to city 1

"""

line = input().strip()
N = int(line.split()[0])
M = int(line.split()[1])
C = int(line.split()[2])
m = []
line = input().strip()
m = [int(x) for x in line.split()]
# graph is represented as adjacency list
edges = {}
for _ in range(M):
    line = input().strip()
    a = int(line.split()[0]) - 1
    b = int(line.split()[1]) - 1
    if a not in edges:
        edges[a] = []
    edges[a].append(b)

MAX_DAYS = 1000
# Initialize dp array, dp[day][city] = max money can be earned when arrive at city on day
dp = [[-float("inf")] * N for _ in range(MAX_DAYS + 1)]
dp[0][0] = 0  # Only city 0 at day 0 is reachable

for day in range(0, MAX_DAYS):
    for city in range(N):
        if dp[day][city] != -float("inf"):
            for next_city in edges.get(city, []):
                dp[day + 1][next_city] = max(
                    dp[day + 1][next_city], dp[day][city] + m[next_city]
                )
        else:
            continue

# Find max profit: money_earned - cost
max_profit = 0
for day in range(MAX_DAYS):
    if dp[day][0] != -float("inf"):
        cost_of_planning = C * (day) * (day)
        print(dp[day][0], cost_of_planning)
        max_profit = max(max_profit, dp[day][0] - cost_of_planning)

print(max_profit)
