package usaco_silver_basic;

import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int array[] = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = cin.nextInt();
        }
        int x = cin.nextInt();

        Arrays.sort(array);
        if (Arrays.binarySearch(array, x) > 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

