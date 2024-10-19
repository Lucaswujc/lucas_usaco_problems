
import java.util.*;
public class ComfortableCows {
    static boolean[][] cows = new boolean[3000][3000];
    static int[][] adj = new int[3000][3000];
    static int ans = 0;
    static int changex[] = {-1, 1, 0, 0};
    static int changey[] = {0, 0, -1, 1};
    static void floodfill(int x, int y) {
        if (!cows[x][y]) {
            cows[x][y] = true;
            ans++;
            if (cows[x][y] && adj[x][y] == 3) {
                for (int i = 0; i < 4; i++) {
                    int x2 = x + changex[i];
                    int y2 = y + changey[i];
                    floodfill(x2, y2);
                }
            }
            for (int i = 0; i < 4; i++) {
                int x2 = x + changex[i];
                int y2 = y + changey[i];
                adj[x2][y2]++;
                if (cows[x2][y2] && adj[x2][y2] == 3) {
                    for (int j = 0; j < 4; j++) {
                        int x3 = x2 + changex[j];
                        int y3 = y2 + changey[j];
                        floodfill(x3, y3);
                    }
                }
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int j = 0; j < n; j++) {
            int x = scan.nextInt() + 1000;
            int y = scan.nextInt() + 1000;
            ans--;
            floodfill(x, y);
            System.out.println(ans);
        }
        scan.close();
    }
}