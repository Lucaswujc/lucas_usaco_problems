<<<<<<< HEAD
package sortingalgorithmns;

import java.util.*;
import java.lang.Math.*;
import java.util.Arrays.*;
=======
package SortingAlgorithmns;

import java.util.*;
>>>>>>> 1df1e03d395f6f8458340dfc8c2b6b9ab079791c
public class binarySearch {
    static int upperBound(int x, int[] array){
        int l = 0;
        int r = array.length;
        while(l<r){
            int mid = (r+l)/2;
            if(x >= array[mid]){
                l = mid +1;
            }
            else{
                r = mid;
            }
        }
        return l;
    }
    static int lowerBound(int x, int[] array){
        int l = 0;
        int r = array.length;
        while(l<r){
            int mid = (r+l)/2;
            if(x <= array[mid]){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        for(int i =0; i < n ;i++){
            array[i] = scan.nextInt();
        }
        Arrays.sort(array);
        int lower = scan.nextInt();
        int upper = scan.nextInt();
        int upperBound = upperBound(upper, array);
        int lowerBound = lowerBound(lower, array);
        System.out.println(upperBound-lowerBound);
<<<<<<< HEAD
=======
        scan.close();
>>>>>>> 1df1e03d395f6f8458340dfc8c2b6b9ab079791c
    }

}