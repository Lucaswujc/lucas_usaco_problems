def process_input(lines):
    N, P = map(int, lines[0].strip().split(' '))
    tube1 = list(map(int, lines[1].strip()))
    tube2 = list(map(int, lines[2].strip()))
    return N, P, tube1, tube2

def compress(tube):
    ret = []
    for i in tube:
        if len(ret) == 0 or ret[-1] != i:
            ret.append(i)
        else:
            continue
    return ret

def solve(tube1, tube2, P):
    beak = []
    moves = []
    while (len(tube1) > 0):
        if tube1[-1] == 1 :
            move(tube1, tube2)
            moves.append((0,1))
        else:
            move(tube1,beak)
            moves.append((0,2))
    while(len(tube2)>0):
        if(tube2[-1] == 1):
            move(tube2, tube1)
            moves.append((1,0))
        else:
            move(tube2, beak)
            moves.append((1,2))
    if (len(beak) != 0):
        move(beak,tube1)
        moves.append((2,1))
    print(moves)
    
def move(src, dest):
    if (len(dest)==0):
        dest.append(src.pop())
    elif (dest[-1] == src[-1]):
        src.pop()
    else:
        dest.append(src.pop())
    

        
        
        
test_case = """
4 1
1221
2211
"""
N, P, tube1, tube2 = process_input(test_case.strip("\n").split('\n'))
print(N, P, tube1, tube2)

tube1 = compress(tube1)
tube2 = compress(tube2)

solve(tube1, tube2, P)

