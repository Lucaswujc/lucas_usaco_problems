import java.util.*;
public class ConveyorBelt  {
    static char[][] grid;
    static int n;
	static int q;          
    static int[][] coords;
    static int count;
    static void update(int x, int y){
        count++;
        grid[x][y] = '.';
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        q = scan.nextInt();
        grid = new char[n+2][n+2];
        coords = new int[q][2];
        for(int i = 0; i< n+2; i++){
            Arrays.fill(grid[i], '?');
        }
        for(int  i =0; i < q; i++){
            coords[i][0] =scan.nextInt();
            coords[i][1] =scan.nextInt();
            grid[coords[i][0]][coords[i][1]] = scan.next().charAt(0);
        }
        floodfill(0, 0);
        int total = (n+2)*(n+2);
        int[] output = new int[q];
        for(int i = q-1; i >= 0; i--){
            output[i] = total - count;
            int r = coords[i][0];
            int c = coords[i][1];
            if(grid[r][c] != '.'){
                grid[r][c] = '?';
                if(grid[r][c-1] == '.' || grid[r][ c+1] == '.'  || grid[r-1][c] == '.' || grid[r+1][c] == '.'){
                    floodfill(r, c);
                }
            }
        }
        for(int i = 0; i < q; i++){
            System.out.println(output[i]);
        }
        scan.close();
    }

	static void floodfill(int r, int c) {
		char ch  = grid[r][c];
        if(ch == '.'){
            return;
        }
        if(ch == '?'){
            update(r, c);
        }
        else if(ch == 'U' && grid[r-1][c] == '.'){
            update(r, c);
        }
        else if(ch == 'D' && grid[r+1][c] == '.'){
            update(r, c);
        }
        else if(ch == 'L' && grid[r][c-1] == '.'){
            update(r, c);
        }
        else if(ch == 'R' && grid[r][c+1] == '.'){
            update(r, c);
        }
        else{
            return;
        }
        if(r > 0){
            floodfill(r - 1, c);
        }
        if(r < n + 1){
            floodfill(r + 1, c);
        }
        if(c > 0){
            floodfill(r, c - 1);
        }
        if(c < n + 1){
            floodfill(r, c + 1);
        }
	}
}