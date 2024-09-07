import java.io.*;
import java.util.*;
public class Bakery {
    static class Pair implements Comparable<Pair> {
		long first, second;

		public Pair(long x, long y) {
			first = x;
			second = y;
		}

		public int compareTo(Pair x) {
			if (this.first == x.first) return (int)(this.second - x.second);
			return (int)(this.first - x.first);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
		PrintWriter pw = new PrintWriter("socdist.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        while(t > 0){
            int n = Integer.parseInt(st.nextToken());
            t--;
        }
        pw.println();
        br.close();
        pw.close();
    }
}