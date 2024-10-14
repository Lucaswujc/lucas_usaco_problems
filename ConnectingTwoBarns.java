import java.util.*;
public class ConnectingTwoBarns {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
		for (int test = 0; test < t; test++) {
			int n = scan.nextInt();
			int m = scan.nextInt();

			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i < n; i++) { adj.add(new ArrayList<>()); }
			for (int i = 0; i < m; i++) {
				int a = scan.nextInt() - 1;
				int b = scan.nextInt() - 1;
				adj.get(a).add(b);
				adj.get(b).add(a);
			}
			int[] visited = new int[n];

			int count = 0;
			Arrays.fill(visited, -1);
			for (int i = 0; i < n; i++) {
				if (visited[i] == -1) {
					Stack<Integer> stack = new Stack<>();
					stack.push(i);
					while (!stack.isEmpty()) {
						int curr = stack.pop();
						if (visited[curr] != -1) continue;
						visited[curr] = count;

						for (int neighbor : adj.get(curr)) { 
                            stack.push(neighbor); 
                        }
					}
					count++;
				}
			}

			List<List<Integer>> components = new ArrayList<>();
			for (int i = 0; i < count; i++) {
				components.add(new ArrayList<>());
			}

			for (int i = 0; i < n; i++) { 
                components.get(visited[i]).add(i); 
            }

			List<Integer> barn1 = components.get(visited[0]);
			List<Integer> barn2 = components.get(visited[n - 1]);

			long[] dist1 = new long[count];
			long[] dist2 = new long[count];
			Arrays.fill(dist1, Integer.MAX_VALUE);
			Arrays.fill(dist2, Integer.MAX_VALUE);

			int barn1Index = 0;
			for (int i = 0; i < n; i++) {
				int dist = Math.abs(barn1.get(barn1Index) - i);

				while (barn1Index < barn1.size() - 1 &&
                 Math.abs(barn1.get(barn1Index + 1) - i) < dist) {
					barn1Index++;
				}

				dist1[visited[i]] = Math.min(dist, dist1[visited[i]]);
			}

			int barn2Index = 0;
			for (int i = 0; i < n; i++) {
				int dist = Math.abs(barn2.get(barn2Index) - i);

				while (barn2Index < barn2.size() - 1 &&
				       Math.abs(barn2.get(barn2Index + 1) - i) < dist) {
					barn2Index++;
				}
				dist2[visited[i]] = Math.min(dist, dist2[visited[i]]);
			}

			long min = Long.MAX_VALUE;
			for (int i = 0; i < count; i++) {
				long cost = dist1[i] * dist1[i] + dist2[i] * dist2[i];
				min = Math.min(min, cost);
			}
			System.out.println(min);
        }
        //input
        //identify the connected components
        /**
         * first we identify the connected components within the graph , 
         * which means within each group, we can identify a color number, 
         * colors[] int[] 
         * for (int i = 1; i < = n ; i_+){
         *  if the colors[i] != -1{
         *  bfs or dfs color (startnode = i , color = i, colors)
         * else skip
         *  }
         * }
         * get a distinct colors into a list 
         * for(color : colorlist){
         *  mindistnace_from_color_to_color0
         *  mindistance_from_color_to_colorN
         *  ansewr = min(answer, mind_distance_c_to_1+min_distance_c_toN)
         * }
         * sort the nodes in each color group 
         * compute distance between to groups
         * g1 contains x elements
         * g2 contains y elements
         * for x in g1 : 
         *  min= min(x to all the nodes in g2)
         * 
         * search a y value in g2 where abs(y-x) is the smallest
         * sort g2 first, thn binary search
         * 
         */

        scan.close();
    }
}