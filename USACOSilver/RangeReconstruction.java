package USACOSilver;
import java.util.*;
 
public class RangeReconstruction {
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] diff = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                diff[i][j] = scan.nextInt();;
            }
        }
        List<Integer> distinct = new ArrayList<>();
        distinct.add(0);
        for (int j = 1; j < n; j++) {
            if (diff[j - 1][j] != 0) {
                distinct.add(j);
            }
        }
        int[] ans = new int[n];
        if (distinct.size() > 1) {
            ans[distinct.get(1)] = diff[0][distinct.get(1)];
            for (int j = 2; j < distinct.size(); j++) {
                int a = distinct.get(j - 2);
                int b = distinct.get(j - 1);
                int c = distinct.get(j);
                int sign = diff[a][c] == diff[a][b] + diff[b][c] ? 1 : -1;
                ans[c] = ans[b] + (sign * Integer.signum(ans[b] - ans[a]) * diff[b][c]);
            }
            for (int j = 1; j < n; j++) {
                if (diff[j - 1][j] == 0) {
                    ans[j] = ans[j - 1];
                }
            }
        }
        for (int j = 0; j < n; j++) {
             System.out.println(ans[j] + " ");
        }
        scan.close();
    }
}