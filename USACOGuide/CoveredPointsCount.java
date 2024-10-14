package USACOGuide;

import java.io.*;
import java.util.*;

public class CoveredPointsCount {
	public static void main(String[] args) throws IOException {
		BufferedReader br =
		    new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Pair[] segments = new Pair[n];
		SortedSet<Long> points = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			segments[i] = new Pair(Long.parseLong(st.nextToken()),
			                       Long.parseLong(st.nextToken()));
			points.add(segments[i].first);
			points.add(segments[i].second + 1);
		}
		br.close();

		int cur = 0;
		Map<Long, Integer> compressed = new HashMap<>();
		List<Long> coords = new ArrayList<>();
		for (long c : points) {
			compressed.put(c, cur);
			
			coords.add(c);
			cur++;
		}
		
		int[] freq = new int[2 * n];
		for (int i = 0; i < n; i++) {
			
			freq[compressed.get(segments[i].first)]++;
			freq[compressed.get(segments[i].second + 1)]--;
		}
		
		for (int i = 1; i < 2 * n; i++) { 
			freq[i] += freq[i - 1]; 
		}
		long[] coveredNum = new long[n + 1];
		for (int i = 1; i < coords.size(); i++) {
			coveredNum[freq[i - 1]] += coords.get(i) - coords.get(i - 1);
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(coveredNum[i]);
			System.out.print(" ");
		}
	}

	private static class Pair {
		public long first, second;

		public Pair(long f, long s) {
			first = f;
			second = s;
		}
	}
}