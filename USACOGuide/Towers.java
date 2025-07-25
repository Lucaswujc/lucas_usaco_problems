package USACOGuide;
import java.io.*;
import java.util.*;
public class Towers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] cubes = new int[n];
        st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) { 
            cubes[i] = Integer.parseInt(st.nextToken()); 
        }

		TreeMap<Integer, Integer> towers = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			if (towers.higherKey(cubes[i]) == null) {
				towers.put(cubes[i], towers.getOrDefault(cubes[i], 0) + 1);
			}
			else {
				int size = towers.higherKey(cubes[i]);
				towers.put(size, towers.get(size) - 1);
				if (towers.get(size) == 0) { 
                    towers.remove(size); 
                }
				towers.put(cubes[i], towers.getOrDefault(cubes[i], 0) + 1);
			}
		}
        int ans = 0;
		for (int a : towers.values()) { 
            ans += a; 
        }
        System.out.println(ans);
    }

}