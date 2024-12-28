input_strs = ["""
5
5 4 3 2 1
1 2
1 3
3 4
3 5
""",]

def solve(N, P, graph, vertices):
    leaves_cnt_map = dict()
    portions_cnt_map = dict()

    bfs_leaves_cnt(graph, 1, leaves_cnt_map)
    total_leaves = leaves_cnt_map[1]
    total_portions = 0
    for node in P[:total_leaves]:
        if(node not in portions_cnt_map):
            portions_cnt_map[node] = 0
        portions_cnt_map[node] += 1
    visited = set()
    # for vertex in vertices:
    #     if vertex not in visited:
    #         bfs_populdate_portions(graph, vertex, portions_cnt_map, leaves_cnt_map, visited)
    bfs_populdate_portions(graph, 1, portions_cnt_map, leaves_cnt_map, visited)
    return max(portions_cnt_map.values())

def bfs_populdate_portions(graph, node, portions_cnt_map, leaves_cnt_map, visited):
    visited.add(node)
    if node != 1 and len(graph[node]) == 1:
        # this is a leaf
        if node in portions_cnt_map:
            portions_cnt_map[node] =1
        else:
            portions_cnt_map[node] =0
        return
    total_children_portions = 0
    for neighbor in graph[node]:
        if neighbor not in visited:
            bfs_populdate_portions(graph, neighbor, portions_cnt_map, leaves_cnt_map, visited)
            total_children_portions += portions_cnt_map[neighbor]
    portions_cnt_map[node] = min(total_children_portions+portions_cnt_map.get(node,0), leaves_cnt_map[node])
        

def bfs_leaves_cnt(graph, node, leaves_cnt_map):
    leaves_cnt_map[node] = 0
    if node != 1 and len(graph[node]) == 1:
        # this is a leaf
        leaves_cnt_map[node] = 1
        return
    for neighbor in graph[node]:
        if neighbor not in leaves_cnt_map:
            bfs_leaves_cnt(graph, neighbor, leaves_cnt_map=leaves_cnt_map)
            leaves_cnt_map[node] += leaves_cnt_map[neighbor]

    
    
# for input_str in input_strs:
#     inputs = input_str.strip("\n").split("\n")
#     N = int(inputs[0].strip())
#     P = list(map(int, inputs[1].strip().split()))
#     graph = dict()
#     vertices = set()
#     for edge in inputs[2:]:
#         u, v = map(int, edge.split())
#         if u not in graph:
#             graph[u] = []
#         graph[u].append(v)
#         vertices.add(u)
#         vertices.add(v)
#     solve(N, P, graph, vertices)
def get_input_from_stdin():
    N = int(input().strip())
    P = list(map(int, input().strip().split(' ')))
    graph = dict()
    vertices = set()
    for _ in range(N-1):
        u, v = map(int, input().strip().split(' '))
        if u not in graph:
            graph[u] = []
        if v not in graph:
            graph[v] = []
        graph[u].append(v)
        graph[v].append(u)
        vertices.add(u)
        vertices.add(v)
    return N, P, graph, vertices

def get_line_from_file(file):
    with open(file) as f:
        lines = f.readlines()
    return lines

def process_input_lines(lines):
    N = int(lines[0].strip())
    P = list(map(int, lines[1].strip().split(' ')))
    graph = dict()
    vertices = set()
    for edge in lines[2:]:
        u, v = map(int, edge.split())
        if u not in graph:
            graph[u] = []
        if v not in graph:
            graph[v] = []
        graph[u].append(v)
        graph[v].append(u)
        vertices.add(u)
        vertices.add(v)
    return N, P, graph, vertices

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
    test_folder = "/Users/yurenwu/Downloads/prob2_silver_jan24"
    for f in get_all_input_files(test_folder,type="in"):
        base_name = os.path.basename(f)
        lines = get_line_from_file(f)
        N, P, graph, vertices = process_input_lines(lines)
        max_portions = solve(N, P, graph, vertices)
        outfile  = f.with_suffix('.out')
        with open(outfile, 'r') as o:
            expected_output = int(o.readline().strip())
        print(f"test_case ={base_name}\t{expected_output == max_portions}\tprogram output = {max_portions}\texpected output = {expected_output}")

def main():
    N, P , graph, vertices = get_input_from_stdin()
    print(solve(N, P, graph, vertices))
    # test_cases_from_disk()


import sys
sys.setrecursionlimit(100000) 
main()
