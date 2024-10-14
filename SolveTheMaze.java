import java.util.*;

public class SolveTheMaze {
    static char[][] grid;
    private static int n;
    private static int m;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t > 0) {
            n = scan.nextInt();
            m = scan.nextInt();
            grid = new char[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                String s = scan.next();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            Boolean work = true;
            int[] changex = { 0, 0, 1, -1 };
            int[] changey = { 1, -1, 0, 0,};
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(grid[i][j] == 'B'){
                        for (int k = 0; k < 4; k++) {
                            int a = i + changex[k];
                            int b = j + changey[k];
                            if ((a >= 0 &&  a < n) && (b >= 0 && b < m)) {
                                if (grid[a][b] == 'G') {
                                    work = false;
                                    break;
                                } else if(grid[a][b] == '.'){
                                    grid[a][b] = '#';
                                }
                            }
                        }
                    }
                    if (!work) {
                        break;
                    }
                }
                if (!work) {
                    break;
                }
            }
            if (work) {
                if(grid[n-1][m-1] != '#'){
                    floodfill(n-1, m-1);
                    
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (grid[i][j] == 'G' && !visited[i][j]) { 
                            work = false;
                            break; 
                        }
                    }
                    if (!work) {
                        break;
                    }
                }
            }
            System.out.println(work == true ? "Yes" : "No");
            t--;
        }
        scan.close();
    }

    private static void floodfill(int r, int c) {
        if ((r < 0 || r >= n || c < 0 || c >= m) || grid[r][c] == '#' || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        floodfill(r, c + 1);
        floodfill(r, c - 1);
        floodfill(r - 1, c);
        floodfill(r + 1, c);
    }
}