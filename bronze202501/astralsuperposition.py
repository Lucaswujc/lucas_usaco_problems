
# White == both empty
# Black == both both stars
# Gray == one star
def solve(A, B, N, final_pic):
    pic1 = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N-1, -1, -1):
        for j in range(N-1, -1, -1):
            val = final_pic[i][j]
            if val == 2:
                # pic 2 has a pixel and pic1 has a pixel
                pic1[i][j] = 1
                # which means pic1 i - A , j -B  == 1
                if i - B >= 0 and j - A >= 0:
                    pic1[i-B][j-A] = 1
                else:
                    return -1
            elif val == 1:
                if i - B < 0 or j-A < 0:
                    # no thoice, have to set current location
                    pic1[i][j] = 1
                else:
                    # if val is 1 , then pixel can exist on current location
                    # disappearing later or exist on previous pic and move down
                    # greedy check if pic1 already contains the pixel then
                    # do nothing, otherwise, tentatively set the
                    # origin to a special value
                    if pic1[i][j] == 3:
                        pass
                    else:
                        pic1[i-B][j-A] = 3
            elif val == 0:
                # no stars on both pictures . need to double check here 
                if (pic1[i][j] == 3):
                    # well.. tentative greedy is not working, reset
                    pic1[i+B][j+A] = 1
                    pic1[i][j] = 0
                elif (pic1[i][j] == 1):
                    # conflict . 
                    return -1
    total = 0
    for row in pic1:
        total += sum([1 for x in row if x > 0])
#    print(pic1)
    return total


T = int(input().strip())
for _ in range(T):
    line = input()

    (N, A, B) = map(int, line.strip().split(" "))
    final_pic = []

    lookup = {"W": 0, "B": 2, "G": 1}
    for _ in range(N):
        row = list(map(lambda x: lookup[x], input().strip()))
        final_pic.append(row)

    print(solve(A, B, N, final_pic))
