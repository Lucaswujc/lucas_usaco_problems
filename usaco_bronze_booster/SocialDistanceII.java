package usaco_bronze_booster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//@formatter:off
/**
 * Social Distancing II [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Farmer John is worried for the health of his Cows after an outbreak of the highly contagious
 * bovine disease CowVID-19. Despite his best attempt at making his 𝑁 Cows (1≤𝑁≤1000 ) practice
 * "social distancing", many of them still unfortunately contracted the disease. The Cows,
 * conveniently numbered 1…𝑁 , are each standing at distinct points along a long path (essentially
 * a one-dimensional number line), with Cow 𝑖 standing at position 𝑥𝑖 . Farmer John knows that
 * there is a radius 𝑅 such that any Cow standing up to and including 𝑅 units away from an
 * infected Cow will also become infected (and will then pass the infection along to additional Cows
 * within 𝑅 units away, and so on).
 * 
 * Unfortunately, Farmer John doesn't know 𝑅 exactly. He does however know which of his Cows are
 * infected. Given this data, please determine the minimum possible number of Cows that were
 * initially infected with the disease.
 * 
 * INPUT FORMAT:
 * 
 * The first line of input contains 𝑁 . The next 𝑁 lines each describe one Cow in terms of two
 * integers, 𝑥 and 𝑠 , where 𝑥 is the position (0≤𝑥≤106 ), and 𝑠 is 0 for a healthy Cow or 1
 * for a sick Cow. At least one Cow is sick, and all Cows that could possibly have become sick from
 * spread of the disease have now become sick.
 * 
 * OUTPUT FORMAT:
 * 
 * Please output the minimum number of Cows that could have initially been sick, prior to any spread
 * of the disease.
 * 
 * SAMPLE INPUT:
 * 
 * 6 
 * 7 1 
 * 1 1 
 * 15 1 
 * 3 1 
 * 10 0 
 * 6 1 
 * 
 * SAMPLE OUTPUT:
 * 
 * 3 In this example, we know that 𝑅<3 since otherwise the Cow at position 7 would have infected
 * the Cow at position 10. Therefore, at least 3 Cows must have started out infected -- one of the
 * two Cows at positions 1 and 3, one of the two Cows at positions 6 and 7, and the Cow at position
 * 15.
 * 
 * Problem credits: Brian Dean
 */
//@formatter:on




public class SocialDistanceII {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Cow[] Cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            Cow c = new Cow(scan.nextInt(), scan.nextInt() == 1);
            Cows[i] = c;
        }
        Arrays.sort(Cows);
        int r = getSmallestGap(Cows);
        int lastIdx = Cows[0].idx;
        boolean lastsick = Cows[0].sick;
        int cnt = 0;
        if (lastsick) {
            cnt++;
        }
        for (int i = 0; i < Cows.length; i++) {
            // not a sick Cow, not increasing counter
            if (!Cows[i].sick) {
                lastIdx = Cows[i].idx;
                lastsick = Cows[i].sick;
                continue;
            }
            // a sick Cow appears after a healthy Cow, increase counter
            if (Cows[i].sick && !lastsick) {
                cnt += 1;
                lastIdx = Cows[i].idx;
                lastsick = Cows[i].sick;
                continue;
            }
            // a sick Cow appears after a sick Cow , compare whether fall within R
            if (Cows[i].idx > lastIdx + r) {
                cnt += 1;
            }
            lastIdx = Cows[i].idx;
            lastsick = Cows[i].sick;
        }
        System.out.println(cnt);
    }

    public static int getSmallestGap(Cow[] Cows) {
        int lastIndex = Cows[0].idx;
        boolean lastsick = Cows[0].sick;
        int r = Integer.MAX_VALUE;
        for (int i = 0; i < Cows.length; i++) {
            if (lastsick != Cows[i].sick) {
                r = Cows[i].idx - lastIndex - 1 < r ? Cows[i].idx - lastIndex - 1 : r;
            }
            lastsick = Cows[i].sick;
            lastIndex = Cows[i].idx;
        }
        return r;
    }

    static class Cow{
        int idx;
        boolean sick;
        Cow(int idx, boolean sick){
            this.idx = idx;
            this.sick = sick;
        }
    }
}
