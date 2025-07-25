line = input().strip()
N  = int(line.split()[0])
K = int(line.split()[1])
moves = []


for _ in range(N):
    line = input().strip()
    match line :
        case "H":
            moves.append(0)
        case "P":
            moves.append(1)
        case "S":
            moves.append(2)
dp = [
        [
            [0 for _ in range(3)] 
            for _ in range(K+1)
        ] 
        for _ in range(N)
    ]
for i in range(N):
    for k in range(K+1):
        for g in range(3):
            if g == moves[i]:
                dp[i][k][g] +=1
            dp[i+1][k+1][0]  = max(dp[i+1][k+1][0], dp[i][k][g]) 
            dp[i+1][k+1][1]  = max(dp[i+1][k+1][1], dp[i][k][g])
            dp[i+1][k+1][2]  = max(dp[i+1][k+1][2], dp[i][k][g])
            dp[i+1][k][g] = max(dp[i+1][k][g], dp[i][k][g])     
            
max_win = 0 
for g in range(3):
    max_win = max(max_win, dp[N-1][K][g])
print(max_win)