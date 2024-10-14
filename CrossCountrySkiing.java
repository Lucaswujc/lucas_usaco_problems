import java.io.*;
import java.util.*;
public class CrossCountrySkiing {
    static int[][] course;
    static int n, m;
    static int startx, starty;
	static boolean[][] visited;  
    static boolean[][] waypoints;  
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        course = new int[n][m];
        visited = new boolean[n][m];
        int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
        for(int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                course[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, course[i][j]);
				maxHeight = Math.max(maxHeight, course[i][j]);
            }
        }
        waypoints = new boolean[n][m];
		for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
				if (a == 1) {
					waypoints[i][j] = true;
					startx = i;
					starty = j;
				} else {
					waypoints[i][j] = false;
				}
			}
		}
        int lo = 0;
		int hi = maxHeight - minHeight;
		int minD = -1;
		while (lo <= hi) {
			int d = (lo + hi) / 2;
			if (reachable(d)) {
				minD = d;
				hi = d - 1;
			} else {
				lo = d + 1;
			}
		}
        out.println(minD);
        br.close();
        out.close();
    }
    static void floodfill(int r, int c, int d, int prevHeight) {
		if ((r < 0 || r >= n || c < 0 || c >= m) || visited[r][c]){
			return;
        }
		if (Math.abs(course[r][c] - prevHeight) > d) { 
            return; 
        }

		visited[r][c] = true;
		floodfill(r + 1, c, d, course[r][c]);
		floodfill(r - 1, c, d, course[r][c]);
		floodfill(r, c + 1, d, course[r][c]);
		floodfill(r, c - 1, d, course[r][c]);
	}
    static boolean reachable(int d) {
		visited = new boolean[n][m];
		floodfill(startx, starty, d, course[startx][starty]);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (waypoints[i][j] && !visited[i][j]) { 
                    return false; 
                }
			}
		}

		return true;
	}
}