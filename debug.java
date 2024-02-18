/*
ID: Lucas WU [lucaswu2]
LANG: JAVA
TASK: friday
*/
import java.io.*;

public class debug {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    // BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("debug.out")));
    //StringTokenizer st = new StringTokenizer(scan.readLine());
    for(int i = 1; i <= 10000; i++){
      System.out.print(i + 93324 + " ");
    }
    /*int t = Integer.parseInt(st.nextToken());
    while(t>0){
      st = new StringTokenizer(scan.readLine());
      st = new StringTokenizer(scan.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      Boolean ok =true;
      String[][] cases = new String[m][2];
      for(int i = 0; i < m; i++){
        st = new StringTokenizer(scan.readLine());
        cases[i][0] = st.nextToken();
        cases[i][1] = st.nextToken();
      }
      for(int i =0; i <n; i++){
        
      }
      if(ok){
        System.out.println("OK");
      }else{
        System.out.println("LIE");
      }
      t--;
    }
    */
  
    
    
    
    /* PROBLEM 2
    int t = Integer.parseInt(st.nextToken());
    while(t>0){
      st = new StringTokenizer(scan.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(scan.readLine());
      String s = st.nextToken();
      char[] cows = new char[n];
      char[] ans = new char[n];
      for(int i = 0; i < n;i++){
        cows[i] = s.charAt(i);
        ans[i] = '.';
      }
      
      int lastg = -k -1;
      int lasth = -k-1;
      int anspatches = 0;
      for(int i = k; i < n; i++){
        if(cows[i -k] == 'G'){
          if((i - k) - lastg > k) {
            anspatches++;
            ans[i] = 'G';
            lastg = i;
            }
        } 
        else {
          if ((i - k) - lasth > k) {
              anspatches++;
              ans[i] = 'H';
              lasth = i;
          }
        }
      }
      Boolean extra = false;
      for(int i = 0; i < n; i++){
        if(cows[i] == 'G' && i -lastg > k){
            extra = true;
        }
      }
      if(extra){
        for(int j = n-1; j >= 0; j--){
          if(ans[j] == '.'){
            anspatches++;
            ans[j] = 'G';
            break;
          }
        }
      }
      extra = false;
      for(int i = 0; i <n; i++){
        if(cows[i] == 'H' && i -lasth > k){
            extra = true;
        }
      }
      if(extra){
      for(int j = n-1; j >= 0; j--){
        if(ans[j] == '.'){
            anspatches++;
            ans[j] = 'H';
            break;
          }
        }
      }
      System.out.println(anspatches);
      for(int i =0; i < n; i++){
        System.out.print(ans[i]);
      }
      System.out.println();
      t--;
    }   
    */  
    out.close();                         
  }
}


