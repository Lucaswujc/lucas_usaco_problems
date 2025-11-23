def max_increasing_sequence(arr):
    if not arr:
        return 0

    dp = [1] * len(arr)
    pos = [-1] * len(arr)
    for i in range(1, len(arr)):
        for j in range(i):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], dp[j] + 1)
                if dp[i] == dp[j] + 1:
                    pos[i] = j

    return max(dp), dp, pos


# Example usage:
if __name__ == "__main__":
    arr = [10, 22, 9, 33, 21, 50, 41, 60, 80]
    max_length, dp, pos = max_increasing_sequence(arr)
    print("[" + ", ".join(f"{x:3}" for x in arr) + "]")
    print("[" + ", ".join(f"{x:3}" for x in dp) + "]")
    print("Length of longest increasing subsequence is", max_length)
    print("The longest increasing subsequence is:", end=" ")

    # Reconstruct the longest increasing subsequence
    lis = []
    current = dp.index(max_length)
    while current != -1:
        lis.append(arr[current])
        current = pos[current]
    lis.reverse()
    print("[" + ", ".join(f"{x:3}" for x in lis) + "]")
