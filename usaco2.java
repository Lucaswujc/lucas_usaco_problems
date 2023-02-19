import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class usaco2 {
  static PrintStream out = System.out;
  static Boolean checkWhetherToPrint(int startx, int starty, char[][] finalPicture, char[][] stamp, int k) {
    int stampx = 0;
    int stampy = 0;
    for (int i = startx; i < startx + k; i++) {
      stampy = 0;
      for (int j = starty; j < starty + k; j++) {
        if (stamp[stampx][stampy] == '.') {
          stampy++;
          continue;
        }
        if (stamp[stampx][stampy] != finalPicture[i][j]) {
          return false;
        }
        stampy++;
      }
      stampx++;
    }
    return true;
  }

  static char[][] stampOnCurrentCanvas(char[][] currentCanvas, char[][] stamp, int stampx, int stampy, int k) {
    int x = 0;
    int y = 0;
    for (int i = stampx; i < stampx + k; i++) {
      y = 0;
      for (int j = stampy; j < stampy + k; j++) {
        if (stamp[x][y] == '*') {
          currentCanvas[i][j] = '*';
        }
        y++;
      }
      x++;
    }
    return currentCanvas;
  }

  static Boolean compareCurrentCanvases(char[][] finalPicture, char[][] currentcurrentCanvas, int n) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!(finalPicture[i][j] == currentcurrentCanvas[i][j])) {
          return false;
        }
      }
    }
    return true;
  }

  static void testRotateStamp() {
    char[][] input = {
        { '1', '1' },
        { '0', '1' }
    };
    char[][] expected = {
        { '1', '1' },
        { '0', '0' }
    };
    char[][] result = rotateStamp(input, input.length);
    boolean comparersult = compareTwoCanvases(expected, result);
    assert comparersult==true;
    out.println("rotate stamp succeeded");

  }

  static boolean compareTwoCanvases(char[][] a, char[][] b) {
    if (a.length != b.length) {
      return false;
    }
    for (int i = 0; i < a.length; i++) {
      if (a[i].length != b[i].length)
        return false;
      else {
        for (int j = 0; j < a[i].length; j++) {
          if (a[i][j] != b[i][j])
            return false;
        }
      }
    }
    return true;
  }

  static char[][] rotateStamp(char[][] matrix, int M) {
    if (M != 1) {
      for (int i = 0; i < M; i++) {
        for (int j = i; j < M; j++) {
          char temp = matrix[i][j];
          matrix[i][j] = matrix[j][i];
          matrix[j][i] = temp;
        }
      }
      for (int i = 0; i < M; i++) {
        int low = 0;
        int high = M - 1;
        while (low < high) {
          char temp = matrix[low][i];
          matrix[low][i] = matrix[high][i];
          matrix[high][i] = temp;
          low++;
          high--;
        }
      }
    }
    return matrix;
  }

  public static void main(String[] args) throws IOException {
    testRotateStamp();
    // BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    // PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usaco2.out")));
    // StringTokenizer st = new StringTokenizer(scan.readLine());
    // int t = Integer.parseInt(st.nextToken());
    // while (t > 0) {
    //   st = new StringTokenizer(scan.readLine());
    //   st = new StringTokenizer(scan.readLine());
    //   int n = Integer.parseInt(st.nextToken());
    //   char[][] finalPicture = new char[n][n];
    //   char[][] currentCanvas = new char[n][n];
    //   for (int i = 0; i < n; i++) {
    //     st = new StringTokenizer(scan.readLine());
    //     String s = st.nextToken();
    //     for (int j = 0; j < n; j++) {
    //       finalPicture[i][j] = s.charAt(j);
    //     }
    //   }
    //   for (int i = 0; i < n; i++) {
    //     for (int j = 0; j < n; j++) {
    //       currentCanvas[i][j] = '.';
    //     }
    //   }
    //   st = new StringTokenizer(scan.readLine());
    //   int k = Integer.parseInt(st.nextToken());
    //   char[][] stamp = new char[k][k];
    //   for (int i = 0; i < k; i++) {
    //     st = new StringTokenizer(scan.readLine());
    //     String s = st.nextToken();
    //     for (int j = 0; j < k; j++) {
    //       stamp[i][j] = s.charAt(j);
    //     }
    //   }
    //   for (int rotate = 0; rotate < 4; rotate++) {
    //     for (int i = 0; i < n - k + 1; i++) {
    //       for (int j = 0; j < n - k + 1; j++) {
    //         if (checkWhetherToPrint(i, j, finalPicture, stamp, k)) {
    //           stampOnCurrentCanvas(currentCanvas, stamp, i, j, k);
    //         }
    //       }
    //     }
    //     stamp = rotateStamp(stamp, k);
    //   }
    //   if (compareCurrentCanvases(finalPicture, currentCanvas, n)) {
    //     System.out.println("YES");
    //   } else {
    //     System.out.println("NO");
    //   }
    //   t--;
    // }
    // System.out.println();
    // out.close();
  }

  public static void daddySolution() {
    /**
     * currentCanvas size is N x N
     * stamp size is M x M
     * final picture char finalPic[N][N]
     * stamp shape char stamp[M][M]
     * currentcurrentCanvas char currentCanvas[N][N] -- initialized as empty
     * 
     * Step 1 we will repeat putting stamp on the currentCanvas at a location i,j
     * Step 1.1 check stamp at i,j whether you can print
     * Step 1.2 if you can print, print on the currentcurrentCanvas, else skip
     * Step 2 rotate stamp by 90 degree clockwise, get a new stamp
     * repeat everything in Step 1
     * Step 3 rotate stamp by 90 degree clockwise,
     * repeat eveerything in step1
     * Step 4 rotate stamp by 90 degree clockwise,
     * repeat eveerything in step1
     * 
     * Step 5 compare currentcurrentCanvas with final picture. if they identical
     * then yse, else no
     * 
     * based on this knoledge, we try to modulize the code in several functions.
     * stamp[M][M] rotateStamp(stamp[M][M])
     * boolean checkWhetherToPrint(int i, int j , finalPicture,stamp)
     * void stampOncurrentCanvas(stamp, currentcurrentCanvas)
     * boolean comparecurrentCanvases(finalPicture, currentcurrentCanvas)
     * 
     * Main program
     * for rotationIteration = 0 to 3 {
     * for(i,j in 0 .. N-M 0..N-M){
     * if(checkWhetherToprint()){
     * stampOncurrentCanvas
     * }
     * }
     * rotateStamp
     * }
     * comparecurrentCanvases
     * 
     */
  }
}
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

