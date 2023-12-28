package usaco_bronze_booster;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Photoshoot
 *
 * Farmer John is lining up his 𝑁 cows (2≤𝑁≤103 ), numbered 1…𝑁 , for a photoshoot. FJ initially
 * planned for the 𝑖 -th cow from the left to be the cow numbered 𝑎𝑖, and wrote down the
 * permutation 𝑎1,𝑎2,…,𝑎𝑁 on a sheet of paper. Unfortunately, that paper was recently stolen by
 * Farmer Nhoj!
 *
 * Luckily, it might still possible for FJ to recover the permutation that he originally wrote down.
 * Before the sheet was stolen, Bessie recorded the sequence 𝑏1,𝑏2,…,𝑏𝑁−1 that satisfies
 * 𝑏𝑖=𝑎𝑖+𝑎𝑖+1 for each 1≤𝑖<𝑁.
 *
 * Based on Bessie's information, help FJ restore the "lexicographically minimum" permutation 𝑎
 * that could have produced 𝑏 . A permutation 𝑥 is lexicographically smaller than a permutation 𝑦
 * if for some 𝑗 , 𝑥𝑖=𝑦𝑖 for all 𝑖<𝑗 and 𝑥𝑗<𝑦𝑗 (in other words, the two permutations are
 * identical up to a certain point, at which 𝑥 is smaller than 𝑦 ). It is guaranteed that at least
 * one such 𝑎 exists.
 *
 * SCORING:
 *
 * Test cases 2-4 satisfy 𝑁≤8. Test cases 5-10 satisfy no additional constraints. INPUT FORMAT:
 *
 * The first line of input contains a single integer 𝑁. The second line contains 𝑁−1
 * space-separated integers 𝑏1,𝑏2,…,𝑏𝑁−1.
 *
 * OUTPUT FORMAT:
 *
 * A single line with 𝑁 space-separated integers 𝑎1,𝑎2,…,𝑎𝑁.
 *
 *
 * SAMPLE INPUT:
 *
 * 5
 * 4 6 7 6
 *
 * SAMPLE OUTPUT:
 *
 * 3 1 5 2 4
 *
 * 𝑎 produces 𝑏 because 3+1=4 , 1+5=6 , 5+2=7 , and 2+4=6.
 *
 * Problem credits: Benjamin Qi and Chris Zhang
 */
//@formatter:on
public class PhotoShoot {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] b = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            b[i] = scan.nextInt();
        }
        int[] ans = new int[N];
        boolean[] used = new boolean[N];
        for (int j = 0; j < N; j++) {
            used[j] = true;
            int ak = j+1;
            boolean success = true;
            for (int k = 0; k < N - 1; k++) {
                int bk = b[k];
                ans[k] = ak;
                int ak1 = bk - ak;
                if (used[ak1-1]) {
                    // not working
                    ans = new int[N];
                    used = new boolean[N];
                    success = false;
                    break;
                }
                used[ak1-1] = true;
                ans[k + 1] = ak1;
                ak = ak1;
            }
            if (success) {
                Arrays.stream(ans).forEach(x -> System.out.printf("%d ", x));
                System.out.println();
                break;
            }
        }
    }
}
