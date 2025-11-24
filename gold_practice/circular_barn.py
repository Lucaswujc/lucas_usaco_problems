"""
Circular Barn Problem
N cows, N rooms arranged in a circle
Cows move clockwise only. We want each room to end up with exactly 1 cow.
Cows movement cost is equal to distance squared
Find the minimum total energy all cows need to travel.
Input: N (number of rooms), followed by N lines each containing the number of cows initially in that room.
Output: Minimum total energy traveled by all cows.
"""

"""
Analysis of the problem : the strategy comes in the following observation

Location1           Location 2            Location 3        Location 4      
   |       a           |          b         |          c       |          
   cow1               coow 2
   
from the diagram above, to move cow 1 and cow 2 to location 3 and 4 there are two options
1. move cow 1 to location 3, cow 2 to location 4: cost is (a+b)^2 + (b+c)^2 = a^2 + 2b^2 + c^2 + 2ab + 2bc
2. move cow 1 to location 4, cow 2 to location 3: cost is (a+b+c)^2 + (b)^2 = a^2 + 2b^2 + c^2 + 2ac + 2bc + 2ab

Therefore the moving strategy is to move each cow to the nearest empty room in clockwise direction. there is no cheaper way to move the cows.

Now the question is using this strategy, where do we start on the circle will yield the minimum energy cost?

if we start from location 0, the number of cows that we will have at each location will be 

cows[0:1] -1 , sum(cows[0:2]) -2, sum(cows[0:3]) -3, ..., sum(cows[0:N]) -N 
notice the last term is always 0 since sum(cows[0:N]) = N
so the number of cows at each location is determined by the prefix sum of cows array - k. if we pick the start point of the
circle barn at location i, then the prefix sum array will be rotated accordingly.

by checking the prefix sum array we can find that at certain location, the prefix sum -k value can drop to negative, 
meaning there are empty rooms at that location and there is no cows to fill in to that room , hence the greedy strategy will not work by starting 
from the current start point. 
Using this observation, we need to ensure start from a location and all prefix sum -k values are non negative.
to get this location, all we have to do is to start from zero , compute prefix sum - k for each location, find the location where this value is
minimum, and start from the next location.
The reason that starting from the next location works is that once we identified the lowest point in the prefix sum - k array, starting from the next location
all the computed prefix sum - k should be greater than the lowest point, hence non negative. By setting the next location as starting point, it is effectively
setting the lowest point to be position N-1 , which is always 0.

With this understanding, the algorithm is as follows:
1. compute the prefix sum - k array, identified the lowest location min_index
2. rotate the cows array to start from min_index + 1
3. simulate the greedy moving strategy to compute the total energy cost
"""

N = int(input().strip())
cows = [int(input().strip()) for _ in range(N)]

# Step 1: Compute the prefix sum - k array
prefix_sum = [0] * (N)
prefix_sum[0] = cows[0] - 1
min_prefix_sum = float("-inf")
min_prefix_idx = -1
for i in range(1, N):
    # cows array is zero basied index, so we use (i + 1) to represent k
    prefix_sum[i] = prefix_sum[i - 1] + cows[i] - (i + 1)
    if prefix_sum[i] < min_prefix_sum:
        min_prefix_sum = prefix_sum[i]
        min_prefix_idx = i

# Step 2: Rotate the cows array
cows = cows[min_prefix_idx + 1 :] + cows[: min_prefix_idx + 1]

# Step 3: Simulate the greedy moving strategyc
cow_inventory = cows.copy()
cow_reserve_idx = (
    0  # index to locate the earliest cows taht can be moved to the current location
)
energy_cost = 0
cow_picekedup = [0] * N  # number of cows picked up to the current location
cow_picekedup[0] = cows[0] - 1  # at location 0, pick up cows[0] - 1 cows
for i in range(N):
    # at the location , compute the number of cows that need to be pickedup
    cow_picekedup[i] = cow_picekedup[i - 1] + cows[i] - 1
    # compute the earlist cow that can be placed to this location
    # increase cow_reserve_idx until we find a location that has cows
    while cow_inventory[cow_reserve_idx] == 0:
        cow_reserve_idx += 1
    cow_inventory[cow_reserve_idx] -= 1
    # compute the energy cost to move the cow from cow_reserve_idx to location i
    distance = i - cow_reserve_idx
    energy_cost += distance * distance

print(energy_cost)
