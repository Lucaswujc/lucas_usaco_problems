"""
There are n cities and m flight connections between them. 
You want to travel from Syrjälä to Lehmälä
so that you visit each city exactly once. How many possible routes are there?
Input
The first input line has two integers n and m: the number of cities and flights.
The cities are numbered 1,2,...,n. City 1 is Syrjälä, and city n is Lehmälä.
Then, there are m lines describing the flights. Each line has two integers a and b:
there is a flight from city a to city b. All flights are one-way flights.
Output
Print one integer: the number of routes modulo 10^9+7.
Constraints

2 <= n <= 20
1 <= m <= n^2
1 <= a,b <= n

Example
Input:
4 6
1 2
1 3
2 3
3 2
2 4
3 4

Output:
2

Analysis:
             1 ---> 2
              \     ^ \
               \    |  \
                \   |   \
                  v |    v  
                    3 -> 4

let dp[mask][to_city] be the number of ways to reach to_city by visiting all cities in mask
base case: dp[1][1] = 1 (starting at city 1, visited only city 1)
transition:
dp[mask][to_city] = sum of dp[mask ^(1<<(to_city-1)][from] for all "from"s connected to to_city
this works because to reach to_city, we have sum up all the ways end in "from" cities
notice that the graph may contain circles, and we can only each city only once, 
therefore, the paths connected to the from_city should exclude the to_city in the mask
The answer will be dp[(1<<N)-1][N], i.e. all cities visited, end at city N

Note: the city is 1 based, but the bit mask is zero based, hence the shift by (to_city - 1)
also the size of the dp array is (1<<N) x (N+1) to accomodate all possible masks and city numbers
"""

N, E = map(int, input().strip().split())
edges = {}
# build reverse graph for easier dp transition
# notice that we need to find all "from" cities that can reach city c
for _ in range(E):
    a, b = map(int, input().strip().split())
    edges.setdefault(b, []).append(a)

dp = [[0] * (N + 1) for _ in range(1 << N)]
dp[1][1] = 1  # starting at city 1, visited only city 1

for mask in range(2, 1 << N):  # from second city to last city
    if mask & (1 << (N - 1)) and mask != (1 << N) - 1:
        # if last city is in the mask, it must be the full mask
        continue
    for to_city in range(1, N + 1):  # current destination city
        for from_city in edges.get(to_city, []):  # next city
            if mask & (1 << (from_city - 1)):  # if the from city is already in the mask
                dp[mask][to_city] += dp[mask - (1 << (to_city - 1))][from_city]

result = dp[(1 << N) - 1][N]
print(result)
