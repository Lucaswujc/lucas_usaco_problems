import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.util.Arrays.*;

class Part {
  int f;
  int m;
  int id;
  public Part(int f, int m, int id) {
    this.f = f;
    this.m = m;
    this.id = id;
  }
}

public class debug {
  public static void main(String[] args) throws IOException {
    // BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    // StringTokenizer st = new StringTokenizer(scan.readLine());
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int[] x = new int[n];
    int[] y = new int[n];
    int[] sortedx = new int[n];
    int[] sortedy = new int[n];
    for(int i = 0; i <n ;i++){
        x[i] = scan.nextInt();
        y[i] = scan.nextInt();
        sortedx[i] = x[i];
        sortedy[i] = y[i];
    }
    Arrays.sort(sortedx);
    Arrays.sort(sortedy);
    int res = 2147483647;
    for(int i =0; i <= Math.min(n-1,3); i++){
        for(int j = Math.max(0, n-4); j >= n-1; j--){
            for(int l = 0; l <= Math.min(n,3); l++){
                for(int k = Math.max(0, n-4); k>=n-1; k--){
                    int cnt = 0;
                    for(int h =0; h <= n -1; h++){
                        int x1 = x[h];
                        int y1 = y[h];
                        if ((x1 >= sortedx[i] && x1 <= sortedx[j]) && (y1 >= sortedy[l] && y1 <= sortedy[k])){
                            cnt++;
                        }
                    }
                    if(cnt>=n-3){
                        res= Math.min(res, (sortedx[i] -sortedx[j])* (sortedy[l] - sortedy[k]));
                    }
                }
            }
        }
    }
    System.out.println(res);
    /*
     * PROBLEM 2
     * int t = Integer.parseInt(st.nextToken());
     * while(t>0){
     * st = new StringTokenizer(scan.readLine());
     * int n = Integer.parseInt(st.nextToken());
     * int k = Integer.parseInt(st.nextToken());
     * st = new StringTokenizer(scan.readLine());
     * String s = st.nextToken();
     * char[] cows = new char[n];
     * char[] ans = new char[n];
     * for(int i = 0; i < n;i++){
     * cows[i] = s.charAt(i);
     * ans[i] = '.';
     * }
     * 
     * int lastg = -k -1;
     * int lasth = -k-1;
     * int anspatches = 0;
     * for(int i = k; i < n; i++){
     * if(cows[i -k] == 'G'){
     * if((i - k) - lastg > k) {
     * anspatches++;
     * ans[i] = 'G';
     * lastg = i;
     * }
     * }
     * else {
     * if ((i - k) - lasth > k) {
     * anspatches++;
     * ans[i] = 'H';
     * lasth = i;
     * }
     * }
     * }
     * Boolean extra = false;
     * for(int i = 0; i < n; i++){
     * if(cows[i] == 'G' && i -lastg > k){
     * extra = true;
     * }
     * }
     * if(extra){
     * for(int j = n-1; j >= 0; j--){
     * if(ans[j] == '.'){
     * anspatches++;
     * ans[j] = 'G';
     * break;
     * }
     * }
     * }
     * extra = false;
     * for(int i = 0; i <n; i++){
     * if(cows[i] == 'H' && i -lasth > k){
     * extra = true;
     * }
     * }
     * if(extra){
     * for(int j = n-1; j >= 0; j--){
     * if(ans[j] == '.'){
     * anspatches++;
     * ans[j] = 'H';
     * break;
     * }
     * }
     * }
     * System.out.println(anspatches);
     * for(int i =0; i < n; i++){
     * System.out.print(ans[i]);
     * }
     * System.out.println();
     * t--;
     * }
     */
  }
}
