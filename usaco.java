import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class usaco {
  public static void main (String [] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usaco.out")));
    StringTokenizer st = new StringTokenizer(scan.readLine());
    long n = Long.parseLong(st.nextToken());
    long t = Long.parseLong(st.nextToken());
    long balecount = 0;
    long eat = 0;
    long lastd = 0;
    for(int i = 0; i < n; i++){
      long b = 1;
      long d = 1;
      String line = scan.readLine();
      d = Long.parseLong(line.split(" ")[0]);
      b = Long.parseLong(line.split(" ")[1]);
      if(i == 0){
        lastd = d;
        balecount += b;
        continue;
      }
      if(balecount > (d - lastd)){
        eat += (d-lastd);
        balecount -= (d-lastd);
      }
      else{
        eat += balecount;
        balecount -= balecount;
      }
      balecount += b;
      lastd = d;
    }

    /*for(long i = 1; i <= t; i++){
      if(delivcount < n){
        if(i == d[(int) delivcount]){
          balecount += b[(int) delivcount];
          delivcount++;
        }
      }
      if(balecount>=1){
        balecount--;
        eat++;
      }
    }*/
    if(t - lastd + 1 <= balecount){
      eat += (t-lastd+1);
    }
    else{
      eat += balecount;
    }
    System.out.println(eat);
    //out.close();                            
  }
}

import java.io.*;
import java.util.*;


public class usaco {
  public static void main (String [] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usaco.out")));
    StringTokenizer st = new StringTokenizer(scan.readLine());
    int n = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(scan.readLine());
    String s = st.nextToken();
    int eg = 0;
    int eh = 0;
    int lg = 0;
    int lh = 0;
    int[] arr= new int[n];
    int ans = 0;
    st = new StringTokenizer(scan.readLine());
    for(int i = 0; i < n; i++){
      arr[i] = Integer.parseInt(st.nextToken());
      arr[i]--;
    }
    for(int i =0; i < n; i++){
      if(s.charAt(i) =='H'){
        eh = i;
        break;
      }
    }
    for(int i =0; i < n; i++){
      if(s.charAt(i) =='G'){
        eg = i;
        break;
      }
    }
    for(int i = n -1; i >= 1; i--){
      if(s.charAt(i) =='H'){
        lh = i;
        break;
      }
    }
    for(int i = n -1; i >= 1; i--){
      if(s.charAt(i) =='G'){
        lg = i;
        break;
      }
    }
    if(arr[eg]>=lg){
      for(int i =0; i < eg; i++){
        if( i == eh){
          continue;
        }
        if(s.charAt(i) =='H' && arr[i] >= eg){
          ans++;
        }
      }
    }

    if(arr[eh]>=lh){
      for(int i =0; i < eh; i++){
        if(i == eg){
          continue;
        }
        if(s.charAt(i) =='G' && arr[i] >= eh){
          ans++;
        }
      }
    }
    if ((arr[eg] >= lg || (eg <= eh && arr[eg] >= eh)) && (arr[eh] >= lh || (eh <= eg && arr[eh] >= eg))){
        ans++;
    }
    System.out.println(ans);
    out.close();                                  
  }
}

