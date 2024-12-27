# input = """3 5
# 1 1 R
# 3 3 L
# 3 2 D
# 1 2 L
# 2 1 U"""
# lines = input.split("\n")
# testcase = """3 8
# 1 1 R
# 1 2 L
# 1 3 D
# 2 3 U
# 3 3 L
# 3 2 R
# 3 1 U
# 2 1 D"""
testcase = """4 13
2 2 R
2 3 R
2 4 D
3 4 D
4 4 L
4 3 L
4 2 U
3 1 D
4 1 R
2 1 L
1 1 D
1 4 L
1 3 D"""
# lines = testcase.split("\n")
# N = int(lines[0].split(" ")[0])
# Q = int(lines[0].split(" ")[1])

line = input()
N = int(line.split(" ")[0])
Q = int(line.split(" ")[1])
graph = [["?" for _ in range(N)] for _ in range(N)]
# print(graph)


def print_graph(graph):
    print("="*len(graph))
    for line in graph:
        for x in line:
            print(x, end="")
        print()
    print("="*len(graph))


# def detect_cycle(graph):
#     visited = set()
#     cycle_grids = set()
#     to_be_built_belts = set()
#     for x in range(len(graph)):
#         for y in range(len(graph[x])):
#             v = graph[x][y]
#             if v == '?':
#                 to_be_built_belts.add((x, y))
#             if v != '?' and (x, y) not in visited:
#                 check_path = set()
#                 check_cycle(x, y, graph, visited, check_path, cycle_grids)
#     # scan through the to_be_built
#     for (x, y) in to_be_built_belts:
#         if (x - 1, y) in cycle_grids and (x+1, y) in cycle_grids and (x, y-1) in cycle_grids and (x, y+1) in cycle_grids:
#             cycle_grids.add((x, y))
#     return (len(cycle_grids))


# def check_cycle(x, y, graph, visited, check_path, cycle_grids):
#     visited.add((x, y))
#     check_path.add((x, y))
#     if graph[x][y] == 'U':
#         x = x - 1
#     elif graph[x][y] == 'D':
#         x = x + 1
#     elif graph[x][y] == 'L':
#         y = y - 1
#     elif graph[x][y] == 'R':
#         y = y + 1
#     if ((x < 0) or (y < 0) or
#         (x >= len(graph)) or
#             (y >= len(graph)) or
#             graph[x][y] == '?'):
#         # out of graph
#         return
#     if (x, y) not in visited:
#         check_cycle(x=x,
#                     y=y,
#                     graph=graph,
#                     visited=visited,
#                     check_path=check_path,
#                     cycle_grids=cycle_grids)
#     else:
#         # visisted to a previously visisted grid
#         # if the pair exists in the current check_path
#         # then it is a cycle
#         # if the pair exists in the cycle_grids set
#         # then the current path connected to a previousely
#         # detected cycle,
#         # else, safely return
#         if (x, y) in check_path or (x, y) in cycle_grids:
#             # newly detected cycle, add all pairs in the check_path to cycle_grids and return
#             cycle_grids.update(check_path)
#             return
#         else:
#             return


def mark_cells(graph):
    good_cells = set()
    # check cells that on the order
    for n in range(len(graph)):
        # top border
        if (graph[0][n] == '?' or graph[0][n] == 'U'):
            good_cells.add((0, n))
        # left border
        if (graph[n][0] == '?' or graph[n][0] == 'L'):
            good_cells.add((n, 0))
        # right border
        if (graph[n][len(graph)-1] == '?' or graph[n][len(graph)-1] == 'R'):
            good_cells.add((n, len(graph)-1))
        # bottom border
        if (graph[len(graph)-1][n] == '?' or graph[len(graph)-1][n] == 'D'):
            good_cells.add((len(graph)-1, n))
    # print(f"border cells {sorted(good_cells)}")
    visited = set().update(good_cells)
    for row in range(len(graph)):
        for col in range(len(graph)):
            if (row, col) not in good_cells:
                visited = set()
                check_cell(row, col, graph, good_cells, visited)
    # print(f"after walk : {sorted(good_cells)}")
    return len(graph) * len(graph) - len(good_cells)


def walk(row, col, move):
    if move == 'U':
        return (row - 1, col)
    elif move == 'D':
        return (row + 1, col)
    elif move == 'L':
        return (row, col - 1)
    elif move == 'R':
        return (row, col + 1)


def check_cell(x, y, graph, good_cells, visited):
    if (x < 0 or y < 0 or x >= len(graph) or y >= len(graph)):
        return True
    if (x, y) in visited:
        return False
    visited.add((x, y))
    if (
        (x, y) in good_cells
    ):
        return True

    if graph[x][y] == '?':
        for move in ['U', 'D', 'L', 'R']:
            newx, newy = walk(x, y, move)
            if check_cell(newx, newy, graph, good_cells, visited=visited):
                good_cells.add((x, y))
                return True
        # if program reach here, then ... it is false
        return False
    else:
        newx, newy = walk(x, y, graph[x][y])
        if check_cell(newx, newy, graph, good_cells, visited=visited): 
            good_cells.add((x, y))
            return True
        else:
            return False


# for line in lines[1:]:
for t in range(Q):
    line = input()
    x = int(line.split(' ')[0])
    y = int(line.split(' ')[1])
    move = str(line.split(' ')[2]).strip()
    graph[x-1][y-1] = move
    # print_graph(graph=graph)
    print(mark_cells(graph))
