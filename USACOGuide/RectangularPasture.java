package USACOGuide;
import java.util.*;
public class RectangularPasture {
    static int getSum(int[][] sums, int fromX, int toX, int fromY, int toY) {
        return sums[toX][toY] - sums[fromX - 1][toY] - sums[toX][fromY - 1] + sums[fromX - 1][fromY - 1];
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Integer[] cows = new Integer[n];
        for(int i = 0; i < n; i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
            cows[i] = i;
        }
        Arrays.sort(cows, Comparator.comparingInt(i -> x[i]));
        for (int i = 1; i <= n; i++) {
            x[cows[i - 1]] = i;
        }
        Arrays.sort(cows, Comparator.comparingInt(j -> y[j]));
        for (int i = 1; i <= n; i++) {
            y[cows[i - 1]] = i;
        }
        int[][] sums = new int[n + 1][n + 1];
        for (int j = 0; j < n; j++) {
            sums[x[j]][y[j]]++;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) {
                    sums[i][j] += sums[i - 1][j];
                }
                if (j > 0) {
                    sums[i][j] += sums[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    sums[i][j] -= sums[i - 1][j - 1];
                }
            }
        }
        long answer = n + 1;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                answer += getSum(sums, Math.min(x[j], x[k]), Math.max(x[j], x[k]), 1, Math.min(y[j], y[k]))
                        * getSum(sums, Math.min(x[j], x[k]), Math.max(x[j], x[k]), Math.max(y[j], y[k]), n);
            }
        }
        System.out.println(answer);
        scan.close();
    }
}