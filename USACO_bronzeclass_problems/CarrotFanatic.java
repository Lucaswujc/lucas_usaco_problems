package USACO_bronzeclass_problems;
import java.util.*;
public class CarrotFanatic {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] a = new int[n+2];
        for(int i =1 ; i <= n ; i++){
            a[i] = scan.nextInt();
        }
        Arrays.sort(a);
        int[] sizeL = new int[n+1];
        int left = 1;
        int right = 1;
        while(left <= n){
            while(right <= n && a[right] - a[left] <=k){
                right++;
            }
            sizeL[left] = right - left;
            left++;
        }
        int[] bestL = new int[n+2];
        bestL[0] = 0;
        for(int i = 1; i <= n ;i++){
            bestL[i] = Math.max(bestL[i-1], sizeL[i]);
        }
        int[] sizeR = new int[n+1];
        left= n;
        right = n;
        while(right >= 1){
            while(left >= 1 && a[right] - a[left] <=k){
                left--;
            }
            sizeR[right] = right - left;
            right--;
        }
        int[] bestR = new int[n+2];
        bestR[n+1] = 0;
        for(int i = n; i >= 1; i--){
            bestR[i] = Math.max(bestR[i+1], sizeR[i]);
        }
        int result = 0;
        for(int i  = 1; i <= n+1; i++){
            result = Math.max(result, bestL[i-1] + bestR[i]);
        }
        System.out.println(result);
        scan.close();
    }
}
