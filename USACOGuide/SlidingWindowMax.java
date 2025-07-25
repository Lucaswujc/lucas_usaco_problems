package USACOGuide;

import java.util.*;

public class SlidingWindowMax {
    static TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();

    static void add(int x) {
        if (multiset.containsKey(x)) {
            multiset.put(x, multiset.get(x) + 1);
        } else {
            multiset.put(x, 1);
        }
    }

    static void remove(int x) {
        multiset.put(x, multiset.get(x) - 1);
        if (multiset.get(x) == 0) {
            multiset.remove(x);
        }
    }

    static ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            ret.add(multiset.lastKey());
            remove(nums[i - k]);
            add(nums[i]);
        }
        ret.add(multiset.lastKey());
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        ArrayList<Integer> result = maxSlidingWindow(nums, k);
        for (int max : result) {
            System.out.print(max + " ");
        }
        scan.close();
    }
}