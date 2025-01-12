
test_cases = ["""
10 3 2
2 5
1 3
1 3
""",
              """
10 3 3
2 7 5
8 3 4
2 5 6
""",
              """20 8 2
3 5
3 5
3 5
3 5
3 5
3 5
3 5
3 5
    """
              ]


def solve(N, M, K, possible_moves):
    """
    for each move, we can calculate the number of marbles Elsie can win 
    by guessing even or odd 
    if Elsie guess even, the winning of 2,5 will be 2, 
    if the possible values are 2,4,5, the winning will be 2 since Bessie will only play 
    the smalllest even marbles for Elsie to win 
    """
    winning = []
    loses = []
    for x in possible_moves:
        even_moves = [m for m in x if m % 2 == 0]
        odd_moves = [m for m in x if m % 2 == 1]
        even_winning = min(even_moves) if len(even_moves) > 0 else 0
        odd_winning = min(odd_moves) if len(odd_moves) > 0 else 0
        even_losing = max(odd_moves) if len(odd_moves) > 0 else 0
        odd_losing = max(even_moves) if len(even_moves) > 0 else 0
        winning.append(
            (even_winning, odd_winning)
        )
        loses.append(
            (even_losing, odd_losing)
        )
    #print(f"winning array:{winning}")
    # print(f"losing array{loses}")
    # assume that there is a solution to not losing all marbles, then at the
    # end of the game Elsie will have at least one marble left
    # we can start with last move and calculate what is the upper bound
    # that each move need
    upper_bound = [0 for _ in range(M+1)]
    upper_bound[-1] = 1
    # looop backwards from M-1 to 0 all inclusive
    for i in range(M-1, -1, -1):
        previous_upper_bound = upper_bound[i+1]
        potential_loses = loses[i]
        if potential_loses[0] == 0 or potential_loses[1] == 0:
            # then Elsie will not lose in this round, the upper bound
            # should be decreased by winning, since one of the winning
            # is zero, then we can use winning[i][0] + winning[i][1]
            upper_bound[i] = previous_upper_bound - \
                (winning[i][0] + winning[i][1])
        else:
            # there are both even and odd numbers in this round
            # there is no gurentee that Elsie will win, the upper bound
            # should be increased by max(loses)
            upper_bound[i] = previous_upper_bound + min(loses[i])
        if upper_bound[i] < 1 :
            upper_bound[i] = 1
    # print(f"upper bounds:{upper_bound}")
    if N < upper_bound[0]:
        print(-1)
        return
    # now we can use the N to step from beginning to the end to decide whether each
    # move can be even or odd
    moves = []
    for i in range(M):
        # always prefer even number if possible
        if N - loses[i][0] >= upper_bound[i+1] and N - loses[i][0] > 0:
            moves.append("Even")
            if loses[i][0] > 0 and loses[i][1] > 0:
                # if both even and odd numbers are available, then chose even will cause
                # N decrease by loses[i][0]
                N = N - loses[i][0]
            elif loses[i][0] > 0:
                # even loss is not zero , odd loss is zero, then chose even will cause
                # N decrease by loses[i][0]
                N = N - loses[i][0]
            else:
                # even loss is zero, odd loss is not zero, then chose even will
                # win, check the win[i][0] and increase N
                N = N + winning[i][0]
        else:
            moves.append("Odd")
            if loses[i][0] > 0 and loses[i][1] > 0:
                # if both even and odd numbers are available, then chose odd will cause
                # N decrease by loses[i][1]
                N = N - loses[i][1]
            elif loses[i][1] > 0:
                # even loss is zero , odd loss is not zero, then chose odd will cause
                # N decrease by loses[i][1]
                N = N - loses[i][1]
            else:
                # even loss is not zero, odd loss is zero, then chose odd will
                # win, check the win[i][1] and increase N
                N = N + winning[i][1]

    print(" ".join(moves))
    return 0

def process_input_from_list(test_cases):
    for test_case in test_cases:
        """
        N : number of marbles Elsie has
        M : number of turns
        K : possible moves for all turns
        """
        lines = test_case.strip("\n").split('\n')
        N, M, K = map(int, lines[0].strip().split())
        possible_moves = []
        for i in range(M):
            possible_moves.append(list(map(int, lines[i+1].strip().split())))
        print(N, M, K, possible_moves)
        solve(N, M, K, possible_moves)

def process_input_lines(lines):
    """
    N : number of marbles Elsie has
    M : number of turns
    K : possible moves for all turns
    """
    test_cases = int (lines[0])
    line_index = 0
    for _ in range(test_cases):
        line_index+=1
        N, M, K = map(int, lines[line_index].strip().split())
        possible_moves = []
        for i in range(M):
            line_index+=1
            possible_moves.append(list(map(int, lines[line_index].strip().split())))
        #print(N, M, K, possible_moves)
        solve(N, M, K, possible_moves)

def process_inputs():
    N, M, K =map(int , input().strip().split(' '))
    possible_moves = []
    for _ in range(M):
        possible_moves.append(list(map(int, input().strip().split())))
    return N, M, K, possible_moves


    

def get_all_input_files(folder, type='in'):
    from pathlib import Path    
    directory_path = Path(folder)
    if type == 'in':
        file_pattern = "*.in"
    elif type == 'out':
        file_pattern = "*.out"
    else:
        file_pattern = type
    matching_files = sorted(list(directory_path.glob(file_pattern)),
                            key=lambda fullname: int(str(fullname).split('/')[-1].split('.')[0]))
    return matching_files

def test_cases_from_disk():
    import os
    test_folder = "/Users/yurenwu/Downloads/prob3_silver_feb24"
    for f in get_all_input_files(test_folder,type="3.in"):
        base_name = os.path.basename(f)
        lines = []
        with open(f) as inputfile:
            lines = inputfile.readlines()
        process_input_lines(lines=lines)
        # outfile  = f.with_suffix('.out')
        # with open(outfile, 'r') as o:
        #     expected_output = int(o.readline().strip())
        # print(f"test_case ={base_name}\t{expected_output == max_portions}\tprogram output = {max_portions}\texpected output = {expected_output}")

    

test_cases = int(input().strip())
for _ in range(test_cases):
    N, M, K, possible_moves = process_inputs()
    solve(N,M,K,possible_moves)


# test_cases_from_disk()
test_cases = [
    """9054 16 2
784 798
168 965
998 13
867 150
957 764
508 971
895 12
365 4
777 130
398 759
699 266
368 581
46 691
890 375
129 610
458 155
    """
]
# process_input_from_list(test_cases=test_cases)