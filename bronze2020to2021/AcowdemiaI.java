package bronze2020to2021;

import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
public class AcowdemiaI {
 
    static int hIndex(Integer[] papers) {
        int h = papers.length;
        while (h > 0 && papers[h - 1] < h) {
            h--;
        }
        return h;
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int l = scan.nextInt();
        Integer[] papers = new Integer[n];
        
        for (int j = 0; j < n; j++) {
            papers[j] = scan.nextInt();
        }
        Arrays.sort(papers, Comparator.reverseOrder());
        int h = hIndex(papers);
        if (h != n) {
            for (int j = h; j >= 0 && j > h - l; j--) {
                papers[j]++;
            }
        }
        Arrays.sort(papers, Comparator.reverseOrder());
        System.out.println(hIndex(papers));
        scan.close();
    }
}