package USACOGuide;
import java.util.*;
public class StudyingAlgorithms {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int t = scan.nextInt();
        int[] alg = new int[n];
        for(int i = 0 ;i < n; i++){
            alg[i] = scan.nextInt();
        }
        int total = 0;
        int ans = 0;
        Arrays.sort(alg);
        for(int i = 0; i < n; i++){
            if(total >= t){
                break;
            }
            total += alg[i];
            ans++;
        }
        if(total > t){
            ans--;
        }
        System.out.println(ans);
        scan.close();
    }
}