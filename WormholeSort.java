import java.io.*;
import java.util.*;

public class WormholeSort {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);

		int cowNum = scan.nextInt();
		int wormholeNum = scan.nextInt();

		int[] cows = new int[cowNum];
		for (int c = 0; c < cowNum; c++) { 
            cows[c] = scan.nextInt() - 1; 
        }

		int maxWidth = 0;
		List<int[]> wormholes = new ArrayList<>();
		for (int w = 0; w < wormholeNum; w++) {
			wormholes.add(new int[] {scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt()});
			maxWidth = Math.max(maxWidth, wormholes.get(w)[2]);
		}

		wormholes.sort(Comparator.comparingInt(wh -> wh[2]));

		int wormholeAt = wormholeNum;
		DSU dsu = new DSU(cowNum);
		for (int i = 0; i < cowNum; i++) {
			while (dsu.getTop(i) != dsu.getTop(cows[i])) {
				wormholeAt--;
				dsu.link(wormholes.get(wormholeAt)[0], wormholes.get(wormholeAt)[1]);
			}
		}

		System.out.println(wormholeAt == wormholeNum ? -1 : wormholes.get(wormholeAt)[2]);
		scan.close();
	}
    static class DSU {
        private final int[] parent;
        private final int[] size;
        public DSU(int size) {
            parent = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int getTop(int n) {
            return parent[n] == n ? n : (parent[n] = getTop(parent[n]));
        }

        public boolean link(int e1, int e2) {
            e1 = getTop(e1);
            e2 = getTop(e2);
            if (e1 == e2) { return false; }
            if (size[e2] > size[e1]) { return link(e2, e1); }
            parent[e2] = e1;
            size[e1] += size[e2];
            return true;
        }
    }
}
