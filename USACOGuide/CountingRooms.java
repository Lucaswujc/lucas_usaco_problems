package USACOGuide;
import java.util.*;
public class CountingRooms  {
    static char[][] layout;
    private static int n;
	private static int m;          
	private static boolean[][] visited;  
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        layout = new char[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i< n; i++){
            String s = scan.next();
            for(int j = 0; j < m; j++){
                layout[i][j] = s.charAt(j);
            }
        }
        int count = 0;
        for(int i = 0; i <n; i++){
            for(int j = 0; j <m ;j++){
                if(!visited[i][j] && layout[i][j] == '.'){
                    floodfill(i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
        scan.close();
    }

	private static void floodfill(int r, int c) {
		if ((r < 0 || r >= n || c < 0 || c >= m) || layout[r][c] == '#' || visited[r][c]){
			return;
        }

		visited[r][c] = true;
		floodfill(r, c + 1);
		floodfill(r, c - 1);
		floodfill(r - 1, c);
		floodfill(r + 1, c);
	}
}