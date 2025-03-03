package USACOGuide;
import java.io.*;
import java.util.*;
public class StaticRangeQueries {
    static class Query {
		int l, r, v;
		public Query(int l, int r, int v) {
			this.l = l;
			this.r = r;
			this.v = v;
		}

		public Query(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	static long difference_array[];
	static int widths[]; 
	static long interval_value[]; 
	static long prefix_sums[];  
	static ArrayList<Integer> indices; 
	static Query queries[];  
	static Query updates[]; 
	static int getCompressedIndex(int a) {
		return Collections.binarySearch(indices, a);
	}
    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
		    Q = Integer.parseInt(st.nextToken());

		indices = new ArrayList<Integer>();
		queries = new Query[Q];
		updates = new Query[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()),
			    r = Integer.parseInt(st.nextToken()),
			    v = Integer.parseInt(st.nextToken());
			indices.add(l);
			indices.add(r);
			updates[i] = new Query(l, r, v);
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()),
			    r = Integer.parseInt(st.nextToken());
			indices.add(l);
			indices.add(r);
			queries[i] = new Query(l, r);
		}
		TreeSet<Integer> temp = new TreeSet<Integer>(indices);
		indices.clear();
		indices.addAll(temp); 
		difference_array = new long[indices.size() + 5];
		widths = new int[indices.size() + 5];
		interval_value = new long[indices.size() + 5];
		prefix_sums = new long[indices.size() + 5];
		for (int i = 0; i < N; i++) {
			Query a = updates[i];
			difference_array[getCompressedIndex(a.l) + 1] += a.v;
			difference_array[getCompressedIndex(a.r) + 1] -= a.v;
		}	
        for (int i = 0; i < indices.size() - 1; i++) {
			widths[i + 1] = indices.get(i + 1) - indices.get(i);
		}
		for (int i = 1; i < indices.size(); i++) {
			interval_value[i] = interval_value[i - 1] + difference_array[i];
		}
    	for (int i = 1; i < indices.size(); i++) {
			prefix_sums[i] = prefix_sums[i - 1] + interval_value[i] * widths[i];
		}
    	for (int i = 0; i < Q; i++) {
			System.out.println(prefix_sums[getCompressedIndex(queries[i].r)] -
			                   prefix_sums[getCompressedIndex(queries[i].l)]);
		}
    }
}
