package bronze2023to2024;
import java.io.*;
import java.util.*;
import javax.naming.TimeLimitExceededException;
public class Cannonball {
    public static void main(String[] args) throws IOException, TimeLimitExceededException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int s = scan.nextInt();
        int[] q = new int[n];
        int[] v = new int[n];
        int brokentargets = 0;
        Boolean[] broken = new Boolean[n];
        Arrays.fill(broken, false);
        for(int i = 0; i < n; i++){
            q[i] = scan.nextInt();
            v[i] = scan.nextInt();
        }
        int pos = s;
        int power = 1;
        Boolean right = true;
        int count = 0;
        while((pos > 0 && pos <= n) ){
            if(q[pos-1] == 1 && !broken[pos-1] && power >= v[pos-1]){
            broken[pos-1] = true;
            brokentargets++;
            }
            if (q[pos-1] == 0) {
                power += v[pos-1];
                right = right == true ? false : true;
            } 
            if(right){
                pos+=power;
            }
            else{
                pos-=power;
            }
            if(count > 300000){
                break;
            }
            count++;
        }
        out.println(brokentargets);
        out.close();
        scan.close();
    }
}
