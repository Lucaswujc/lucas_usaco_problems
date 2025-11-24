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

import os
from pathlib import Path

"""
Analysis of the problem:
The strategy comes in the following observation:
if we know the location to break the circle, i.e. where the first door is placed, 
then we can treat the circular barn as a linear arrangement of rooms.
We can use dynamic programming to solve the linear arrangement problem.
We define dp[i][j] as the minimum cost to place j doors in the first i rooms.
To compute dp[i][j], we try placing the j-th door at different positions before i.
All rooms from position prev to i-1 will use this door at position prev.
The cost for rooms from prev to i-1 using door at prev is calculated as follows:
cost = sum((room_idx - prev) * rotated[room_idx]) for room_idx in range(prev, i)
The answer will be dp[n][k], where n is the total number of rooms.

With this said, we can repeat doing this for each possible breaking point,
and take the minimum cost among all breaking points.

"""


def process_input(input_source=None):
    """
    Process input from either stdin or a file.

    Args:
        input_source: None (for stdin), file path string, or file-like object

    Returns:
        tuple: (n, k, rooms, max_cow_room_idx)
    """
    if input_source is None:
        # Read from stdin
        lines = []
        try:
            while True:
                lines.append(input())
        except EOFError:
            pass
    elif isinstance(input_source, str):
        # Read from file path
        with open(input_source, "r") as f:
            lines = [line.strip() for line in f.readlines()]
    else:
        # Assume it's a file-like object
        lines = [line.strip() for line in input_source.readlines()]

    # Parse the input
    line = lines[0].strip().split()
    n, k = int(line[0]), int(line[1])
    rooms = []
    max_cows = -1
    max_cow_room_idx = -1
    for i in range(n):
        cow_for_room = int(lines[i + 1].strip())
        if cow_for_room > max_cows:
            max_cows = cow_for_room
            max_cow_room_idx = i
        rooms.append(cow_for_room)
    return n, k, rooms, max_cow_room_idx


def solve_linear_barn(rotated, n, k):
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
    return dp[n][k]


def solve_circular_barn(n, k, rooms):
    min_cost = float("inf")
    for i in range(len(rooms)):
        rotated = rooms[i:] + rooms[:i]
        min_cost = min(min_cost, solve_linear_barn(rotated, n, k))
    return min_cost


def get_test_files(directory="~/Downloads/cbarn2_gold_feb16/"):
    """
    Get all .in and .out files from the specified directory.

    Args:
        directory: Path to the directory (default: ~/Downloads/cbarn2_gold_feb16/)

    Returns:
        tuple: (list of .in files, list of .out files)
    """
    # Expand the ~ to the home directory
    dir_path = Path(directory).expanduser()

    # Check if directory exists
    if not dir_path.exists():
        return [], []

    # Get all .in files
    in_files = sorted(dir_path.glob("*.in"))

    # Get all .out files
    out_files = sorted(dir_path.glob("*.out"))

    return in_files, out_files


def main(input_source=None):
    """
    Main function to solve the circular barn problem.

    Args:
        input_source: None (for stdin), file path string, or file-like object
    """
    in_files, out_files = get_test_files()
    if in_files and out_files:
        for in_file, out_file in zip(in_files, out_files):
            print(f"Processing input file: {in_file}")
            n, k, rooms, max_cow_room_idx = process_input(str(in_file))
            min_cost = solve_circular_barn(n, k, rooms)
            with open(out_file, "r") as f:
                expected_output = int(f.read().strip())
            assert min_cost == expected_output, (
                f"Test failed for {in_file}: expected {expected_output}, got {min_cost}"
            )
            print(f"Test passed for {in_file}: {min_cost}")
    else:
        n, k, rooms, max_cow_room_idx = process_input(input_source=None)
        min_cost = solve_circular_barn(n, k, rooms)
        print(min_cost)


if __name__ == "__main__":
    # Example usage:
    # main()  # Read from stdin
    # main("input.txt")  # Read from file
    main()
