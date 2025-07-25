package USACOSilver;
import java.util.*;
public class PlanetsCycle {
    static int n, steps;
	static int[] planets, len;
	static boolean[] visited;
	static LinkedList<Integer> path = new LinkedList<>();
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		planets = new int[n];
		visited = new boolean[n];
		len = new int[n];
		for (int i = 0; i < n; i++) { 
            planets[i] = scan.nextInt()-1; 
        }

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				steps = 0;
				dfs(i);
				int decrement = 1;
				Integer last = path.peekLast();
				while (!path.isEmpty()) {
					if (path.peekFirst().equals(last)) decrement = 0;
					len[path.poll()] = steps;
					steps -= decrement;
				}
			}
		}

		for (int i = 0; i < n; i++) { 
            System.out.print(len[i] + " "); 
        }
		scan.close();
	}

	public static void dfs(int n) {
		visited[n] = true;
		path.add(n);
		steps++;
		if (!visited[planets[n]]) dfs(planets[n]);
		else {
			path.add(planets[n]);
			steps += len[planets[n]];
		}
	}
}