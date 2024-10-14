import java.io.*;
import java.util.*;
public class SwitchingOnTheLights {
    static boolean illum[][];
	static boolean visited[][];
	static List<Pair>[][] switches;
	static int litRooms = 1;
    static int n;
	static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {-1, 0, 1, 0};
    static class Pair {
		public int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter("lightson.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
        illum = new boolean[n][n];
		visited = new boolean[n][n];
		switches = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) { 
                switches[i][j] = new ArrayList<Pair>(); 
            }
        }

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			switches[x - 1][y - 1].add(new Pair(a - 1, b - 1));
		}

		illum[0][0] = true;
		floodfill(0, 0);
        out.println(litRooms);
        br.close();
        out.close();
    }
    public static boolean checkConnected(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int x2 = x + dirX[i];
			int y2 = y + dirY[i];

			if (x2 < 0 || y2 < 0 || x2 > n - 1 || y2 > n - 1) { 
                continue; 
            }
			if (visited[x2][y2]) { 
                return true; 
            }
		}
		return false;
	}
    public static void floodfill(int r, int c) {
        if ((r < 0 || r >= n || c < 0 || c >= n) || !illum[r][c] || visited[r][c]) {
            return;
        }
        if (!checkConnected(r, c) && !(r == 0 && c == 0)) { 
            return; 
        }
		visited[r][c] = true;
        for (int i = 0; i < 4; i++) { 
            floodfill(r + dirX[i], c + dirY[i]); 
        }
        for (int i = 0; i < switches[r][c].size(); i++) {
			int newX = switches[r][c].get(i).x;
			int newY = switches[r][c].get(i).y;

			if (!illum[newX][newY]) { 
                litRooms++; 
            }

			illum[newX][newY] = true;
			floodfill(newX, newY);
		}
		
    }
}