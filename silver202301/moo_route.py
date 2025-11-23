N = int(input())
A = list(map(int, input().split())) + [-2]
 
route = []
i = 0
while not (i == 0 and A[i] == -2):
    # move to right 
	while A[i] > 0: 
		route.append('R')
		A[i] -= 1
		i += 1
    # move to left 
	while i > 0 and (A[i-1] > 1 or A[i] == -2): 
		route.append('L')
		i -= 1
		A[i] -= 1
print("".join(route))