package USACOSilver;

import java.io.*;
import java.util.*;
public class WormholeSort {
    static List<int[]>[]wormholes;
	static boolean[] visited;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		PrintWriter pw = new PrintWriter("wormsort.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] cows = new int[n];
        wormholes = new ArrayList[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            cows[i] = Integer.parseInt(st.nextToken());
        }
        int maxWidth = 0;
        for (int i = 0; i < n; i++) {
             wormholes[i] = new ArrayList<>(); 
            }
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			int width = Integer.parseInt(st.nextToken());;
			wormholes[c1].add(new int[] {c2, width});
			wormholes[c2].add(new int[] {c1, width});
			maxWidth = Math.max(maxWidth, width);
		}

		int lo = 0;
		int hi = maxWidth + 1;
		int valid = -1;
		int[] component = new int[n];
		while (lo <= hi) {
			int mid = (lo + hi) / 2;

			Arrays.fill(component, -1);
			int currComp = 0;
			for (int c = 0; c < n; c++) {
				if (component[c] != -1) { continue; }
				List<Integer> frontier =
				    new ArrayList<>(Collections.singletonList(c));
				while (!frontier.isEmpty()) {
					int curr = frontier.remove(frontier.size() - 1);
					component[curr] = currComp;
					for (int[] neighbors : wormholes[curr]) {
						if (component[neighbors[0]] == -1 && neighbors[1] >= mid) {
							frontier.add(neighbors[0]);
						}
					}
				}
				currComp++;
			}

			boolean sortable = true;
			for (int c = 0; c < n; c++) {
				if (component[c] != component[cows[c]]) {
					sortable = false;
					break;
				}
			}

			if (sortable) {
				valid = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
        pw.println(valid == maxWidth+1 ? -w : valid);
        br.close();
        pw.close();
    }
}