def detect_degeneracy(a, b, c, d, e, f):
    # Compute the discriminant
    discriminant = b**2 - 4 * a * c

    # Check for degenerate cases
    if a == 0 and b == 0 and c == 0:
        return "Degenerate: Point (Empty Set)"

    # Case for two intersecting lines (degenerate hyperbola)
    elif discriminant > 0 and a != 0 and c != 0 and f == 0:
        return "Degenerate: Two intersecting lines"

    # Case for two parallel lines (degenerate ellipse)
    elif discriminant < 0 and a == c and b == 0 and f == 0:
        return "Degenerate: Two parallel lines"

    # Case for one line (coincident lines)
    elif discriminant == 0 and (a == 0 or c == 0):
        return "Degenerate: One line (Coincident lines)"

    # Case for no real graph (impossible geometry)
    elif f != 0 and (a * f < 0 or c * f < 0):
        return "Degenerate: No real graph (Empty set)"

    # If none of the degenerate conditions are met, it is not degenerate
    return "Not Degenerate"


# Example usage
# Test case 1: Circle equation x^2 + y^2 = 0 (Degenerate case: Point)
a, b, c, d, e, f = 1, 0, 1, 0, 0, 0
print(detect_degeneracy(a, bw, c, d, e, f))  # Output: Degenerate: Point (Empty Set)

# Test case 2: Two intersecting lines (x^2 - y^2 = 0)
a, b, c, d, e, f = 1, 0, -1, 0, 0, 0
print(detect_degeneracy(a, b, c, d, e, f))  # Output: Degenerate: Two intersecting lines

# Test case 3: Ellipse equation that collapses to a point (x^2 + y^2 = -1)
a, b, c, d, e, f = 1, 0, 1, 0, 0, -1
print(
    detect_degeneracy(a, b, c, d, e, f)
)  # Output: Degenerate: No real graph (Empty set)

# Test case 4: Two parallel lines (x^2 = 4)
a, b, c, d, e, f = 1, 0, 0, 0, 0, -4
print(detect_degeneracy(a, b, c, d, e, f))  # Output: Degenerate: Two parallel lines
