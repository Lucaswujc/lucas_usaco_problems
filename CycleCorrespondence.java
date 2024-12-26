import java.util.*;
public class CycleCorrespondence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] b = new int[k];
        int[] e = new int[k];
        for (int i = 0; i < k; i++) {
            b[i] = scan.nextInt();
        }
        for (int i = 0; i < k; i++) {
            e[i] = scan.nextInt();
        }

        Set<Integer> sameOutside = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            sameOutside.add(i);
        }
        for (int val : b) {
            sameOutside.remove(val);
        }
        for (int val : e) {
            sameOutside.remove(val);
        }
        

        int[] reverse = new int[k];
        for (int i = 0; i < k; i++) {
            reverse[i] = e[k - i - 1];
        }

        int result = sameOutside.size() + Math.max(maxRotation(b, e, k), maxRotation(b, reverse, k));

        System.out.println(result);
        scan.close();
    }
    static int maxRotation(int[] cycleA, int[] cycleB, int k) {
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < k; i++) {
            pos.put(cycleA[i], i);
        }

        int[] withOffset = new int[k];
        for (int i = 0; i < k; i++) {
            if (pos.containsKey(cycleB[i])) {
                int offset = (i - pos.get(cycleB[i]) + k) % k;
                withOffset[offset]++;
            }
        }

        int max = 0;
        for (int count : withOffset) {
            max = Math.max(max, count);
        }
        return max;
    }
}