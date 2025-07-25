def factor(n: int):
    """sqrt(n) complexity

    Args:
        n (int): _description_

    Returns:
        _type_: _description_
    """
    vector = set()
    i = 2
    while i <= n:
        while (n % i == 0):
            vector.add(i)
            n = n // i
        i += 1
    if n > 1:
        vector.add(n)
    return vector


print(factor(100))  # [2, 2, 5, 5]


def count_divsors(n: int):
    """ sqrt(n) complexity

    Args:
        n (int): _description_

    Returns:
        _type_: _description_
    """
    counter = 0
    i = 1
    while i * i <= n:
        if n % i == 0:
            counter += 1 if i*i == n else 2
        i += 1
    return counter


print(count_divsors(100))  # 9


x = [1, 2, 3]
y = [(e + 1, e+2) for e in x]
print(y)
print("seperator".join([str(e) for e in y]))
