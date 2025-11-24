"""
Taming the Herd
================
FJ start log when first break out happens, he recorded the counter on each day the number of days since last break out.
for example
day         0, 1, 2, 3, 4, 5
break       T, F, F, T, T, T
counter     0, 1, 2, 3, 1, 1
Cows can temper the log entry,
If the log is 1 1 2 0 0 1
the number of tempered entries is 2

Find the minimum number of tampered entries if we know the total break outs is K where K is between 1 and N inclusive
Input format
N = number of days log recorded
log entries for N days
Output format N lines, each line i is the minimum number of tampered entries if there are i break outs , i between 1 and N inclusive

"""

"""
Analysis : 
this is very similar to sname problem, by knowing up to k number of break outs, minimize the number of tampered entries
We can use dynamic programming to solve the problem.
for each possible number of break outs k from 1 to N do the following : 

Define dp[i][j] as the minimum number of tampered entries in the first i days with j break outs
To compute dp[i][j], we try placing the j-th break out at different positions before i.
All days from position prev to i-1 will use this break out at position prev.
The number of tampered entries for days from prev to i-1 using break out at prev is calculated as follows:
tampered = number of days x in [prev, i-1] where log_entries[x] != (x - prev)
The answer will be dp[n][k], where n is the total number of days.

Complexity: 
outer loop for k from 1 to N : O(N)
inner loop for i from 1 to N : O(N)
inner loop for j from 1 to k : O(N)
innermost loop for prev from 0 to i-1 : O(N)
Overall complexity : O(N^4)
Note: the calculation of tampered entries in the innermost loop takes O(N) time., this will increase the time complexity to O(N^5)
it will be too slow . 

However, the loop to calculate tampered entries can be precomputed in O(N^2) time because, by giving a start location, i, assuming 
a break out happenes on the day i, we can precompute the number of tampered entries for all end locations j >= i in O(N) time.
This number does not change no matter how many break outs we have before day i. and it does not change for different k values.

therefore, we can precompute a 2D array tampered[i][j] = number of tampered entries if break out happens at day i and we check days [i, j)
Overall complexity is reduced to O(N^4)
"""
from pathlib import Path

INF = float("inf")


def process_input(input_source=None):
    """
    Process input from either stdin or a file.

    Args:
        input_source: None (for stdin), file path string, or file-like object
    Returns:
        tuple: (N, log_entries)
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
    N = int(lines[0].strip())
    log_entries = [int(x) for x in lines[1].strip().split()]

    return N, log_entries


def precompute_tampered(n, log_entries):
    """
    Precompute tampered[i][j] = number of tampered entries
    if breakout happens at day i and we check days [i, j)
    """
    tampered = [[0] * (n + 1) for _ in range(n + 1)]

    for i in range(n):
        for j in range(i, n):
            # Count tampered entries from day i to day j
            # assuming breakout at day i
            if log_entries[j] != (j - i):
                tampered[i][j + 1] = tampered[i][j] + 1
            else:
                tampered[i][j + 1] = tampered[i][j]

    return tampered


def solve_for_k_breakouts(n, k, log_entries, tampered_pre_computed):
    # dp[i][j] = minimum tampered entries in first i days with j break outs
    dp = [[INF] * (k + 1) for _ in range(n + 1)]
    dp[0][0] = 0

    for i in range(1, n + 1):
        for j in range(1, k + 1):
            for prev in range(i):
                """
                calculated tampered entries from day prev to day i-1
                assuming breakout at day prev
                tempared_entries = 0
                for x in range(prev, i):
                    if log_entries[x] != (x - prev):
                        tempared_entries += 1
                """

                dp[i][j] = min(
                    dp[i][j], dp[prev][j - 1] + tampered_pre_computed[prev][i]
                )

    return dp[n][k]


def solve(N, log_entries, tampered_pre_computed):
    results = []
    for k in range(1, N + 1):
        result = solve_for_k_breakouts(N, k, log_entries, tampered_pre_computed)
        results.append(result)
    return results


def get_test_files(directory="taming_the_heard"):
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
    in_files = sorted([str(file) for file in dir_path.glob("*.in")])

    # Get all .out files
    out_files = sorted([str(file) for file in dir_path.glob("*.out")])

    return in_files, out_files


def main():
    """
    Main function to solve the taming the heard problem.
    """
    script_dir = Path(__file__).parent.resolve()
    directory = str(script_dir / "taming_the_heard")
    in_files, out_files = get_test_files(directory=directory)
    if in_files and out_files:
        for in_file, out_file in zip(in_files, out_files):
            N, log_entries = process_input(in_file)
            tampered_pre_computed = precompute_tampered(N, log_entries)
            results = solve(N, log_entries, tampered_pre_computed)
            with open(out_file, "r") as f:
                expected_results = [int(line.strip()) for line in f.readlines()]
            assert results == expected_results, f"Test failed for {in_file}"
            print(f"Test passed for {in_file}")
        print("All tests passed!")
    else:
        N, log_entries = process_input()
        tampered_pre_computed = precompute_tampered(N, log_entries)
        results = solve(N, log_entries, tampered_pre_computed)
        for result in results:
            print(result)


if __name__ == "__main__":
    main()
