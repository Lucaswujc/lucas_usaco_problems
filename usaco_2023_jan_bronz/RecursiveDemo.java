package usaco_2023_jan_bronz;

import java.util.HashMap;
import java.util.Map;

public class RecursiveDemo {

    public static void main(String[] args) {
        int i = 60;
//        System.out.println("==============fibonacci sequence demo=============");
//        System.out.println(fibonacci(i, "", new HashMap<Integer, Long>()));
//        //System.out.println("==============binary string construction demo=============");
//       // binaryStringDemo("", "", i);
        //System.out.println("==============hanoi tower demo=============");
        //haoi(i, 'A', 'B', 'C');
    }

    /**
     * fibonacci sequence
     * if i = 0: F[0]  = 0
     * if i = 1 : F[1] = 1
     * F[i] = F[i-1] + F[i-2]
     */
    static long fibonacci(int i, String indent, Map<Integer, Long> cache) {
        if (i == 0) {
            cache.put(0, 0l);
            return 0;
        }
        ;
        if (i == 1) {
            cache.put(1, 1l);
            return 1;
        }
        indent += "\t";
        if (cache.containsKey(i)) {
            System.out.println(String.format("cache hit for f[%d] = %d", i, cache.get(i)));
            return cache.get(i);
        } else {
            System.out.println(indent + String.format("f[%d] = f[%d] + f[%d]", i, i - 1, i - 2));
            long fi1 = fibonacci(i - 1, indent, cache);
            long fi2 = fibonacci(i - 2, indent, cache);
            cache.put(i, fi1 + fi2);
            return fi1 + fi2;
        }

    }

    /**
     * use the recursive logic to produce full permutation of binary strings with length of N
     * logical thinking, the final string with length of N (denoted as RESULT[N] is looking for N-1 results and add
     * T / F to each and every N-1 results. RESULT[N] = (RESULT[N-1] + "T") UNION (RESULT[N-1]+"F")
     *
     * @param currentString
     * @param indent
     * @param expectedLengh
     */
    public static void binaryStringDemo(String currentString, String indent, int expectedLengh) {
        System.out.print(indent);
        indent = indent + "\t";
        if (currentString.length() == expectedLengh) {
            System.out.println("final string is : " + currentString);
            return;
        } else {
            System.out.println("currenString = \"" + currentString + "\"");
            binaryStringDemo(currentString + "T", indent, expectedLengh);
            binaryStringDemo(currentString + "F", indent, expectedLengh);
        }
    }

    /**
     * Hanoi tower demo
     * Tower of Hanoi is a mathematical puzzle where we have three rods (A, B, and C) and N disks.
     * Initially, all the disks are stacked in decreasing value of diameter i.e., the smallest disk is
     * placed on the top and they are on rod A. The objective of the puzzle is to move the entire stack
     * to another rod (here considered C), obeying the following simple rules:
     * <p>
     * Only one disk can be moved at a time.
     * Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack
     * i.e. a disk can only be moved if it is the uppermost disk on a stack.
     * No disk may be placed on top of a smaller disk.
     * <p>
     * a sample strategy for three disk https://media.geeksforgeeks.org/wp-content/uploads/tower-of-hanoi.png
     * <p>
     * thinking process:
     * The idea is to use the helper node to reach the destination using recursion. Below is the pattern for this problem:
     * <p>
     * Shift ‘N-1’ disks from ‘A’ to ‘B’, using C.
     * Shift last disk from ‘A’ to ‘C’.
     * Shift ‘N-1’ disks from ‘B’ to ‘C’, using A.
     * Visual for 3 disks
     * <p>
     * initial state
     * A             B               C
     * 1
     * 2
     * 3

     * desired state
     * A             B               C
     *                               1
     *                               2
     *                               3

     * if we can achieve this as desired state -1 step, then problem is translated to after move 3 to C, and then move
     * 1,2 from B-->C using A as helper pol
     * A             B                C
     *               1
     *               2
     * 3

     * so the problem is three steps:
     * move n-1 disk from A-->B using C as the helper,
     * move 1 disk from A --> C,
     * move n-1 disk from B-->C using A as the helper
     * psuedo code
     * hanoitowner(n , from_pol,to_pol,helper_pol){
     *      honoitowner(n-1, from_pol, helper_pol, to_pol),
     *      move_disk_n_from_<from_pol>_to_<to_pol>
     *      hanoitowner(n-1, helper_pol, to_pol, from_pol)
     * }
     */
    static void haoi(int n, char from_rod,
                     char to_rod, char aux_rod) {
        if (n == 0) {
            return;
        }
        haoi(n - 1, from_rod, aux_rod, to_rod);
        System.out.println("Move disk " + n + " from rod "
                + from_rod + " to rod "
                + to_rod);
        haoi(n - 1, aux_rod, to_rod, from_rod);
    }


}
