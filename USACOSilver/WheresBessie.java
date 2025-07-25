package USACOSilver;
import java.io.*;
import java.util.*;
public class WheresBessie {
    static int n;
    static char[][] image;
    static boolean[][] visited = new boolean[20][20];
    static int iMin, jMin, iMax, jMax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("where.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        image = new char[n][n];
        for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < n; j++) { image[i][j] = row.charAt(j); }
		}
		

		List<PCL> pcls = new ArrayList<>();

		for (int i1 = 0; i1 < n; i1++) {
			for (int j1 = 0; j1 < n; j1++) {
				for (int i2 = 0; i2 < n; i2++) {
					for (int j2 = 0; j2 < n; j2++) {
						if (check(i1, j1, i2, j2)) {
							pcls.add(new PCL(i1, j1, i2, j2));
						}
					}
				}
			}
		}

		int pclCount = 0;
		for (int i = 0; i < pcls.size(); i++) {
			boolean work = true;
			for (int j = 0; j < pcls.size(); j++) {
				if (i == j) { 
					continue; 
				}
				if (pcls.get(i).isInside(pcls.get(j))) {
					work = false;
					break;
				}
			}
			pclCount += work ? 1 : 0;
		}
		out.println(pclCount);
        br.close();
        out.close();
    }
    static void floodfill(int i, int j, char color) {
		if (i < iMin || j < jMin || i > iMax || j > jMax || visited[i][j] ||image[i][j] != color) {
			return;
		}

		visited[i][j] = true;

		floodfill(i + 1, j, color);
		floodfill(i - 1, j, color);
		floodfill(i, j + 1, color);
		floodfill(i, j - 1, color);
	}
	static boolean check(int i1, int j1, int i2, int j2) {
		int[] regions = new int[26];
		iMin = i1;
		iMax = i2;
		jMin = j1;
		jMax = j2;
		for (int i = iMin; i <= iMax; i++) {
			for (int j = jMin; j <= jMax; j++) {
				if (!visited[i][j]) {
					char c = image[i][j];
					regions[c - 'A']++;
					floodfill(i, j, c);
				}
			}
		}

		
		visited = new boolean[20][20];
		int count = 0;
		boolean oneRegion = false;
		boolean moreRegions = false;
		for (int i = 0; i < regions.length; i++) {
			if (regions[i] != 0) { 
				count++; 
			}
			if (regions[i] == 1) { 
				oneRegion = true; 
			}
			if (regions[i] > 1) { 
				moreRegions = true; 
			}
		}
		if(count == 2 && oneRegion && moreRegions){
			return true;
		}
		else{
			return false;
		}
	}
	static class PCL {
		public int i1, j1;
		public int i2, j2;
		public PCL(int i1, int j1, int i2, int j2) {
			this.i1 = i1;
			this.j1 = j1;
			this.i2 = i2;
			this.j2 = j2;
		}

		public boolean isInside(PCL p) {
			return (i1 >= p.i1 && i2 <= p.i2 && j1 >= p.j1 && j2 <= p.j2);
		}
	}
}