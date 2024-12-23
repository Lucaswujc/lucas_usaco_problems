from collections import Counter

def equal_frequency(s1):
    char_counts = Counter(s1)

    num_unique_chars = len(char_counts)
    target_frequency = sum(char_counts.values()) // num_unique_chars

    # operation array of tuple ('add', 'char',cnt) ('remove','char',cnt), ('switch','from','to',cnt)
    operations = []
    total_operations = 0

    # for each character, record char and its diff to the targeted frequency
    # split the positive and negative to two arrays
    excess_chars = {}
    deficit_chars = {}

    for char, count in char_counts.items():
        if count < target_frequency:
            # Track deficit for this character
            deficit_chars[char] =  target_frequency - count
        elif count > target_frequency:
            # Track excess for this character
            excess_chars[char] = count - target_frequency

    # Perform switches to balance excess and deficit, respecting alphabetical order
    new_excess_chars = []
    new_deficit_chars = []
    print(deficit_chars)
    for excess_char, excess_count in excess_chars.items():
        for deficit_char, deficit_count in deficit_chars.items():
            if excess_count == 0: 
                break
            if abs(ord(excess_char) - ord(deficit_char)) ==1 :
                switch_count = min(excess_count, deficit_count)
                operations.append(("switch", excess_char, deficit_char, switch_count))
                total_operations += switch_count
                print(f"debug: switch {excess_char} {deficit_char}")                
                # Adjust counts
                excess_count -= switch_count
                deficit_count -= switch_count
                deficit_chars[deficit_char] = deficit_count
        excess_chars[excess_char] = excess_count                
    print(deficit_chars)
    for excess_char, excess_count in new_excess_chars.items():
        if excess_count > 0:
            operations.append(("remove", excess_char, excess_count))
            total_operations += excess_count

    for deficit_char, deficit_count in deficit_chars.items():
        if deficit_count > 0:
            operations.append(("add", deficit_char, deficit_count))
            total_operations += deficit_count

    # Replace characters if needed to minimize operations
    s2 = ''.join([char * target_frequency for char in char_counts])

    # Print operation details
    print(f"Total Operations: {total_operations}")
    for op in operations:
        if op[0] == "switch":
            print(f"Operation: {op[0]}, From: {op[1]}, To: {op[2]}, Count: {op[3]}")
        else:
            print(f"Operation: {op[0]}, Character: {op[1]}, Count: {op[2]}")

    return s2

# Example usage
s1 = "aaabbc"
s2 = equal_frequency(s1)
print(f"Input String: {s1} .  targeted String:{s2}")