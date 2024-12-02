import java.io.*;
import java.util.*;
public class ClockTree {
    static int n;
    static ArrayList<Integer>[] adj;
    static int[] clocks;
    static int sum0 = 0, sum1 = 0, nodes0 = 0, nodes1 = 0;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
		PrintWriter out = new PrintWriter("clocktree.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { 
            adj[i] = new ArrayList<>(); 
        }
        clocks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            clocks[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(0, 0, -1);
		if ((sum0 % 12) == (sum1 % 12)) {
			out.println(n);
		}
		else if ((sum0 + 1) % 12 == (sum1 % 12)) {
			out.println(nodes1);
		}
		else if ((sum0 % 12) == ((sum1 + 1) % 12)) {
			out.println(nodes0);
		}
		else {
			out.println(0);
		}
        out.close();
        br.close();
	}

	static void dfs(int i, int color, int parent) {
		if (color == 0) {
			nodes0++;
			sum0 += clocks[i];
		} else {
			nodes1++;
			sum1 += clocks[i];
        }
		for (int neighbor : adj[i]) {
			if (neighbor != parent) {
				dfs(neighbor, 1 - color, i);
			}
		}
	}

}