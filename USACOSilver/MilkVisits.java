package USACOSilver;
import java.io.*;
import java.util.*;
public class MilkVisits {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter out = new PrintWriter("milkvisits.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		String farms = br.readLine();

		List<Integer>[] neighbors = new ArrayList[n];
		for (int i = 0; i < n; i++) { 
			neighbors[i] = new ArrayList<>(); 
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer road = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(road.nextToken()) - 1;
			int b = Integer.parseInt(road.nextToken()) - 1;
			neighbors[a].add(b);
			neighbors[b].add(a);
		}
		int componentNum = 0;
		int[] component = new int[n];
		Arrays.fill(component, -1);
		for (int i = 0; i < n; i++) {
			if (component[i] != -1) { continue; }
			ArrayDeque<Integer> frontier = new ArrayDeque<>();
			frontier.add(i);
			char type = farms.charAt(i);
			while (!frontier.isEmpty()) {
				int curr = frontier.poll();
				component[curr] = componentNum;
				for (int neighbor : neighbors[curr]) {
					if (farms.charAt(neighbor) == type && component[neighbor] == -1) {
						frontier.add(neighbor);
					}
				}
			}
			componentNum++;
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			char milk = st.nextToken().charAt(0);

			if (component[a] == component[b]) {
				out.print(farms.charAt(a) == milk ? 1 : 0);
			} 
            else {
				out.print(1);
			}
		}
        br.close();
		out.close();
	}
}