def from_std_in():
    N = input()
    months = sorted(list(set(map(int, input().split()))))
    return print(solve2(N, months))


def test_mod_cnt(months, x):
    rs = set()
    for m in months:
        rs.add(m % x)
        if len(rs) > 3:
            return (False, rs)
    return (True, rs)


def solve2(N, months):
    """
    Brutal force solution is not efficient enough, we need to find a better solution.
    In stead of for x in range(1, upper_bound+1), and test each x, we can greatly reduce the number of x 
    to test by checking the first 4 values in months, 
    Notice that if there is a valid x, then such x must pass the test for the first 4 values in months.
    (why 4??? because first three values will always pass. The fourth value is the first one that may trigger a fail.)
    Now if a valid x can pass the first four values (denoted as m1, m2, m3, m4), it means that 
    m1 % x, m2 % x, m3 % x, m4 % x can only have 3 different values,
    let's denote the four results as r1, r2, r3 and r4, then we can have the following equations:
    if r1 == r2 then m1 % x == m2 % x, then m1 - m2 % x == 0, then (m1 - m2) % x == 0
    if r1 == r3 then m1 % x == m3 % x, then m1 - m3 % x == 0, then (m1 - m3) % x == 0
    .... 
    if r4 == r3 then m4 % x == m3 % x, then m4 - m3 % x == 0, then (m4 - m3) % x == 0
    there are total 6 equations  (choose 2 out of 4)
    from these 6 equations, we can generate list of possible valid x values, 
    only the values in such list need to tested for months, therefore, we successfully reduce the loop 
    for x in range(1, upper_bound+1) to a much smaller list of x values.
    the time complexity is reduced from O(10^9) to O(10^4),
    Args:
        N (_type_): _description_
        months (_type_): _description_
    """

    upper_bound = min(months)//4
    if len(months) < 4:
        # even the //2 seems redudant, but it is necessary to make sure the return value is int!!!!
        return upper_bound*(upper_bound+1)//2

    potentially_validx = set()
    for i, s in enumerate(months[:4]):
        for _, r in enumerate(months[i:4]):
            diff = abs(s-r)
            for t in range(1, int(diff**(0.5) + 1)):
                if diff % t == 0:
                    potentially_validx.add(t)
                    potentially_validx.add(diff//t)
    result = 0
    potentially_validx = sorted([int(x) for x in potentially_validx if x <= upper_bound])
    for x in potentially_validx:
        if test_mod_cnt(months, x)[0]:
            result += x
    return result


def solve(N, months):
    """
    Brutal force solution, first found the min of months, 
    the upper bound of such L is min(months)//4, (For the correct L, each month is at least 4 weeks long.)
    then iterate through all the possible x, among 1 to upper_bound,
    for each x, test number of mods by iterate through all values in months, (10^4)

    why it does not work for large values in months?
    the for x in range(1, upper_bound+1) is O(upper_bound) = O(min(months)//4) = O(4*10^9) = O(10^9)
    the test function is O(N) = O(10^4)

    Args:
        N (int): total number of months (1 <= N <= 10^4)
        months (set): set of each individual month (1 <= month <= 4 * 10^9)

    Returns:
        int: sum of all possible x
    """
    upper_bound = min(months)//4
    result = 0
    for x in range(1, upper_bound+1):
        if test_mod_cnt(months, x)[0]:
            result += x
    return result


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


def get_line_from_file(file):
    with open(file) as f:
        lines = f.readlines()
    return lines


def process_input_lines(lines):
    N = int(lines[0].strip())
    months = sorted(list(set(map(int, lines[1].strip().split(' ')))))
    return N, months


def test_cases_from_disk():
    import os
    test_folder = "/Users/yurenwu/Downloads/prob3_silver_jan24"
    for f in get_all_input_files(test_folder, type="in"):
        base_name = os.path.basename(f)
        lines = get_line_from_file(f)
        N, months = process_input_lines(lines)
        result = solve2(N, months)
        outfile = f.with_suffix('.out')
        with open(outfile, 'r') as o:
            expected_output = int(o.readline().strip())
        print(f"test_case ={base_name}\t{expected_output == result}\tprogram output = {result}\texpected output = {expected_output}")


def main():
    # test_cases_from_disk()
    from_std_in()


main()
