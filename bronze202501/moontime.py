N = int(input())
a = list(map(int, input().strip().split(" ")))
num_dic = dict()

possible_moo_suffix_cnt = 0
for x in a:
    # define a data structure here 
    # [ count of appearance of value x , prefix distinct val cnt, possible_moos (previous value of prefix distinct val)]
    # number of distinct keys before current x 
    prefix_distinct_cnt = len(num_dic)-1
    if x not in num_dic:
        num_dic[x] = [0,prefix_distinct_cnt, 0]
    val = num_dic[x]
    val[0] = val[0]+1
    val[2] = val[1]
    val[1] = prefix_distinct_cnt

total = 0
for x in num_dic:
    if num_dic[x][0]>=2:
        total += num_dic[x][2]
print(total)
