"""
Input N , L , N is number of movies, L is the length of the total time
Next N lines, each line contains the following info
1. duratino of the movie
2. number of show times
3. list of show time start time , list size is equal to number of show times
Output:  minimum number of movies that can be watched during the total time L
where there is no gap between movies watched
Constraints: each movie can be watched only once
Analysis:
=========
let dp[mask] be the maximum continuous time interval that can be reached by watching movies in mask
base case: dp[0] = 0
transition:
to compute dp[mask], we can try to add one movie i not in mask to the watched list
let's call prev_mask = mask ^ (1 << i) , i.e. exclude movie i from mask
to calculate the new time for adding movie i,
we need to track the earliest and latest start time of the dp[prev_mask]
then by adding i to the watch list, we need to find whether there is a show time in movie i
that can start before the latest end time of dp[prev_mask] and extend the total time by some
non-negative amount
Also, it is possible that there is a show time start before the earliest end time of dp[prev_mask]
but end equal or after the earliest start time of dp[prev_mask], in this case, we can, it is also
valid that dp[prev_mask] can be extended by some non-negative amount

Therefore, for each movie i not in mask, we need to check all show times of movie i
to see if such show time exists, then we can update dp[mask] = max(dp[mask],
    max(all extended times from adding movie i to the dp[prev_mask])
    )

The final answer will be the minimum number of movies in mask where dp[mask] >= L

"""


class Movie:
    def __init__(self, duration, show_times):
        self.duration = duration
        self.show_times = show_times


class TimeRange:
    def __init__(self, earliest_start, latest_end):
        self.earliest_start = earliest_start
        self.latest_end = latest_end

    def extend_with_movie(self, movie):
        best_earliest = self.earliest_start
        best_latest = self.latest_end

        for show_time in movie.show_times:
            show_end = show_time + movie.duration
            new_earliest = self.earliest_start
            new_latest = self.latest_end

            # Special case: if current interval is empty (earliest == latest),
            # we can start watching from any show time
            if self.earliest_start == self.latest_end:
                new_earliest = show_time
                new_latest = show_end
            # Otherwise, show must overlap or connect with current interval
            elif show_time <= self.latest_end and show_end >= self.earliest_start:
                new_earliest = min(new_earliest, show_time)
                new_latest = max(new_latest, show_end)
            else:
                continue

            # Keep the show time that gives maximum duration
            if (new_latest - new_earliest) > (best_latest - best_earliest):
                best_earliest = new_earliest
                best_latest = new_latest
        return TimeRange(best_earliest, best_latest)

    def get_duration(self):
        return self.latest_end - self.earliest_start


N, L = map(int, input().strip().split())

movies = []
for _ in range(N):
    line_nums = list(map(int, input().strip().split()))
    duration = line_nums[0]
    num_show_times = line_nums[1]
    show_times = line_nums[2:]
    movies.append(Movie(duration, show_times))

INF = float("inf")
# dp[mask] = maximum time that can be reached by watching movies in mask
dp = [
    TimeRange(0, 0) for _ in range(1 << N)
]  # initialize dp array with time range (0, 0)
dp[0] = TimeRange(0, 0)  # base case: no movies watched, time range is (0, 0)
# Fill the DP table
for mask in range(1, 1 << N):
    for i in range(N):
        if mask & (1 << i) == 0:  # movie i is not in the current mask
            continue

        # the mask contains movie i , now find the mask does not contain movie i
        prev_mask = mask ^ (1 << i)
        extended_range = dp[prev_mask].extend_with_movie(movies[i])
        dp[mask] = max(dp[mask], extended_range, key=lambda x: x.get_duration())
# The answer will be the minimum number of movies in mask where dp[mask] >= L
result = INF
for mask in range(1 << N):
    if dp[mask].get_duration() >= L:
        num_movies = bin(mask).count("1")
        result = min(result, num_movies)
if result == INF:
    result = -1
print(result)
