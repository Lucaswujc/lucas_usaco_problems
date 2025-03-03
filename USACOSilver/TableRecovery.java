package USACOSilver;
import java.util.*;
public class TableRecovery {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scan.nextInt();
            }
        }

        int maxN = 2*n;
        int[] count = new int[maxN + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count[table[i][j]]++;
            }
        }

        int[][] num = new int[2][maxN + 1];
        List<List<Integer>> configGroup = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int swap1 = 0;
            int swap2 = 0;
            for (int j = 1; j <= maxN; j++) {
                if (count[j] == i) {
                    if (swap1 == 0) {
                        swap1 = j;
                    } else {
                        swap2 = j;
                    }
                }
            }

            int orig1 = i + 1;
            int orig2 = (maxN + 2) - orig1;

            int first = first(table, swap1, swap2);
            int second = (swap1 == first) ? swap2 : swap1;

            num[0][first] = orig1;
            num[0][second] = orig2;
            num[1][first] = orig2;
            num[1][second] = orig1;

            configGroup.add(Arrays.asList(first, second));
        }

        boolean[] seen = new boolean[maxN + 1];
        int[] order1 = new int[maxN - 1];
        int[] order2 = new int[maxN - 1];
        int order = 0;
        for (int[] row : table) {
            for (int val : row) {
                if (!seen[val]) {
                    if (order < order1.length) {
                        order1[order] = val;
                        order2[order1.length - 1 - order] = val;
                    }
                    seen[val] = true;
                    order++;
                }
            }
        }

        int[][] ans = new int[n][n];
        boolean[] config = new boolean[maxN + 1];
        Arrays.fill(config, true);

        List<Integer> skippable = new ArrayList<>();
        for (int i = 2; i <= maxN; i++) {
            if (num[0][i] == num[1][i]) {
                skippable.add(i);
            }
        }
        while (true) {
            do {
                for (int a : order2) {
                    if (config[a]) {
                        config[a] = false;
                    } else {
                        config[a] = true;
                        break;
                    }
                }
            } while (!check(maxN, config, configGroup));

            boolean skip = false;
            for (int val : skippable) {
                if (!config[val]) {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int idx = config[table[i][j]] ? 1 : 0;
                    ans[i][j] = num[idx][table[i][j]];
                }
            }

            if (tableCheck(ans)) {
                break;
            }

            boolean checkTr = true;
            for (int i = 2; i <= maxN; i++) {
                checkTr = (checkTr && config[i]);
            }
            if (checkTr) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(j != n-1){
                    System.out.print(ans[i][j] + " ");
                }
                else{
                    System.out.print(ans[i][j]);
                }
            }
            System.out.println();
        }
        scan.close();
    }
    private static int first(int[][] table, int a, int b) {
        for (int[] row : table) {
            for (int val : row) {
                if (val == a) {
                    return a;
                } else if (val == b) {
                    return b;
                }
            }
        }
        return 0;
    }

    private static boolean tableCheck(int[][] table) {
        int n = table.length;
        int[] sum = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i] += table[i][j];
            }
        }
        Arrays.sort(sum);
        for (int i = 1; i < n; i++) {
            if (sum[i] != sum[i - 1] + n) {
                return false;
            }
        }
        return true;
    }

    private static boolean check(int maxN, boolean[] config, List<List<Integer>> configGroup) {
        for (List<Integer> group : configGroup) {
            if (group.size() >= 2) {
                int first = group.get(0);
                int second = group.get(1);
                if (config[first] != config[second]) {
                    return false;
                }
            }
        }
        return true;
    }
}