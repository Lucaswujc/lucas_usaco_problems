package usaco_bronze_booster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Livestock Lineup [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Every day, Farmer John milks his 8 dairy cows, named Bessie, Buttercup, Belinda, Beatrice, Bella,
 * Blue, Betsy, and Sue.
 * 
 * The cows are rather picky, unfortunately, and require that Farmer John milks them in an order
 * that respects 𝑁 constraints (1≤𝑁≤7 ). Each constraint is of the form "𝑋 must be milked beside
 * 𝑌 ", stipulating that cow 𝑋 must appear in the milking order either directly after cow 𝑌 or
 * directly before cow 𝑌 .
 * 
 * Please help Farmer John determine an ordering of his cows that satisfies all of these required
 * constraints. It is guaranteed that an ordering is always possible. If several orderings work,
 * then please output the one that is alphabetically first. That is, the first cow should have the
 * alphabetically lowest name of all possible cows that could appear first in any valid ordering.
 * Among all orderings starting with this same alphabetically-first cow, the second cow should be
 * alphabetically lowest among all possible valid orderings, and so on.
 * 
 * INPUT FORMAT:
 * 
 * The first line of input contains 𝑁 . The next 𝑁 lines each contain a sentence describing a
 * constraint in the form "𝑋 must be milked beside 𝑌 ", where 𝑋 and 𝑌 are names of some of
 * Farmer John's cows (the eight possible names are listed above). OUTPUT FORMAT:
 * 
 * Please output, using 8 lines, an ordering of cows, one cow per line, satisfying all constraints.
 * If multiple orderings work, output the one that is alphabetically earliest. SAMPLE INPUT:
 * 
 * 3 Buttercup must be milked beside Bella Blue must be milked beside Bella Sue must be milked
 * beside Beatrice
 * 
 * SAMPLE OUTPUT:
 * 
 * Beatrice Sue Belinda Bessie Betsy Blue Bella Buttercup
 */
public class LiveStockLineup {
    static String[] cows = "Bessie,Buttercup,Belinda,Beatrice,Bella,Blue,Betsy,Sue".split(",");
    static HashMap<String, Integer> dict = new HashMap<>();
    static {
        // sort the cows alphabetically
        Arrays.sort(cows);
        // create a lookup for quick index search
        for (int i = 0; i < cows.length; i++) {
            dict.put(cows[i], i);
        }
    }

    public static void main(String[] argsv) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[][] rules = new String[n][2];
        int[][] rulesmatrix = new int[n][2];
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scan.nextLine();
            String[] p = s.split("must be milked beside");
            String p1 = p[0].trim();
            String p2 = p[1].trim();
            rules[i][0] = p1;
            rules[i][1] = p2;
            rulesmatrix[i][0] = dict.get(p1);
            rulesmatrix[i][1] = dict.get(p2);
        }
        // Arrays.stream(rules).forEach(s -> System.out.println(s[0]+"|"+s[1]));
        // construct the initial list as int array instead of strings
        int[] input = new int[cows.length];
        for (int i = 0; i < input.length; i++)
            input[i] = i;
        permutation(input, 0, rulesmatrix);
        scan.close();
        char[] cs = result.toCharArray();
        for (char c : cs) {
            int i = c - '0';
            System.out.println(cows[i]);
        } 
    }

    /**
     * Heaps full permutation algorithm , recursive ,
     * 
     * @param input
     * @param left
     * @param rulesmatrix -- for rules check when reached to the end of recursive call
     * @return
     */

    static String result = "77777777"; 

    public static void permutation(int[] input, int left, int[][] rulesmatrix) {
        if (left == input.length - 1) {
            if (checkrules(input, rulesmatrix)) {
                String r = "";
                for (int x : input) {
                    r = r + String.valueOf(x);
                }
                if (Integer.valueOf(r) < Integer.valueOf(result))
                    result = r;
            }
        } else {
            for (int i = left; i < input.length; i++) {
                swap(input, left, i);
                permutation(input, left + 1, rulesmatrix);
                swap(input, i, left);
            }
        }
    }

    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static boolean checkrules(int[] input, int[][] rulesmatrix) {
        for (int i = 0; i < rulesmatrix.length; i++) {
            int[] rule = rulesmatrix[i];
            int c1 = rule[0];
            int c2 = rule[1];
            int p1 = 0;
            int p2 = 0;
            for (int j = 0; j < input.length; j++) {
                if (input[j] == c1)
                    p1 = j;
                if (input[j] == c2)
                    p2 = j;
            }
            if ((p1 - p2) * (p1 - p2) != 1)
                return false;
        }
        // passed all the rules , hence this is a answer that we can accept
        //Arrays.stream(input).forEach(s -> System.out.println(cows[s]));
        return true;
    }
}
