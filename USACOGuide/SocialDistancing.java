package USACOGuide;
import java.io.*;
import java.util.*;
public class SocialDistancing {
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Pair[] intervals = new Pair[m];
        for(int  i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            intervals[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(intervals);
        long low = 0;
        long high = intervals[m-1].second-intervals[0].first+1;
        while(low < high){
            long mid = low + (high-low+1)/2;
            int count = 1;
            int intervalCount = 0;
			long current = intervals[0].first;
            while(current + mid <= intervals[m-1].second){
                while (current + mid > intervals[intervalCount].second) {
					intervalCount++;
				}
				current = Math.max(intervals[intervalCount].first, current + mid);
				count++;
				if (count == n) {
                    break;
                }
            }
            if (count >= n) {
                low = mid;
            }
			else {
                high = mid - 1;
            }
        }
        pw.println(low);
        br.close();
        pw.close();
    }
}