import java.util.*;
public class CowChecklist {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int h = scan.nextInt();
        int g = scan.nextInt();
        int[][] holsteins = new int[h][2];
        int[][] guernseys = new int[g][2];
        long[][][] dp = new long[h+1][g+1][2];
        for(int i =0; i < h; i++){
            holsteins[i][0] = scan.nextInt();
            holsteins[i][1] = scan.nextInt();
        }
        for(int i =0; i < g; i++){
            holsteins[i][0] = scan.nextInt();
            holsteins[i][1] = scan.nextInt();
        }
        for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], Long.MAX_VALUE);
			}
		}
        dp[1][0][0] = 0;
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[i].length; j++) {
				for(int k = 0; k < 2; k++) {
					if(k == 0 && i == 0){
                        continue;
                    }
					if(k == 1 && j == 0) continue;
                    int x = 0;
                    int y = 0;
					if(k == 0) {
                        x = holsteins[i-1][0];
                        y = holsteins[i-1][1];
                    }
					else {
                        x = guernseys[i-1][0];
                        y = guernseys[i-1][1];
                    }
					if(i < h) {
                        int dist = (x-holsteins[i][0])*(x-holsteins[i][0]) + (y-holsteins[i][1])*(y-holsteins[i][1]);
						dp[i+1][j][0] = Math.min(dp[i+1][j][0], dp[i][j][k] + dist);
					}
					if(j < g) {
                        int dist = (x-guernseys[i][0])*(x-guernseys[i][0]) + (y-guernseys[i][1])*(y-guernseys[i][1]);
						dp[i][j+1][1] = Math.min(dp[i][j+1][1], dp[i][j][k] + dist);
					}
				}
			}
		}
        System.out.println(dp[h][g][0]);
        scan.close();
    }
}