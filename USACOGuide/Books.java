package USACOGuide;
import java.util.*;
public class Books {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int t = scan.nextInt();
        int[] books = new int[n];
        for(int i =0; i < n; i++){
            books[i] = scan.nextInt();

        }
        int max = 0;
        int cur = 0;
        int left=  0;
        int right = 0;
        while (left <n && right < n){
            while(right < n){
                cur += books[right];
                right++;
                
                if(cur > t){
                    right--;
                    cur -= books[right];
                    break;
                }
            }
            max = Math.max(max, right-left);
            cur-=books[left];
            left++;
        }
        System.out.println(max);
        scan.close();
    }
}