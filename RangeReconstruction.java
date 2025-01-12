import java.util.*;
 
public class RangeReconstruction {
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] differences = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                differences[i][j] = scan.nextInt();;
            }
        }
        List<Integer> distinct = new ArrayList<>();
        distinct.add(0);
        for (int j = 1; j < n; j++) {
            if (differences[j - 1][j] != 0) {
                distinct.add(j);
            }
        }
        int[] answer = new int[n];
        if (distinct.size() > 1) {
            answer[distinct.get(1)] = differences[0][distinct.get(1)];
            for (int j = 2; j < distinct.size(); j++) {
                int a = distinct.get(j - 2);
                int b = distinct.get(j - 1);
                int c = distinct.get(j);
                int sign = differences[a][c] == differences[a][b] + differences[b][c] ? 1 : -1;
                answer[c] = answer[b] + (sign * Integer.signum(answer[b] - answer[a]) * differences[b][c]);
            }
            for (int j = 1; j < n; j++) {
                if (differences[j - 1][j] == 0) {
                    answer[j] = answer[j - 1];
                }
            }
        }
        for (int j = 0; j < n; j++) {
             System.out.println(answer[j] + " ");
        }
    }
}