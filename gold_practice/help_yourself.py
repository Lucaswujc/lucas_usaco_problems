from itertools import combinations


def get_input():
    segments = []
    N = int(input("Enter the number of segments: "))
    for i in range(N):
        segment_str = input()
        segment = (int(segment_str.split()[0]), int(segment_str.split()[1]))
        segments.append(segment)
    return segments


def yield_combinations(segments):
    for i in range(len(segments)):
        yield from combinations(segments, i+1)


def test(interval, union):
    for segment in union:
        if segment[0] <= interval[0] and interval[1] <= segment[1]:
            return True
    return False


def compute_complexity(union):
    complexity = 0
    min_l = min([segment[0] for segment in union])
    max_r = max([segment[1] for segment in union])
    for i in range(min_l, max_r+1):
        if not (test((i-1, i), union)) and test((i, i+1), union):
            complexity += 1
    return complexity


if __name__ == "__main__":
    complexity = 0
    segments = get_input()
    for segment in segments:
        print(segment)
    for union in yield_combinations(segments):
        c = compute_complexity(union)
        print(c, union)
        complexity += c
    print(complexity)
