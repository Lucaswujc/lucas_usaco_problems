package USACOGold;

import java.util.*;

public class DilucAndKaeya {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t > 0) {
            int n = scan.nextInt();
            String s = scan.next();

            int dNum = 0;
            int kNum = 0;
            HashMap<Integer, HashMap<Integer, Integer>> prevRatios = new HashMap<>();
            int[] maxPrefChunks = new int[s.length()];
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'D') {
                    dNum++;
                } else if (s.charAt(i) == 'K') {
                    kNum++;
                }
                int gcd = gcd(dNum, kNum);
                int dRatio = dNum / gcd;
                int kRatio = kNum / gcd;
                if (!prevRatios.containsKey(dRatio)) {
                    prevRatios.put(dRatio, new HashMap<>());
                }
                prevRatios.get(dRatio).put(kRatio, prevRatios.get(dRatio).getOrDefault(kRatio, 0) + 1);
                maxPrefChunks[i] = prevRatios.get(dRatio).get(kRatio);
            }

            for (int i = 0; i < s.length() - 1; i++) {
                System.out.print(maxPrefChunks[i] + " ");
            }
            System.out.println();
            t--;
        }
        scan.close();
    }
}