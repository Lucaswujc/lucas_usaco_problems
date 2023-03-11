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

