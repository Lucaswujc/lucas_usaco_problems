package usaco_bronze_booster;

//@formatter:off
/**
 * Swapity Swap [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Farmer John's 𝑁 cows (1≤𝑁≤100 ) are standing in a line. The 𝑖 th cow from the left has label
 * 𝑖 , for each 1≤𝑖≤𝑁 . Farmer John has come up with a new morning exercise routine for the cows.
 * He tells them to repeat the following two-step process exactly 𝐾 (1≤𝐾≤109 ) times:
 * 
 * The sequence of cows currently in positions 𝐴1…𝐴2 from the left reverse their order
 * (1≤𝐴1<𝐴2≤𝑁 ). Then, the sequence of cows currently in positions 𝐵1…𝐵2 from the left reverse
 * their order (1≤𝐵1<𝐵2≤𝑁 ). After the cows have repeated this process exactly 𝐾 times, please
 * output the label of the 𝑖 th cow from the left for each 1≤𝑖≤𝑁 .
 * 
 * SCORING:
 * 
 * Test cases 2-3 satisfy 𝐾≤100 . Test cases 4-13 satisfy no additional constraints. INPUT FORMAT:
 * 
 * The first line of input contains 𝑁 and 𝐾 . The second line contains 𝐴1 and 𝐴2 , and the third
 * contains 𝐵1 and 𝐵2 . 
 * OUTPUT FORMAT:
 * 
 * On the 𝑖 th line of output, print the label of the 𝑖 th cow from the left at the end of the
 * exercise routine. 
 * SAMPLE INPUT:
 * 
 * 7 2 
 * 2 5 
 * 3 7 
 * SAMPLE OUTPUT:
 * 
 * 1
 * 2 
 * 4 
 * 3 
 * 5 
 * 7 
 * 6 
 * Initially, the order of the cows is [1,2,3,4,5,6,7] from left to right. After the
 * first step of the process, the order is [1,5,4,3,2,6,7]. After the second step of the process,
 * the order is [1,5,7,6,2,3,4] . Repeating both steps a second time yields the output of the
 * sample.
 */
//@formatter:on
import java.util.Scanner;
import java.util.Arrays;

public class SwapitySwap {
    public static void main(String[] argsv) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = i + 1;
        }
        int K = scan.nextInt();
        int[][] operations = new int[2][2];
        for (int j = 0; j < 2; j++) {
            operations[j][0] = scan.nextInt() - 1;
            operations[j][1] = scan.nextInt() - 1;
        }

        scan.close();
        solution1(Arrays.copyOf(cows, cows.length), operations, K);
        solution2(operations, K, N);
    }

    /**
     * solution 2 is to for each cow, we can identify that after X iterations, this cow will go back
     * to its original location. then we can establish a array of X, then for each cow, we can use K
     * % Xi to identify its location after K iterations.
     * 
     * @param cows
     * @param operations
     * @param K
     */
    public static void solution2(int[][] operations, int K, int N) {
        int[] cows = new int[N];
        for (int i = 0; i < cows.length; i++) {
            int xi = 1;
            int nextLocation = getNextLocation(i,operations);
            while (i != nextLocation) {
                xi += 1;
                nextLocation = getNextLocation(nextLocation, operations);
            }
            nextLocation = i;
            for (int j = 0; j < K % xi; j++) {
                nextLocation = getNextLocation(nextLocation, operations);
            }
            cows[nextLocation] = i + 1;
        }
    }

    public static int getNextLocation(int currentLocation, int[][] operations) {
        for (int i = 0; i < operations.length; i++) {
            if (currentLocation <= operations[i][1] && currentLocation >= operations[i][0])
                currentLocation = operations[i][0] + operations[i][1] - currentLocation;
        }
        return currentLocation;
    }

    /**
     * consider the situation that aftre X operations, the cows array returns to the original state,
     * then we only need to run K%X + X iterations. the task is to find the X b
     * 
     * @param cows
     * @param operations
     * @param K
     */
    public static void solution1(int[] cows, int[][] operations, int K) {
        int counter = 0;
        while (true) {
            for (int j = 0; j < 2; j++) {
                int start = operations[j][0];
                int end = operations[j][1];
                swap(cows, start, end);
            }
            counter++;
            if (check(cows))
                break;
        }
        for (int i = 0; i < K % counter; i++) {
            for (int j = 0; j < 2; j++) {
                int start = operations[j][0];
                int end = operations[j][1];
                swap(cows, start, end);
            }
        }
        for (int i = 0; i < cows.length; i++) {
            System.out.println(cows[i]);
        }
    }

    public static boolean check(int[] cows) {
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] != i + 1)
                return false;
        }
        return true;
    }

    public static void swap(int[] cows, int start, int end) {
        while (start < end) {
            int temp = cows[start];
            cows[start] = cows[end];
            cows[end] = temp;
            start++;
            end--;
        }
    }

}
