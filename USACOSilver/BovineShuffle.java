package USACOSilver;
import java.io.*;
import java.util.*;
public class BovineShuffle {
    public static int n;
	public static int[] parent;
	public static int[] status;
	public static void count(int i) {
		HashSet<Integer> there = new HashSet<Integer>();
		while (status[i] == 0) {
			there.add(i);
			status[i] = 1;
			i = parent[i];
		}
		if (there.contains(i)) {
			int samei = i;
			do {
				status[i] = 2;
				i = parent[i];
			} while (i != samei);
		}
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        parent = new int[n];
		for (int i = 0; i < n; i++) { 
            parent[i] = Integer.parseInt(st.nextToken()) - 1;
        }

		status = new int[n];
		for (int i = 0; i < n; i++){
			if (status[i] == 0) {
                count(i);
            }
        }

		int res = 0;
		for (int i = 0; i < n; i++){
			if (status[i] == 2) {
                res++;
            }
        }
		out.println(res);
        out.close();
        br.close();
    }
}