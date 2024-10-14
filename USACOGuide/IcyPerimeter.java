package USACOGuide;
import java.util.*;

public class IcyPerimeter {
    public static char[][] grid;
    public static int n;
    public static boolean[][] visited;
    public static int area;
    public static int perimeter;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = scan.next();
            for (int j = 0; j < n; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        visited = new boolean[n][n];
        int maxArea = 0;
        int maxPerimeter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '#') {
                    area = 0;
                    perimeter = 0;
                    floodfill(i, j, n);
                    if (area > maxArea) {
                        maxArea = area;
                        maxPerimeter = perimeter;
                    } else if (area == maxArea) {
                        maxPerimeter = Math.min(perimeter, maxPerimeter);
                    }
                }
            }
        }
        System.out.println(maxArea + " " + maxPerimeter);
        scan.close();
    }

    public static void floodfill(int r, int c, int n) {
        if ((r < 0 || r >= n || c < 0 || c >= n) || (grid[r][c] == '.' || visited[r][c])) {
            return;
        }
        area++;
        if (c - 1 >= 0) {
            if (grid[r][c - 1] != '#') {
                perimeter++;
            }
        }
        else{
            perimeter++;
        }
        if (c + 1 < n) {
            if (grid[r][c + 1] != '#') {
                perimeter++;
            }
        }
        else{
            perimeter++;
        }
        if (r - 1 >= 0) {
            if (grid[r - 1][c] != '#') {
                perimeter++;
            }
        }
        else{
            perimeter++;
        }
        if (r + 1 < n) {
            if (grid[r + 1][c] != '#') {
                perimeter++;
            }
        }
        else{
            perimeter++;
        }
        visited[r][c] = true;
        floodfill(r, c + 1, n);
        floodfill(r, c - 1, n);
        floodfill(r - 1, c, n);
        floodfill(r + 1, c, n);
    }
}