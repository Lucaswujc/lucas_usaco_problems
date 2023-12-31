package usaco_bronze_booster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//@formatter:off
/**
 * Cowntact Tracing [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Farmer John is worried for the health of his cows (conveniently numbered 1…𝑁 as always) after an
 * outbreak of the highly contagious bovine disease COWVID-19.
 * 
 * Recently, Farmer John tested all of his cows and found some of them to be positive for the
 * disease. Using video footage from inside his barn, he is able to review recent interactions
 * between pairs of cows --- it turns out that when cows greet each-other, they shake hooves, a
 * gesture that can unfortunately spread the infection from one cow to another. Farmer John
 * assembles a time-stamped list of interacting pairs of cows, with entries of the form (𝑡,𝑥,𝑦) ,
 * meaning that at time 𝑡 , cow 𝑥 shook hooves with cow 𝑦 . Farmer John also knows the following:
 * 
 * (i) Exactly one cow on his farm could have started out carrying the disease (we'll call this cow
 * "patient zero").
 * 
 * (ii) Once a cow is infected, she passes the infection along with her next 𝐾 hoof shakes
 * (possibly including the same partner cow several times). After shaking hooves 𝐾 times, she no
 * longer passes the infection along with subsequent hoof shakes (since at this point she realizes
 * she is spreading the infection and washes her hooves carefully).
 * 
 * (iii) Once a cow is infected, she stays infected.
 * 
 * Unfortunately, Farmer John doesn't know which of his 𝑁 cows is patient zero, nor does he know
 * the value of 𝐾 ! Please help him narrow down the possibilities for these unknowns based on his
 * data. It is guaranteed that at least one possibility is valid.
 * 
 * INPUT FORMAT:
 * 
 * The first line of the input file contains 𝑁 (2≤𝑁≤100 ) and 𝑇 (1≤𝑇≤250 ). The next line
 * contains a string of length 𝑁 whose entries are 0s and 1s, describing the current state of
 * Farmer John's 𝑁 cows --- 0 represents a healthy cow and 1 represents a cow presently with the
 * disease. Each of the next 𝑇 lines describes a record in Farmer John's list of interactions and
 * consists of three integers 𝑡 , 𝑥 , and 𝑦 , where 𝑡 is a positive integer time of the
 * interaction (𝑡≤250 ) and 𝑥 and 𝑦 are distinct integers in the range 1…𝑁 , indicating which
 * cows shook hands at time 𝑡 . At most one interaction happens at each point in time.
 * 
 * OUTPUT FORMAT:
 * 
 * Print a single line with three integers 𝑥 , 𝑦 , and 𝑧 , where 𝑥 is the number of possible
 * cows who could have been patient zero, 𝑦 is the smallest possible value of 𝐾 consistent with
 * the data, and 𝑧 is the largest possible value of 𝐾 consistent with the data (if there is no
 * upper bound on 𝐾 that can be deduced from the data, print "Infinity" for 𝑧 ). Note that it
 * might be possible to have 𝐾=0 .
 * 
 * SAMPLE INPUT:
 * 
 * 4 3 
 * 1100 
 * 7 1 2 
 * 5 2 3 
 * 6 2 4 
 * 
 * SAMPLE OUTPUT:
 * 
 * 1 1 Infinity 
 * 
 * The only candidate for patient zero is cow 1. For all 𝐾>0 , cow 1 infects cow 2 at
 * time 7, while cows 3 and 4 remain uninfected.
 */
//@formatter:on

class Contact implements Comparable<Contact>{
    int time;
    int c1, c2;

    public Contact(int time, int c1, int c2) {
        this.time = time;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public int compareTo(Contact o) {
        if (o ==null)
            return -1;
        return this.time - o.time;
    }

}
public class ContactTracing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int T = scan.nextInt();
        scan.nextLine();
        String s = scan.nextLine();
        char[] cows = s.toCharArray();        
        Contact[] contacts = new Contact[T];
        for (int i = 0; i < T; i++) {
            contacts[i] = new Contact(scan.nextInt(), scan.nextInt()-1, scan.nextInt()-1);
        }
        Arrays.sort(contacts);
        Set<Integer> p0 = new HashSet<>();
        int minK = Integer.MAX_VALUE;
        int maxK = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (cows[i] == '1') {
                // possible patient zero ; 
                for (int k = 0; k < contacts.length; k++) {
                    if (simulate(i, k, cows, contacts)) {
                        p0.add(i);
                        if (k < minK)
                            minK = k;
                        if (k > maxK)
                            maxK = k;
                    }
                }
            }
        }
        String maxks = String.valueOf(maxK);
        if (maxK == contacts.length - 1)
            maxks = "Infinity";
        System.out.println(String.format("%d %d %s", p0.size(), minK,maxks));
    }

    public static boolean simulate(int patient0, int spreadcount, char[] cows,Contact[] contacts){
        char[] simulatedCows = new char[cows.length];
        Arrays.fill(simulatedCows, '0');
        simulatedCows[patient0] = '1';
        int[] cowsSpreadCycles = new int[cows.length];
        cowsSpreadCycles[patient0] = spreadcount;
        for (Contact c : contacts) {
            if (simulatedCows[c.c1] != simulatedCows[c.c2]) {
                int healthyCow = simulatedCows[c.c1] == '0' ? c.c1 : c.c2;
                int spreadingCow = simulatedCows[c.c1] == '0' ? c.c2 : c.c1;
                if (cowsSpreadCycles[spreadingCow] > 0) {
                    simulatedCows[healthyCow] = '1';
                    cowsSpreadCycles[healthyCow] = spreadcount;
                    cowsSpreadCycles[spreadingCow] -= 1;
                }
            }
        }
        boolean ret = true;
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] != simulatedCows[i]) {
                ret = false;
                break;
            }
        }
        return ret;
    }

}
