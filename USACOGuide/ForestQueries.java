package USACOGuide;

import java.util.*;
public class ForestQueries {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); 
        int q = scan.nextInt();
        int[][] forest = new int[n+1][n+1];
        int[][] prefix = new int[n+1][n+1];
        for(int  i = 1; i <= n ; i++){
            String s = scan.next();
            for(int j = 1; j <= n; j++){
                forest[i][j] = s.charAt(j-1) == '*' ? 1 : 0;
            }
        }
        for(int  i = 1; i <= n ; i++){
            for(int j = 1; j <= n; j++){
                prefix[i][j] = forest[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        for(int i =0 ; i < q; i++){
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();
            System.out.println(prefix[x2][y2] - prefix[x1-1][y2] - prefix[x2][y1-1] + prefix[x1-1][y1-1]);
        }
        scan.close();
    }
}