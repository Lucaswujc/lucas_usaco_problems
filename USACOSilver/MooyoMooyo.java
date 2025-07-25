package USACOSilver;
import java.io.*;
import java.util.*;

public class MooyoMooyo {
    static int[][] grid;
    static int n;
    static int m = 10;
    static int k;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                grid[i][j] = (int) s.charAt(j)-48;
            }
        }
        while (true) {
            visited = new boolean[n][m];
            boolean cont = false;
            for(int i = 0; i < n; i++){
                for (int j = 0; j < m; j++) {
                    count = 0;
                    if(grid[i][j] == 0){
                        continue;
                    }
                    if (!visited[i][j] && grid[i][j] != 0) {
                        floodfill(i, j, grid[i][j]);
                        if (count >= k) {
                            deletion(i, j, grid[i][j]);
                            cont = true;
                        }
                    }
                }
            }
            gravity();
            if (!cont) {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(grid[i][j]);
            }
            out.println();
        }
        printout();
        out.close();
        br.close();
    }
    static void printout(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
    private static void floodfill(int r, int c, int color) {
        if ((r < 0 || r >= n || c < 0 || c >= m) || grid[r][c] != color || visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        count++;
        floodfill(r, c + 1, color);
        floodfill(r, c - 1, color);
        floodfill(r - 1, c, color);
        floodfill(r + 1, c, color);
    }

    private static void deletion(int r, int c, int color) {
        if ((r < 0 || r >= n || c < 0 || c >= m) || grid[r][c] != color) {
            return;
        }
        if (grid[r][c] == 0 && visited[r][c]) {
            return;
        }
        grid[r][c] = 0;
        deletion(r, c + 1, color);
        deletion(r, c - 1, color);
        deletion(r - 1, c, color);
        deletion(r + 1, c, color);
    }

    private static void gravity() {
        
        for (int j = 0; j < m; j++) {
            int count1 = n-1;
            for (int i = n - 1; i >= 0; i--) {
                if (grid[i][j] != 0) {

                    int temp = grid[i][j];
                    grid[i][j] = grid[count1][j];
                    grid[count1][j] = temp;
                    count1--;
                }
            }
        }
    }
}
