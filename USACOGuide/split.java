package USACOGuide;
import java.util.*;
import java.io.*;
class Pair implements Comparable<Pair> {
        public long first, second;
        public Pair(long x, long y) {
            first = x;
            second = y;
        }
        public int compareTo(Pair o) {
            if (o.first != first) { 
                return (int)(first - o.first); 
            }
            return (int)(second - o.second);
        }
    }
public class split {
    
    public static Pair update(Pair a, long b) {
		return new Pair(Math.min(a.first, b), Math.max(a.second, b));
	}
    
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("split.in"));
        PrintWriter pw = new PrintWriter("split.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Pair[] cows = new Pair[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			cows[i] = new Pair(x, y);
		}
		long ans = 0;
		for (int t = 0; t < 2; t++) {
			Arrays.sort(cows);
			Pair[] pref = new Pair[n];
			Pair[] suf = new Pair[n];
		
			pref[0] = new Pair(cows[0].second, cows[0].second);
			for (int i = 1; i < n; i++) {
				pref[i] = update(pref[i - 1], cows[i].second);
			}

			suf[n - 1] = new Pair(cows[n - 1].second, cows[n - 1].second);
			for (int i = n - 2; i >= 0; i--) {
				suf[i] = update(suf[i + 1], cows[i].second);
			}
			long area = (cows[n - 1].first - cows[0].first) * (pref[n - 1].second - pref[n - 1].first);

			long best = Long.MAX_VALUE;
			for (int i = 0; i < n - 1; i++) {
				if (cows[i].first != cows[i + 1].first) {
					long first_rect = (cows[i].first - cows[0].first) * (pref[i].second - pref[i].first);
					long second_rect = (cows[n - 1].first - cows[i + 1].first) * (suf[i + 1].second - suf[i + 1].first);
					best = Math.min(best, first_rect + second_rect);
				}
			}

			ans = Math.max(ans, area - best);
			for (int i = 0; i < n; i++) {
				long temp = cows[i].first;
				cows[i].first = cows[i].second;
				cows[i].second = temp;
			}
		}
		pw.println(ans);
		pw.close();
        br.close();
    }
}