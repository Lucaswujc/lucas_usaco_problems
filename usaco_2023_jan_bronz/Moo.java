package usaco_2023_jan_bronz;

import java.util.Scanner;

public class Moo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(solve(line));
    }

    /**
     * Brutal force ??
     * check each character, calculate whether it is possible to turn the substr(i,i+2) to MOO
     * by doing this, it is obvious that for any position i (i >=1 i<= length -1), if string[i]
     * is O , then it will be possible to construct MOO, otherwise, it is not
     * so the problem translated to :

     * look for three letter string within the input which contains [M|O]O[M|O]
     * thinking process: if we can find an O, then we can use the three letter
     * string where O is the middle string to easily convert it to MOO.
     * if there is no such string, then -1
     * operations will be trim left with i-1 , trim right with length - i - 2
     * plus translate [M|O]O[M|O] to MOO.
     *
     * @param line
     */
    public static int solve(String line) {
        if (line.length() < 3) return -1; //nothing to do here, no solution
        int ret = Integer.MAX_VALUE;

        for (int i = 1; i < line.length() - 1; i++) {
            if (line.charAt(i) == 'O') {
                //solution found
                char beforeO = line.charAt(i - 1);
                char afterO = line.charAt(i + 1);
                int charsToTrimFromLeft = i - 1;
                int charsToTrimFromRight = line.length() - i - 2;
                ret = Math.min(ret,
                        (charsToTrimFromLeft + charsToTrimFromRight +
                                (beforeO == 'M' ? 0 : 1) + (afterO == 'O' ? 0 : 1)));
            }
        }
        return (ret !=Integer.MAX_VALUE) ? ret : -1;
    }
}
