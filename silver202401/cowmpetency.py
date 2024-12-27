# cowptency scores . 
# for all all pairs of (ai,hi), it is guarenteed that ai is unique across all pairs. 
# hence we can sort the pairs based on ai , then find the max of [a1..ai], 
# if the max is less than hi, then assign hi-1 to any zeros in the sequence. 
# repeat the pairs of such assignment untill it is not possible or all (ai,hi) pairs are 
# consumed. 
# if all pairs are consumed and there are still zeros in the sequence, then such zeros
# can be assigned to 1 


def generage_array(score_array, index, max_score_c):
    if score_array[index] != 0:
        if index == len(score_array) - 1: 
            # index reached to the end of the array, return result
            yield score_array
        else:
            # move to the next index 
            yield from generage_array(score_array, index + 1, max_score_c)
    else:
        # if the current position is zero, then assign a score to it by iterating from 1..max_score_c
        # brutal force approach
        for score in range(1, max_score_c + 1):
            score_array[index] = score
            if index  == len(score_array)-1 : 
                yield score_array
            else: 
                yield from generage_array(score_array, index + 1, max_score_c)
        score_array[index] = 0

def test_array_with_rules(s, rules):
    for ai , hi in rules:
        # hi is the first element greater than max(s[:ai+1])
        # this means that all elements up to ai+1 , max value 
        # should be less than s[hi]. 
        # moreover elements from ai to hi max value should be 
        # less or equal to s[hi]
        # to find violations, 
        # if max(s[:ai+1]) != max(s[:hi]) then there is at least 
        # one element between ai and si is greater than max(s[:ai+1])
        # or max(s[:ai+1]) >= s[hi] 
        if max(s[:ai+1]) != max(s[:hi]) or max(s[:ai+1]) >= s[hi]:
            return False
    return True

def cowmpetency(scores, pairs):
    new_scores = scores.copy()
    for pair in pairs:
        ai = pair[0]
        hi = scores[pair[1]]
        if hi == 0:
            # find the max of [a0..ai] and assign score[hi] = max+1
            max_score = max(scores[:ai+1])
            if max_score ==0:
                # all scores below pair[1] should be 1 
                new_scores[pair[1]] = 2
            else:
                new_scores[pair[1]] = max_score + 1
        else:
            for x in range(0,ai+1):
                if scores[x] == 0:
                    scores[x] = hi - 1
                elif scores[x] >= hi:
                    return -1
    for x in range(0,len(scores)):
        if scores[x] == 0:
            scores[x] = 1
    return scores

def read_input(input_str):
    input_list = input_str.strip().split('\n')
    N, Q, C = input_list[0].split(' ')
    scores = [int(x) for x in input_list[1].split(' ')]
    rules = sorted(
        [(int(x.split(' ')[0])-1, int(x.split(' ')[1])-1) for x in input_list[2:]], 
        key=lambda x: int(x[0]))
    return scores, rules, N, Q, C

# test_case_str = """7 3 5
# 1 0 2 3 0 4 0
# 3 4
# 1 2
# 4 5
# """
# scores, pairs = read_input(test_case_str)
# print(scores, pairs)
# print(cowmpetency(scores, pairs))


test_cases_str = ["""
7 6 10
0 0 0 0 0 0 0
1 2
2 3
3 4
4 5
5 6
6 7""",

"""8 4 9
0 0 0 0 1 6 0 6
1 3
6 7
4 7
2 3""",
"""2 1 1
0 0
1 2""",
"""10 4 10
1 2 0 2 1 5 8 6 0 3
4 7
1 2
5 7
3 7""",
"""10 2 8
1 0 0 0 0 5 7 0 0 0
4 6
6 9"""
]
# for test_case_str in test_cases_str[1:]:
#     scores, pairs = read_input(test_case_str)
#     # print(scores, pairs)
#     print(cowmpetency(scores, pairs))


# for test_case_str in test_cases_str[:]:
#     # print(f"test case :\n{test_case_str}")
#     result_found = False
#     scores, rules , N , Q, C= read_input(test_case_str)
#     for x in generage_array(scores, 0, int(C)):
#         if test_array_with_rules(x, rules = rules):
#             print(x)
#             result_found = True
#             break
#     if not result_found:
#         print(-1)



num_test_cases = int(input())
for x in range(num_test_cases):
    N, Q, C = map(int, input().split(' '))
    scores = [int(x) for x in input().split(' ')]
    rules = []
    for r in range(Q):
        rule = tuple(map(int, input().split(' ')))
        rule = (rule[0]-1, rule[1]-1)
        rules.append(rule)
    rules =sorted(rules, key=lambda x: int(x[0]))
    result_found = False
    for a in generage_array(scores, 0, C):
        if test_array_with_rules(a, rules = rules):
            print(" ".join([str(x) for x in a]))
            result_found = True
            break
    if not result_found:
        print(-1)
        
