# input N, M K , N is number of words. M is line of poet, K is sum of syllables in each line
# then N lines of pair (syllables count, rhyme class)
# then M lines of rhyme theme, where letter is used to represent rhyme class
# output the number of different poems that can be formed modulo 10^9 + 7
from collections import Counter

MOD = 10**9 + 7
N, M, K = map(int, input().strip().split())
words = [tuple(map(int, input().strip().split())) for _ in range(N)]
rhyme_themes = [input().strip() for _ in range(M)]

# Phase 1: DP to count ways to form lines with K syllables ending in each rhyme class
# dp[k][r] = number of ways to make k syllables ending with rhyme class r
max_rhyme = max(r for _, r in words)  # Find maximum rhyme class
dp = [[0] * (max_rhyme + 1) for _ in range(K + 1)]

# Base case: we can start with any word
for syllables, rhyme_class in words:
    if syllables <= K:
        dp[syllables][rhyme_class] = (dp[syllables][rhyme_class] + 1) % MOD

# Build dp table: for each state, try adding each word
for k in range(1, K):
    for prev_rhyme in range(max_rhyme + 1):
        if dp[k][prev_rhyme] > 0:
            # Try adding each word
            for syllables, rhyme_class in words:
                if k + syllables <= K:
                    dp[k + syllables][rhyme_class] = (
                        dp[k + syllables][rhyme_class] + dp[k][prev_rhyme]
                    ) % MOD

# ways[r] = number of ways to form a line with K syllables ending in rhyme class r
ways = [dp[K][r] for r in range(max_rhyme + 1)]

# Phase 2: Combine all rhyme theme lines into one pattern
# The M lines represent the M lines of the poem
scheme = "".join(rhyme_themes)  # Concatenate all lines into one pattern
letter_count = Counter(scheme)
letters = sorted(letter_count.keys())  # Sort for consistency
num_letters = len(letters)


# Count all valid assignments of rhyme classes to letters
# note that when pattern is ABA, it means first and third line must rhyme,
# second line can be different or the same, it does not matter, hence
# assignment only needs to ensure same letters get same rhyme class, we can
# greedily assign rhyme classes to letters in recursive manner, more like a recursive dfs


def count_assignments(letter_idx, assignment):
    if letter_idx == num_letters:
        # All letters assigned, calculate the product
        prod = 1
        for i, letter in enumerate(letters):
            freq = letter_count[letter]
            rhyme = assignment[i]
            prod = (prod * pow(ways[rhyme], freq, MOD)) % MOD
        return prod

    total = 0
    # Try each rhyme class for current letter
    for r in range(max_rhyme + 1):
        if ways[r] > 0:  # Only use rhyme classes with valid lines
            assignment[letter_idx] = r
            total = (total + count_assignments(letter_idx + 1, assignment)) % MOD

    return total


result = count_assignments(0, [-1] * num_letters)
print(result)
