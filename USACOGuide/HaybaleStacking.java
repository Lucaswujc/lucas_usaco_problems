package USACOGuide;
import java.util.*;
class HaybaleStacking {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] differences = new int[n+1];
        for(int i = 0; i < k; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            differences[a]++;
            differences[b+1]--;
        }
        int[] original = new int[n+1];
        int prefix= 0 ;
        for(int i = 0; i < n; i++){
            prefix += differences[i];
            original[i] = prefix;
        }
        Arrays.sort(original);
        System.out.println(original[n/2]);
        scan.close();
    }
}