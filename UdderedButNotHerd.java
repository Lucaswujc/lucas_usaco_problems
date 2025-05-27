import java.io.*;
import java.util.*;
public class UdderedButNotHerd {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String heard = in.readLine();
        Map<Character, Integer> index = new HashMap<>();
        for (char letter : heard.toCharArray()) {
            if (!index.containsKey(letter)) {
                index.put(letter, index.size());
            }
        }
        int n = index.size();
        int[][] adjacent = new int[n][n];
        for (int j = 1; j < heard.length(); j++) {
            adjacent[index.get(heard.charAt(j - 1))][index.get(heard.charAt(j))]++;
        }
        int[][] sums = new int[n][1 << n];
        int[] dp = new int[1 << n];
        dp[0] = 1;
        for (int i = 1; i < (1 << n); i++) {
            dp[i] = heard.length();
            int k = 0;
            while ((i & (1 << k)) == 0) {
                k++;
            }
            for (int j = 0; j < n; j++) {
                sums[j][i] = sums[j][i - (1 << k)] + adjacent[j][k];
                if ((i & (1 << j)) != 0) {
                    dp[i] = Math.min(dp[i], dp[i - (1 << j)] + sums[j][i]);
                }
            }
        }
        System.out.println(dp[(1 << n) - 1]);
    }
}