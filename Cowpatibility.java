import java.io.*;
import java.util.*;

public class Cowpatibility {
    static int flavors = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cowpatibility.out"));

        int n = Integer.parseInt(br.readLine());
        int[][] cows = new int[n][flavors];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < flavors; j++) {
                cows[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(cows[i]);
        }

        List<Map<List<Integer>, Integer>> common = new ArrayList<>();
        for (int i = 0; i < flavors; i++) {
            common.add(new HashMap<>());
        }

        for (int i = 0; i < n; i++) {
            int[] cow = cows[i];
            common.get(4).merge(toList(cow), 1, Integer::sum);

            for (int a = 0; a < flavors; a++) {
                common.get(0).merge(List.of(cow[a]), 1, Integer::sum);
                for (int b = a + 1; b < flavors; b++) {
                    common.get(1).merge(List.of(cow[a], cow[b]), 1, Integer::sum);
                    for (int c = b + 1; c < flavors; c++) {
                        common.get(2).merge(List.of(cow[a], cow[b], cow[c]), 1, Integer::sum);
                        for (int d = c + 1; d < flavors; d++) {
                            common.get(3).merge(List.of(cow[a], cow[b], cow[c], cow[d]), 1, Integer::sum);
                        }
                    }
                }
            }
        }

        long compatible = 0;
        for (int i = 0; i < flavors; i++) {
            for (int count : common.get(i).values()) {
                long pairs = (long) count * (count - 1) / 2;
                if (i % 2 == 0) {
                    compatible += pairs;
                } else {
                    compatible -= pairs;
                }
            }
        }

        long totalPairs = (long) n * (n - 1) / 2;
        pw.println(totalPairs - compatible);
        pw.close();
        br.close();
    }

    private static List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int val : arr)
            list.add(val);
        return list;
    }
}
