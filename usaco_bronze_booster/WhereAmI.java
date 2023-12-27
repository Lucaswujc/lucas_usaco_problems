package usaco_bronze_booster;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//@formatter:off
/**
 * Where Am I? [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Farmer John has gone out for a walk down the road and thinks he may now be lost!
 * 
 * Along the road there are 𝑁 farms (1≤𝑁≤100 ) in a row. Farms unfortunately do not have house
 * numbers, making it hard for Farmer John to figure out his location along the road. However, each
 * farm does have a colorful mailbox along the side of the road, so Farmer John hopes that if he
 * looks at the colors of the mailboxes nearest to him, he can uniquely determine where he is.
 * 
 * Each mailbox color is specified by a letter in the range A..Z, so the sequence of 𝑁 mailboxes
 * down the road can be represented by a string of length 𝑁 containing letters in the range A..Z.
 * Some mailboxes may have the same colors as other mailboxes. Farmer John wants to know what is the
 * smallest value of 𝐾 such that if he looks at any sequence of 𝐾 consecutive mailboxes, he can
 * uniquely determine the location of that sequence along the road.
 * 
 * For example, suppose the sequence of mailboxes along the road is 'ABCDABC'. Farmer John cannot
 * set 𝐾=3 , since if he sees 'ABC', there are two possible locations along the road where this
 * consecutive set of colors might be. The smallest value of 𝐾 that works is 𝐾=4 , since if he
 * looks at any consecutive set of 4 mailboxes, this sequence of colors uniquely determines his
 * position along the road.
 * 
 * INPUT FORMAT:
 * 
 * The first line of input contains 𝑁 , and the second line contains a string of 𝑁 characters,
 * each in the range A..Z. OUTPUT FORMAT:
 * 
 * Print a line containing a single integer, specifying the smallest value of 𝐾 that solves Farmer
 * John's problem. 
 * SAMPLE INPUT:
 * 
 * 7 
 * ABCDABC 
 * SAMPLE OUTPUT:
 * 4
 */
//@formatter:on
public class WhereAmI {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String mailboxes = scan.next();
        solution1(n, mailboxes);
    }

    public static void solution1(int n, String mailboxes) {
        // iterate k, if k is a valid choice, then all substring
        // with the length of k in mailboxes is unique,
        for (int k = 1; k <= n; k++) {
            Set<String> temp = new HashSet<String>();
            boolean unique = true;
            for (int i = 0; i <= n - k; i++) {
                String sub = mailboxes.substring(i, i + k);
                if (temp.contains(sub)) {
                    unique = false;
                    break;
                } else {
                    temp.add(sub);
                }
            }
            if (unique) {
                System.out.println(k);
                break;
            }
        }
    }
}
