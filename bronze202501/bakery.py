T = 2 # test cases 
N = 3 # number of friends 
tc = 7 # time for cookie 
tm = 9 # time for muffin
for x in range(N):
    lineinput = line.split().map(int)
    ai = lineinput[0] # number of cookie
    bi = lineinput[1] # number of muffin
    ci = lineinput[2] # number waiting
    
# total number of money can spent is M , 
# tc + tm - M > 2 
# c is money spent on cookie, M-c is money spent on muffin
# (tc - c) * ai + (tc -(M-c)) * bi <= ci
# c <= tc 


# then brutal force ??? binary search ???

