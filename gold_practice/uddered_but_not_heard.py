"""
a special alphabet contains full 26 letters from a to z, but the order of letters is unknown.
A cow has hummed a song that contains all 26 letters in this special alphabet multiple times,
but the order of letters in the song is also unknown.
The input is a string representing the selected letter sequences from the song that the cow has hummed.
determine the minimum number of times the cow has hummed the entire special alphabet.

input: a string
output : minimum number of times the cow has hummed the entire special alphabet
Sample: "mildredree"
Output : 3
It is possible that the special alphabet order is "MILDREabcfghjknopqstuvwxyz"
then the cow has hummed the entire alphabet 3 times: we can pick the MILDRE in the first sequence
and dre in the second sequence and e in the third sequence to form 3 times of the entire alphabet.

Analysis:
=========
let dp[i] be the minimum number of times the cow has hummed the entire alphabet to form the
first i letters in the input string
base case: dp[0] = 0
transition:
to compute dp[i], we can try to find the last occurrence of letter 'a' to 'z' before index i
let's say the last occurrence of letter c before index i is at index j (j < i)
then we can update dp[i] = min(dp[i], dp[j] + 1)
if there is no occurrence of letter c before index i, then we cannot update dp[i]
The final answer will be dp[n] where n is the length of the input string

??? why this appears in bitmask ??
"""

line = input().strip()
n = len(line)
INF = 10**9
dp = [INF] * (n + 1)
# base case, there is no letter, hence 0 times of the entire alphabet
dp[0] = 0
dp[1] = 1  # first letter always need at least one time
# create a lookup for each letter's last occurrence index
# initialize with -1
last_occurrence = {chr(i + ord("a")): -1 for i in range(26)}
last_occurrence[line[0]] = 0
for i in range(2, n + 1):
    char_at_i_minus_one = line[i - 1]
    if last_occurrence[char_at_i_minus_one] != -1:
        j = last_occurrence[char_at_i_minus_one]
        # definition of dp is first i letters in the sequence, hence dp index is i+1
        dp[i] = min(dp[i], dp[j] + 1)
        last_occurrence[char_at_i_minus_one] = i
    else:
        # first time see this letter, update the last occurrence
        # dp[i] ... remains dp[i-1] since it is the first time this letter appears
        # to maintain the minimum count, we can assume it is part of the previous count
        last_occurrence[char_at_i_minus_one] = i
        dp[i] = dp[i - 1]
print(dp[n])
