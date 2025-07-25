package USACOSilver;
import java.util.*;
/*Bessie and Elsie have discovered a row of N
 cakes (2≤N≤5⋅105,N even)
 cakes, with sizes a1,a2,…,aN
 in that order (1≤ai≤109).

Each cow wants to eat as much as possible. However, being very civilized cows, they decided to play a game to split it! The game proceeds with both cows alternating turns. Each turn consists of one of the following:

Bessie chooses two adjacent cakes and stacks them, creating a new cake with the sum of the sizes.
Elsie chooses either the leftmost or rightmost cake and puts it in her stash.
When only one cake remains, Bessie eats it, and Elsie eats all cakes in her stash. If both cows play optimally to maximize the amount of cake they eat and Bessie plays first, how much cake will each cow eat?

INPUT FORMAT (input arrives from the terminal / stdin):
Each input consists of T (1≤T≤10) independent test cases. It is guaranteed that the sum of all N
 within an input does not exceed 106
Each test case is formatted as follows. The first line contains N. The next line contains N 
space-separated integers, a1,a2,…,aN.

OUTPUT FORMAT (print output to the terminal / stdout):
For each test case, output a line containing b and e, 
representing the amounts of cake Bessie and Elsie respectively will consume if both cows play optimally.
SAMPLE INPUT:
2
4
40 30 20 10
4
10 20 30 40
SAMPLE OUTPUT:
60 40
60 40
For the first test case, under optimal play,

Bessie will stack the middle two cakes. The cakes now have sizes [40,50,10]
.
Elsie will eat the leftmost cake. The remaining cakes now have sizes [50,10]
.
Bessie stacks the remaining two cakes.
Bessie will eat 30+20+10=60 cake, while Elsie will eat 40
 cake.

The second test case is the reverse of the first, so the answer is the same. */
public class CakeGame {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        //input t
        int t = scan.nextInt();
        while (t > 0) {
            //input n, and n cakes
            int n = scan.nextInt();;
            int[] cakes = new int[n];
            for (int i = 0; i < n; i++) {
                cakes[i] = scan.nextInt();
                
            }
            //prefix
            long[] prefix = new long[n+1];
            for(int i = 0; i < n; i++){
                prefix[i+1] = prefix[i] + cakes[i];
            }
            //n is even, elsie will always take one away and bessie will always take one away, 
            //resulting in 2 taken every time, meaning elsie will take n/2 cakes total
            //and the cake that bessie will eat should be an interval over n/2
            //we interateb over all possible intervals to find the min (this will be the most optimal for elsie)
            //because elsie controls what cakes bessie can get and e = total - b
            //I used prefix sums to optimize
            long total = prefix[n];
            long b = solve(prefix, n);
            long e = total - b; //elsie is remainder of cakes
            System.out.println(b + " " + e);
            t--;
        }

        scan.close();
    }
    static long solve(long[] prefix, int n){
        //iteratye up to mid because elsie can only eat n/2 cakes
        int mid = n / 2 - 1; //dec by 1 because 0 indexing
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= mid; i++) {
            //sum of bessie's cakes is from n - (mid - i) - i = n - mid + i - i = n - mid
            int start = i;
            int end =  n - (mid - i);
            long ans = prefix[end] - prefix[start];
            if (ans < min) {
                min = ans;
            }
        }
        return min;
    }
}