package USACOGuide;
import java.io.*;
import java.util.*;
public class SnowBoots {
    static class Boot {
		public int depth;
		public int steps;
		public int idx;
		public Boot(int depth, int steps, int idx) {
			this.depth = depth;
			this.steps = steps;
			this.idx = idx;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());        
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] snow = new int[n];
        st = new StringTokenizer(br.readLine());  
        for(int i =0 ; i < n; i++){
            
            snow[i] = Integer.parseInt(st.nextToken());
        }
        Boot[] boots = new Boot[b];
        for(int i = 0; i < b; i++){
            st = new StringTokenizer(br.readLine());  
            boots[i] = new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(boots, Comparator.comparingInt(d -> - d.depth));
        Integer[] sortedTiles = new Integer[n - 2];
        for (int i = 1; i < n - 1; i++) { 
            sortedTiles[i - 1] = i; 
        }
        Arrays.sort(sortedTiles, Comparator.comparingInt(t -> - snow[t]));

		int[] tilesByDepth = new int[n - 2];
		for (int i = 0; i < tilesByDepth.length; i++) {
			tilesByDepth[i] = sortedTiles[i];
		}

		TreeSet<Integer> validTiles = new TreeSet<>();
		for (int t = 0; t < n; t++) { 
            validTiles.add(t); 
        }

		int tileAt = 0;

		int neededStep = 1;
		boolean[] canReach = new boolean[b];
		for (Boot a : boots) {
			while (tileAt < tilesByDepth.length &&
			       snow[tilesByDepth[tileAt]] > a.depth) {
				int invalid = tilesByDepth[tileAt];
				validTiles.remove(invalid);
				neededStep =
				    Math.max(neededStep, validTiles.ceiling(invalid) -
				                             validTiles.floor(invalid));
				tileAt++;
			}
			canReach[a.idx] = a.steps >= neededStep;
		}
        for (boolean t : canReach) { 
            out.println(t ? 1 : 0); 
        }
        out.close();
        br.close();
    }
}