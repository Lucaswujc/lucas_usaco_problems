package usaco_bronze_booster;

import java.util.Scanner;

//@formatter:off
/**
 * Cow Gymnastics [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * In order to improve their physical fitness, the cows have taken up gymnastics! Farmer John
 * designates his favorite cow Bessie to coach the 𝑁 other cows and to assess their progress as
 * they learn various gymnastic skills.
 * 
 * In each of 𝐾 practice sessions (1≤𝐾≤10 ), Bessie ranks the 𝑁 cows according to their
 * performance (1≤𝑁≤20 ). Afterward, she is curious about the consistency in these rankings. A pair
 * of two distinct cows is consistent if one cow did better than the other one in every practice
 * session.
 * 
 * Help Bessie compute the total number of consistent pairs.
 * 
 * INPUT FORMAT:
 * 
 * The first line of the input file contains two positive integers 𝐾 and 𝑁 . 
 * The next 𝐾 lines will each contain the integers 1…𝑁 in some order, indicating the rankings 
 * of the cows (cows are identified by the numbers 1…𝑁 ). 
 * If 𝐴 appears before 𝐵 in one of these lines, that means cow 𝐴 did better than cow 𝐵 . 
 * 
 * OUTPUT FORMAT: Output, on a single line, the number of consistent pairs. 
 * SAMPLE INPUT:
 * 3 4 
 * 4 1 2 3 
 * 4 1 3 2 
 * 4 2 1 3 
 * SAMPLE OUTPUT:
 * 4 
 * The consistent pairs of cows are (1,4) , (2,4) , (3,4) , and (1,3) .
 * 
 * Problem credits: Nick Wu
 */
//@formatter:on

public class CowGymnastics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt(); // k exercises
        int n = scan.nextInt(); // n cows
        int[][] initialInput = new int[k][n];
        // capture the input from console
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                initialInput[i][j] = scan.nextInt();
            }

            solution2(initialInput);
        }

    }

    public static void solution2(int[][] input) {
        // use the input data, we can detect all pairs of i,j whether i appears ahead of j in all
        // sessions
        int k = input.length;
        int n = input[0].length;
        int answer = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int i_better_j_cnt = 0;
                // in each excercise detect whether i is better than j
                for (int excercise = 0; excercise < k; excercise++) {
                    int pos_i = 0;
                    int pos_j = 0;
                    for (int t = 0; t < n; t++) {
                        if (input[excercise][t] == i)
                            pos_i = t;
                        if (input[excercise][t] == j)
                            pos_j = t;
                    }
                    // if pos_i < pos_j, then i perfrms better than j in this excercise
                    i_better_j_cnt = (pos_i < pos_j ? i_better_j_cnt + 1 : i_better_j_cnt);
                }
                if (i_better_j_cnt == k) {
                    System.out.println(String.format("(%d,%d)", i, j));
                    answer += 1;
                }
            }
        System.out.println(answer);
    }

    public static void solution1(int[][] input) {
        // construct a N x N matrix of int, inital value 0
        // use this matrix to store whether i,j is consistently 1 , -1 or -2 .
        // will only set the values for staets[i][j] where i < j,
        // the matrix is only filled upper half (diagnal half), bottom half is wasted for zero fills
        // state[i][j] == 1 if the i is ahead of j, 0 if equal, -1 if i is after j
        // if a flip is detected, the cell will be set to -2
        // count numboer of non -2 for the upper half of the matrix
        int n = input[0].length;
        int k = input.length;
        int[][] states = new int[n][n];
        for (int i = 0; i < k; i++) {
            int[] row = new int[n];
            for (int j = 0; j < n; j++) {
                int x = input[i][j];
                for (int s = 0; s <= j - 1; s++) {
                    int y = row[s];
                    if (x == y) {
                        states[x][y] = 0;
                        continue;
                    }
                    if (x < y) {
                        if (states[x - 1][y - 1] == 0 || states[x - 1][y - 1] == -1)
                            states[x - 1][y - 1] = -1;
                        else
                            // set to -2 as flipped (inconsistent)
                            states[x - 1][y - 1] = -2;
                    } else {
                        if (states[y - 1][x - 1] == 0 || states[y - 1][x - 1] == 1)
                            states[y - 1][x - 1] = 1;
                        else
                            states[y - 1][x - 1] = -2;
                    }
                }
                // append the header
                row[j] = x;
            }
        }

        // now count the upper half of the matrix where i < j
        int result = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (states[i][j] != -2) {
                    System.out.println(String.format("(%d,%d)", i + 1, j + 1));
                    result = result + 1;
                }
        System.out.println(result);
    }
}
