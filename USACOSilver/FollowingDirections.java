package USACOSilver;
import java.util.*;
public class FollowingDirections {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] grid = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++){
           
            if(i == n){
                for(int j = 0; j < n; j++){
                    grid[i][j] = scan.nextInt();
                }
                continue;
            }
            String s = scan.next();
            for(int j = 0; j < n+1; j++){
                if(j == n){
                    grid[i][j] = scan.nextInt();
                    continue;
                }
                grid[i][j] = s.charAt(j) == 'R' ? 1 : 2;
                
            }
        }
        int[][] path = new int[n + 1][n + 1];
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == n || j == n) {
                    ans += path[i][j] * grid[i][j];
                } 
                else {
                    path[i][j]++;
                    if (grid[i][j] == 1) {
                        path[i][j + 1] += path[i][j];
                    } else {
                        path[i + 1][j] += path[i][j];
                    }
                }
            }
        }
        System.out.println(ans);
        int q = scan.nextInt();
        for(int t = 0; t < q; t++){
            int x = scan.nextInt() - 1;
            int y = scan.nextInt() - 1;
            int a = x;
            int b = y;
            while (a != n && b != n) {
                if (grid[a][b] == 1) {
                    b++;
                } 
                else {
                    a++;
                }
                path[a][b] -= path[x][y];
            }
            ans -= path[x][y] * grid[a][b];
            grid[x][y] = grid[x][y] == 1 ? 2 : 1;
            a = x;
            b = y;
            while (a != n && b != n) {
                if (grid[a][b] == 1) {
                    b++;
                } 
                else {
                    a++;
                }
                path[a][b] += path[x][y];
            }
            ans += path[x][y] * grid[a][b];
            System.out.println(ans);
        }
        scan.close();
    }
}