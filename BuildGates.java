import java.io.*;
import java.util.*;
public class BuildGates {  
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));

		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int x = 1002;
		int y = 1002;
		boolean[][] isFence = new boolean[2005][2005];
		isFence[x][y] = true;
		for(int i = 0; i < s.length(); i++) {
			int dirX = 0, dirY = 0;
			if(s.charAt(i) == 'N') {
				dirX = -1;
			}
			else if(s.charAt(i) == 'S') {
				dirX = 1;
			}
			else if(s.charAt(i) == 'W') {
				dirY = -1;
			}
			else {
				dirY = 1;
			}
			for(int a = 0; a < 2; a++) {
				x += dirX;
				y += dirY;
				isFence[x][y] = true;
			}
		}
		int ans = -1;
		int[] dx = new int[]{-1,1,0,0};
		int[] dy = new int[]{0,0,-1,1};
		for(int i = 0; i < isFence.length; i++) {
			for(int j = 0; j < isFence[i].length; j++) {
				if(isFence[i][j]) {
					continue;
				}
				ans++;
				LinkedList<Point> q = new LinkedList<Point>();
				q.add(new Point(i, j));
				isFence[i][j] = true;
				while(!q.isEmpty()) {
					Point curr = q.removeFirst();
					for(int k = 0; k < dx.length; k++) {
						int newX = curr.x + dx[k];
						int newY = curr.y + dy[k];
						if(newX >= 0 && newX < isFence.length && newY >= 0 && newY < isFence[newX].length && !isFence[newX][newY]) {
							isFence[newX][newY] = true;
							q.add(new Point(newX, newY));
						}
					}
				}
			}
		}
		pw.println(ans);
		
		pw.close();
        br.close();
	}
	
	static class Point {
		public int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}