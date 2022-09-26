import java.util.Arrays;
import java.util.*;
public class debug {
    static int endsearch = 0;
    static int binarysort(int[] array, int n, int num){
    int low = 0;
    int high = n;
    int mid = 0;
    while(mid < num){
        mid = (low + high)/2;
        if(mid > num){
            low = low -1;
            break;
        }
 
        
    }
    endsearch = low;
    return low;
    
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int S = scan.nextInt();;
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
        int numofpairs = 0;
        endsearch = numofnewfoxes-1;
        for (int i = 0; i < numofnewfoxes; i++){
            int len_i = foxes[i];
            int max_len = S- len_i;
            int x = binarysort(foxes, endsearch, max_len);
            int add = x-i;
            if(add <= 0){
                add = 0;
            }
            numofpairs = numofpairs + add;
        }
        System.out.println(numofpairs);
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