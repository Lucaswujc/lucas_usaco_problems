import java.io.*;
import java.util.*;
public class GreatRevegetation {
    public static int[] type;
	public static boolean impossible;
	public static ArrayList<Integer>[] same;
	public static ArrayList<Integer>[] diff;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
		PrintWriter out = new PrintWriter("revegetate.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		type = new int[n + 1];
		same = new ArrayList[n + 1];
		diff = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			same[i] = new ArrayList<>();
			diff[i] = new ArrayList<>();
		}
		int connected = 0;  

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (type == 'S') {
				same[a].add(b);
				same[b].add(a);
			}
			if (type == 'D') {
				diff[a].add(b);
				diff[b].add(a);
			}
		}

		for (int i = 1; i <= n; i++) {
			if (type[i] == 0) {
				dfs(i, 1);
				connected++;
			}
		}
		if (impossible) {
			out.println(0);
		} else {
			out.print(1);
			for (int i = 0; i < connected; i++) { out.print(0); }
			out.println();
		}
		out.close();
        br.close();
	}
	public static void dfs(int node, int color) {
		type[node] = color;

		for (int neighbor : same[node]) {
			if (type[neighbor] == 3 - color) { 
                impossible = true; 
            }
			if (type[neighbor] == 0) { dfs(neighbor, color); }
		}
		for (int neighbor : diff[node]) {
			if (type[neighbor] == color) { 
                impossible = true; 
            }
			if (type[neighbor] == 0) { 
                dfs(neighbor, 3 - color); 
            }
		}
	}
}