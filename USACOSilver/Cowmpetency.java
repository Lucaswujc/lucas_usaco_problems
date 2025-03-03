package USACOSilver;
import java.util.*;
public class Cowmpetency {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int q = scan.nextInt();
            int c = scan.nextInt();
            int[] comp = new int[n];
            boolean[] assigned = new boolean[n];
            int[] b = new int[n];
            for(int i = 0; i < n;i++){
                comp[i] = scan.nextInt();
                if(comp[i] == 0){
                    comp[i] = 1;
                }
            }  
            for(int i = 0;i < q; i++){
                int a = scan.nextInt();
                int h = scan.nextInt();
                b[a-1] = h-1;
            }
            for(int i = 0; i < n; i++){
                for(int j = i; j <= b[i]; j++){
                    if(b[j] != 0 && b[j] != b[i]){
                        System.out.println(-1);
                    }
                    b[j] = b[i];
                }
            }
            for (int i = 0; i < n; i++){
                if (b[i] == 0){
                    continue;
                }
                int mxBefore = Integer.MIN_VALUE;
                for (int j = 0; j <= i; j++) {
                    mxBefore = Math.max(mxBefore, comp[j]);
                }
                int mxAfter = Integer.MIN_VALUE;
                for (int j = 0; j < b[i]; j++) {
                    mxAfter = Math.max(mxAfter, comp[j]);
                }
        
                if (mx_after > mx_before){
                    for (int j = i; j >= 0; j--){
                        if (b[j] != 0 && b[j] < b[i]){
                            System.out.println("-1");;
                        }
                        if(assigned[j]){
                            continue;
                        }
                        c[j] = mx_after
                        break
                    }
                    else:
                        return print(-1)
                    mx_before = mx_after
                }
                if not assigned[B[i]]:
                    c[B[i]] = mx_before + 1
                # check to make sure B[i] > mx_before
                if c[B[i]] <= mx_before:
                    return print(-1)
            }
            t--;
        }
        scan.close();
    }
}