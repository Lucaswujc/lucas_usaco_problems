package USACOGold;
import java.util.*;

public class ErasingVertices {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] adj = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = scan.next();
            for (int j = 0; j < n; j++) {
                adj[i][j] = line.charAt(j) == '1' ? 1 : 0;
            }
        }

        int[] reachFrom = new int[n];

        for (int start = 0; start < n; start++) {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[n];
            stack.push(start);
            visited[start] = true;

            while (!stack.isEmpty()) {
                int curr = stack.pop();
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && adj[curr][i] == 1) {
                        visited[i] = true;
                        reachFrom[i]++;
                        stack.push(i);
                    }
                }
            }
        }

        double expectedOps = 0;
        for (int count : reachFrom) {
            expectedOps += 1.0 / (count + 1);
        }

        System.out.printf("%.15f\n", expectedOps);
        scan.close();
    }
}
