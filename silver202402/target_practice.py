# 
# 
#

def process_inputs(lines):
    N , x1 = map(int, lines[0].strip().split())
    x2=[]
    y2=[]
    y1=[]
    top_right = []
    bottom_right = []
    for i in range(N):
        X2, Y1, Y2 = map(int, lines[i+1].strip().split())
        x2.append(X2)
        y1.append(Y1)
        y2.append(Y2)
        top_right.append((X2, Y2))
        bottom_right.append((X2, Y1))
    s = map(int, lines(i+1).strip().split())
    positive_slops = sorted([x for x in s if x > 0])
    negative_slops = sorted([x for x in s if x < 0])
    return N, x1, x2, y1, y2, s, top_right, bottom_right, positive_slopes, negative_slopes

def solve(N, x1, x2, y1, y2, s):
    """
    """

test_case = """
2 1
1 3 6
4 6 3
1 -1 2 -2 3 -3 4 -4
"""    

N, x1 ,x2, y1, y2, s, top_right, buttom_right, positive_slopes, negative_slopes = process_inputs(test_case.strip('\n').split('\n'))
if len(positive_slopes)<N or len(negative_slopes)<N:
    print(-1)
    exit()

# the length of positive slopes and negative slopes determines how to arrange the rest of the points
# into to two groups. since all the points share the same x1, we can sort all top left and bottom left 
# points by their y values, the hihgest portion of y values can be arranged in postive slope groups 
# while the lowest portion of y values can be arranged in negative slope groups 
# for example, (10,10), (10,1) are two points(same x1), if we can have a line with positive slope s1
# passing (10,10), while a line with negative slope s2 passing(10,1), then the distance of such two 
# cows on y axis is  (10 - 10 * s1) - (1+ (-1) * 10* s2) = 10 - 10s1 - 1 + 10s2 = 9 + 10(s2 - s1)
# on the other hand, if we have a line with positive slope s1 passing (10,1), while a line with negative
# slope s2 passing (10,10), then the distance of such two cows on y axis is 
# (10 + (-1) * 10 * s2) - (1 - 10 * s1) = 10 - 10s2 - 1 + 10s1 = 9 + 10(s1 - s2)
# given s2 < 0 and s1 > 0 then the first case  - second case is a negative number, hence the first case 
# cows are closer to each other than the second case 

all_y = sorted(y1 + y2)
negative_slope_vertices = top_right
positive_slope_vertices = buttom_right
for y in all_y:
    """
    small est to largest y values
    """
    if len(negative_slope_vertices) < len(negative_slopes):
        negative_slope_vertices.append((x1, y))
    else:
        positive_slope_vertices.append((x1, y))

# now we have all the points arranged in two groups, we can start to calculate the optimal distance
# for positive_slope_vertices, we want the highest y intercept 
# for negative_slope_vertices, we want the lowest y intercept
