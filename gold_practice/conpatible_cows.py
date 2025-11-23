# Define set [1-5]
# Setp[1] is the cows share 1 flavor in common , set[2] is share 2 flavors in common ... set[5]
# the answer should be 
# ANS = s1 union s2 union s3 union s4 union s5 - 
# (s1 intersect s2) - (s1 inttersec s3 ) - (s1 intersec s4 ) - (s1 intersec s5 ) 
# - (s2 intersec s3 ) - (s2 intersec s4 ) - (s2 intersec s5 ) 
# - (s3 intersec s4 ) - (s3 intersec s5 ) - (s4 intersec s5 )
# + (s1 intersec s2 intersec s3 ) + (s1 intersec s2 intersec s4 ) + (s1 intersec s2 intersec s5 )
# + (s1 intersec s3 intersec s4 ) + (s1 intersec s3 intersec s5 ) + (s1 intersec s4 intersec s5 )
# + (s2 intersec s3 intersec s4 ) + (s2 intersec s3 intersec s5 ) + (s2 intersec s4 intersec s5 )
# + (s3 intersec s4 intersec s5 )
# -(s1 intersec s2 intersec s3 intersec s4 ) - (s1 intersec s2 intersec s3 intersec s5 )
# - (s1 intersec s2 intersec s4 intersec s5 ) - (s1 intersec s3 intersec s4 intersec s5 )
# - (s2 intersec s3 intersec s4 intersec s5 )
# + (s1 intersec s2 intersec s3 intersec s4 intersec s5 )



Hashmap<color, set<cow>> color_map

find share share two flavors 
for color in color_map:
    for another_color in color_map:
        if color == another_color:
            continue
        new_set = color_map[color].intersection(color_map[another_color])
        
# hero = n , max_life = x 
# if there is a single pair of heros share the same life value, then there is no winner
# count should be ways that we can assign lifes to N herios where at least 1 pair of them share same value 
# the value itself can be 1 .. X inclusive 
# the asnwer should X * [ways to choose at least 1 pair of heros share same life value]
# if there is N herios, then we can use counting priciple to solve this by 
# 1 pair, 2 pair 3 pair ... N pair