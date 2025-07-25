package USACOGuide;
import java.util.*;
public class Apartments {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();
        int[] desire = new int[n];
        int[] apartments = new int[m];
        for(int i  =0; i < n; i++){
            desire[i] = scan.nextInt();
        }
        for(int i = 0; i < m; i++){
            apartments[i] = scan.nextInt();
        }
        Arrays.sort(desire);
        Arrays.sort(apartments);
        int ans = 0;
        int i = 0;
        int j = 0;
        while(i < n && j < m){
            if (Math.abs(desire[i] - apartments[j]) <= k) {
				i++;
				j++;
				ans++;
			}
            else if(desire[i] > apartments[j]){
                j++;
            }
            else{
                i++;
            }
        }
        System.out.println(ans);
        scan.close();
    }
}