package USACOGuide;
import java.io.*;
import java.util.*;

public class HighCardWins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[] elsieOwns = new boolean[2 * n];
        for (int i = 0; i < n; i++) {
            elsieOwns[Integer.parseInt(br.readLine()) - 1] = true;
        }
        List<Integer> bessie = new ArrayList<Integer>();
        List<Integer> elsie = new ArrayList<Integer>();
        int ans = 0;
        for (int i = 0; i < n * 2; i++) {
            if (elsieOwns[i]) {
                elsie.add(i + 1);
            } else {
                bessie.add(i + 1);
            }
        }
        int bidx = 0;
        int eidx = 0;
        while (bidx < n && eidx < n) {
            if (bessie.get(bidx) > elsie.get(eidx)) {
                ans++;
                bidx++;
                eidx++;
            } else {
                bidx++;
            }
        }
        out.println(ans);
        br.close();
        out.close();
    }
}