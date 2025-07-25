package USACOSilver;
import java.io.*;
import java.util.*;
public class MilkPails {
    static int x, y, k, m, sol;
	static boolean[][][] visited;
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sol = m;
        visited = new boolean[x + 1][y + 1][k + 1];
        floodfill(0, 0, 0);
        out.println(sol);
        br.close();
        out.close();
    }
    static void floodfill(int curX, int curY, int curK) {
		if (visited[curX][curY][curK]) {
            return;
        }
		visited[curX][curY][curK] = true;

		if (curK == k) {
			sol = Math.min(sol, Math.abs(m - (curX + curY)));
			return;
		}

		//fill one
		floodfill(x, curY, curK + 1);
		floodfill(curX, y, curK + 1);
		//empty one
		floodfill(0, curY, curK + 1);
		floodfill(curX, 0, curK + 1);
		//pour one
		floodfill(Math.min(curX + curY, x), Math.max((curX + curY) - x, 0), curK + 1);
		floodfill(Math.max((curX + curY) - y, 0), Math.min(curX + curY, y), curK + 1);
	}
}