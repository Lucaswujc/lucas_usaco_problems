package USACOSilver;

import java.util.*;
public class CircularBarn {
    public static void main(String[] args) {
        boolean[] isPrime = new boolean[5000001];
        for (int p = 1; p <= 5000000; p++) {
            isPrime[p] = true;
        }
        for (int p = 2; p <= 5000000; p++) {
            if (isPrime[p]) {
                for (int k = 2 * p; k <= 500000; k += p) {
                    isPrime[k] = false;
                }
            }
        }
        int[] lastPrimes = new int[4];
        int[] steps = new int[5000000 + 1];
        for (int k = 1; k <= 5000000; k++) {
            if (k % 2 == 0) {
                steps[k] = k / 2;
            } else {
                if (isPrime[k]) {
                    lastPrimes[k % 4] = k;
                }
                steps[k] = 1 + steps[k - lastPrimes[k % 4]];
            }
        }
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int[] rooms = new int[n];
            for (int j = 0; j < n; j++) {
                rooms[j] = scan.nextInt();
            }

            boolean johnWins = true;
            int minSteps = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int stepsHere = steps[rooms[j]] / 2;
                if (stepsHere < minSteps) {
                    minSteps = stepsHere;
                    johnWins = steps[rooms[j]] % 2 == 1;
                }
            }
            if(johnWins){
                System.out.println("Farmer John");
            }
            else{
                System.out.println("Farmer Nhoj");
            }
            t--;
        }
        scan.close();
    }
}
