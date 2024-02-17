package USACO_bronzeclass_problems;

import java.util.Scanner;
class Charm {
        int position; // position
        int length; // length

        public Charm(int position, int length) {
            this.position = position;
            this.length = length;
        }

    }
public class LuckyCharms {
    

    /**
     * L length of the bracelet
     * C # of charms
     * P_i is the location of a charm
     * S_i is the lenght of a charm
     * 
     * N: nails location from the left of the bracelet 1<=N <=L-1
     */

    public static void main(String[] args) {
        /**
         * scan input
         * create an array size of [C][2] to store P-i and S-i
         * 
         * use an array size of C to track answer.
         * for each position of charm, find the absolute value of the position
         * and the nail, and add the distance from the bracelet (S_i)
         * 
         * do we really need to store all these numbers???
         * by the time the si,pi arrives, we should be able to calculate the drop
         * distance directly
         */
        Scanner scan = new Scanner(System.in);
        scan.nextInt();
        int C = scan.nextInt();
        int N = scan.nextInt();
        Charm[] collectionOfCharms = new Charm[C];
        for (int line = 0; line < C; line++) {
            int Si = scan.nextInt();
            int Pi = scan.nextInt();
            collectionOfCharms[line] = new Charm(Pi, Si);

        }
        int[] dropDist = new int[C];
        for (int idx = 0; idx < C; idx++) {
            Charm aCharm = collectionOfCharms[idx];
            dropDist[idx] = Math.abs(aCharm.position - N) + aCharm.length;
        }
        scan.close();
    }
}
