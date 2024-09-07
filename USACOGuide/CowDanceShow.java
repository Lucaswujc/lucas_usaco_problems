package USACOGuide;
import java.util.*;
import java.io.*;
public class CowDanceShow {
    static int[] danceTimes;
	static int n;
	static int maxTime;

	static boolean isOk(int stageSize) {
		PriorityQueue<Integer> currentDancing = new PriorityQueue<>();
		for (int x = 0; x < n; x++) {
			if (currentDancing.size() < stageSize) {
				currentDancing.add(danceTimes[x]);
			} else {
				currentDancing.add(currentDancing.remove() + danceTimes[x]);
			}
		}

		int lastFinish = Integer.MAX_VALUE;

		while (currentDancing.size() > 0) {
			lastFinish = currentDancing.remove();
		}
		return lastFinish <= maxTime;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		maxTime = Integer.parseInt(st.nextToken());
		danceTimes = new int[n];
       
		for (int x = 0; x < n; x++) { 
            st = new StringTokenizer(br.readLine());
            danceTimes[x] = Integer.parseInt(st.nextToken()); 
        }

		int low = 1;   
		int high = n;  

		while (low < high) {
			int mid = (low + high) / 2;
			if (isOk(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		out.println(low);
		out.close();
        br.close();
	}
}