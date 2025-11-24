"""
After the last debacle involving Farmer John's circular barn,
one would think he had learned his lesson about non-traditional architecture.
However, he thinks he can still make his circular barn (from the preceding problem)
function properly by allowing multiple cows into each room. To recap,
the barn consists of a ring of n rooms, numbered clockwise from 1…n around
the perimeter of the barn (3≤n≤100). Each room has doors to its two neighboring rooms,
and also a door opening to the exterior of the barn.
Farmer John wants exactly ri cows to end up in room i (1≤ri≤1,000,000).
To herd the cows into the barn in an orderly fashion, he plans to unlock k
exterior doors (1≤k≤7), allowing the cows to enter through only those doors.
Each cow then walks clockwise through the rooms until she reaches a suitable destination
. Farmer John wants to unlock the exterior doors that will cause his cows to collectively
walk a minimum total amount of distance after entering the barn (they can initially
line up however they like outside the k unlocked doors; this does not contribute to the
total distance in question). Please determine the minimum total distance his cows will
need to walk, if he chooses the best k such doors to unlock.

INPUT FORMAT :

The first line of input contains n and k. Each of the remaining n lines contain r1…rn.
OUTPUT FORMAT (file cbarn2.out):

Please write out the minimum amount of distance the cows need to travel.
"""

"""
Anayslsi of the problem:
The strategy comes in the following observation:
To minimize the total walking distance, each cow should walk to the nearest room that needs cows in
the clockwise direction. There is no cheaper way to move the cows.
The optimal placement of the k doors can be found by breaking the circular barn at the room 
with the maximum number of cows. this is because it is obvious that if there is no door  at this 
location, these cows will hace to walk from a door before this location, incurring extra distance cost.
By breaking the circle at this point, we can treat the problem as a linear arrangement of rooms.
We can use dynamic programming to solve the linear arrangement problem.
We define dp[i][j] as the minimum cost to place j doors in the first i rooms.
To compute dp[i][j], we try placing the j-th door at different positions before i.
All rooms from position prev to i-1 will use this door at position prev.
The cost for rooms from prev to i-1 using door at prev is calculated as follows:
cost = sum((room_idx - prev) * rotated[room_idx]) for room_idx in range(prev, i)
The final answer will be dp[n][k], where n is the total number of rooms.
"""

line = input().strip().split()
n, k = int(line[0]), int(line[1])
rooms = []
max_cows = -1
max_cow_room_idx = -1
for _ in range(n):
    cow_for_room = int(input().strip())
    if cow_for_room > max_cows:
        max_cows = cow_for_room
        max_cow_room_idx = _
    rooms.append(cow_for_room)

# with the locaiton of max cow, we can break the circle at that point
# and treat it as a linear arrangement
rotated = rooms[max_cow_room_idx:] + rooms[:max_cow_room_idx]


# dp[i][j] = minimum cost to place j doors in the first i rooms
# INF represents impossible state
INF = float("inf")
dp = [[INF] * (k + 1) for _ in range(n + 1)]
dp[0][0] = 0

# For each position
for i in range(1, n + 1):
    # For each number of doors used so far
    for j in range(1, min(i, k) + 1):
        # Try placing the j-th door at different positions before i
        # All rooms from position prev to i-1 will use this door at position prev
        for prev in range(j - 1, i):
            if dp[prev][j - 1] == INF:
                continue

            # Calculate cost for rooms from prev to i-1 using door at prev
            cost = 0
            for room_idx in range(prev, i):
                # Distance from door (at prev) to room_idx
                distance = room_idx - prev
                cost += distance * rotated[room_idx]

            dp[i][j] = min(dp[i][j], dp[prev][j - 1] + cost)

min_cost = dp[n][k]

print(min_cost)
