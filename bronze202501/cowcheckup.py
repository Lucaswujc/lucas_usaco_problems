N = int(input())
a1 = list(map(lambda x: -1 * x, map(int, input().strip().split(" "))))
a2 = list(map(int, input().strip().split(" ")))


def check_cnt(a1, a2):
    return sum([1 if x+y == 0 else 0 for x, y in zip(a1, a2)])


def reorder(a, i, j):
    subarray = a[i: j+1]
    suffix = a[j+1:]
    prefix = a[0:i]
    subarray.reverse()
    return prefix+subarray+suffix


result = {}
for i in range(N):
    for j in range(i, N):
        swapped = reorder(a1, i, j)
        cnt = check_cnt(swapped,a2)
        if cnt not in result:
            result[cnt] = 0
        result[cnt] += 1
for x in range(N+1):
    if x not in result:
        print(0)
    else:
        print(result[x])
