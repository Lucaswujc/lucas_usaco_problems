package USACOGuide;
import java.util.*;
public class NuskevsPhantomThnook {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();;
        int cols = scan.nextInt();
        int q = scan.nextInt();
        char[][] grid = new char[rows+1][cols+1];
        for(int i = 1; i < rows; i++){
            String a = scan.next();
            for(int j = 1; j < cols; j++){
                grid[i][j] = a.charAt(j-1);
            }
        }
        int[][] prefix = new int[rows+1][cols+1];
        for (int r = 1; r <= rows; r++){
            for(int c = 1; c <= cols; c++){
                prefix[r][c] = prefix[r-1][c] +prefix[r][c-1] - prefix[r-1][c-1];
                if(grid[r][c] == '1'){
                    boolean up = grid[r-1][c] == '1';
                    boolean left = grid[r][c] =='1';
                    if(!(up || left)){
                        prefix[r][c]++;
                    }
                    if(up && left){
                        prefix[r][c]--;
                    }
                }
            }
        }
        int[][] horpref = new int[rows+1][cols+1];
        int[][] verpref = new int[rows+1][cols+1];
        for (int r = 1; r <= rows; r++) {
			for (int c = 1; c <= cols; c++) {
				horpref[r][c] = horpref[r][c - 1];
				verpref[r][c] = verpref[r - 1][c];
				if (grid[r][c] == '1') {
					if (grid[r][c - 1] == '0') { 
                        horpref[r][c]++; 
                    }
					if (grid[r - 1][c] == '0') { 
                        verpref[r][c]++; 
                    }
				}
			}
		}
        for(int i =0; i < q;i++){
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();
            int corner = grid[x1][y1] =='1' ? 1: 0;
            int topR = horpref[x1][y2] - horpref[x1][y1];
            int topC = verpref[x2][y1] - verpref[x1][y1];
            int ans = prefix[x2][y2] - prefix[x1][y2] - prefix[x2][y1] + prefix[x1][y1];
            System.out.println(corner+topR+topC+ans);
        }
        scan.close();
    }
}