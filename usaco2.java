import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class usaco2 {
  public static void main (String [] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usaco2.out")));
    StringTokenizer st = new StringTokenizer(scan.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] cows = new int[n][3];
    int[][] acs = new int[m][4];
    for(int i = 0; i < n; i++){
      st = new StringTokenizer(scan.readLine());
      cows[i][0] = Integer.parseInt(st.nextToken());
      cows[i][1] = Integer.parseInt(st.nextToken());
      cows[i][2] = Integer.parseInt(st.nextToken());
    }
    for(int i = 0; i < m; i++){
      st = new StringTokenizer(scan.readLine());
      acs[i][0] = Integer.parseInt(st.nextToken());
      acs[i][1] = Integer.parseInt(st.nextToken());
      acs[i][2] = Integer.parseInt(st.nextToken());
      acs[i][3] = Integer.parseInt(st.nextToken());
    }
    int answer = m*1000;
    for (int mask = 0; mask < 1 << m; mask++) {
      int[] numberLine = new int[101];
      int totalCost = 0;
      for (int j = 0; j < m; j++) {
          if ((mask & (1 << j)) != 0) {
              totalCost += acs[j][3];
              for (int x = acs[j][0]; x <= acs[j][1]; x++) {
                  numberLine[x] += acs[j][2];
              }
          }
      }
      boolean works = true;
      for (int j = 0; j < n; j++) {
          for (int x = cows[j][0]; x <= cows[j][1]; x++) {
              if (numberLine[x] < cows[j][2]) {
                  works = false;
              }
          }
      }
      if (works) {
          answer = Math.min(answer, totalCost);
      }
    }
    System.out.println(answer);
    out.close();                                  
  }
}

