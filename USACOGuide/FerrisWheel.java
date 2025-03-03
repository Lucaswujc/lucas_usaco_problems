package USACOGuide;
import java.util.*;
public class FerrisWheel {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int w = scan.nextInt();
        scan.nextLine();
        String[] weightsStr = scan.nextLine().split(" ");
		Integer[] children = new Integer[n];
        for(int i = 0; i < n; i++){
            children[i] = Integer.parseInt(weightsStr[i]);
        }
        Arrays.sort(children);
        int i = 0;
        int j = n-1;
        int ans = 0;
        while(i <= j){
            ans++;
            if(i == j){
                break;
            }
            if(children[j] + children[i] > w){
                j--;
            }
            else{
                j--;
                i++;
            }
        }
        System.out.println(ans);
        scan.close();
    }
}