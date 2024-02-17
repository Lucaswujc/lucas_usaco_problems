package USACO_bronzeclass_problems;
import java.util.*;

public class FoxGown2 {
    static int binarysearch(int[] array, int start, int end, int max_len) {
        int low = start;
        int high = end;
        
        int mid = 0;
        // Find the max index in the array where the corresponding vlaue is <= the max
        // length
        int ret = 0;
        if((max_len < array[start])){
            return -1;
        }
        do{
            mid = (low + high) / 2;
            if (max_len > array[mid]) {
                // the midpoint is less than the max value
                // reset low to mid
                low = mid;
            }
            if (max_len < array[mid]) {
                // the midpoint is greater than the max value
                // reset high to mid
                high = mid;

            }
            if (max_len == array[mid]) {
                // we found value is equal
                // keep on increasing mid by one until it is greater the max length
                while (array[mid] == max_len) {
                    if (mid == end) {
                        return end;
                    }
                    mid++;
                }
                mid = mid - 1;
                return mid;
            }
        } while(low + 1 < high); 
//        System.out.println(start + " " + end + " " + max_len + " " + ret);
        if(array[high] <= max_len){
            ret = high;
        }
        else{
            ret = low;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int S = scan.nextInt();
        ;
        int[] foxes = new int[N];
        for (int i = 0; i < N; i++) {
            foxes[i] = scan.nextInt();
        }
        quickSort(foxes, 0, N - 1);
        int numofnewfoxes = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (foxes[i] <= S) {
                numofnewfoxes = i + 1;
                break;
            }
        }
        long numofpairs = 0;
        int endsearch = numofnewfoxes - 1;
        int i = 0;
        while(i < endsearch){
            int len_i = foxes[i];
            int max_len = S - len_i;
            endsearch = binarysearch(foxes, i + 1, endsearch, max_len);
            int add = endsearch - i;
            i++;
            numofpairs = numofpairs + add;
        }
        System.out.println(numofpairs);
        scan.close();
    }

    static int partition(int array[], int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return (i + 1);
    }

    static void quickSort(int array[], int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
}