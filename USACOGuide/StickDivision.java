package USACOGuide;
import java.io.*;
import java.util.*;
public class StickDivision {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		Queue<Integer> sticks = new PriorityQueue<Integer>();
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) sticks.add(Integer.valueOf(st.nextToken()));

		long costs = 0;
        
		while (sticks.size() > 1) {
			int a = sticks.remove();
			int b = sticks.remove();
			sticks.add(a + b);
			costs += a + b;
		}
		System.out.println(costs);
        x = x + 1;
    }
}