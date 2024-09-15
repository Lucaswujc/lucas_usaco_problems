package USACOGuide;
import java.util.*;
public class StuckinARut {
    static int[] x;
    static int[] y;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<Integer> eastCows = new ArrayList<>();
		List<Integer> northCows = new ArrayList<>();
		x = new int[n];
		y = new int[n];
		for (int i = 0; i < n; i++) {
			String direction = scan.next();
			if (direction.equals("E")) {
				eastCows.add(i);
			} else {
				northCows.add(i);
			}
			x[i] = scan.nextInt();
			y[i] = scan.nextInt();
		}
        eastCows.sort(Comparator.comparingInt(j -> y[j]));
        northCows.sort(Comparator.comparingInt(j -> x[j]));
        boolean[] stop = new boolean[n];
		int[] cowsStopped = new int[n];
		for (int i : eastCows) {
			for (int j : northCows) {
				if (((!stop[i]) && (!stop[j])) && ((x[j] > x[i]) && (y[i] > y[j]))) {
					if ((x[j] - x[i]) > (y[i] - y[j])) {
						stop[i] = true;
						cowsStopped[j]+= (1 + cowsStopped[i]);
					} else if ((x[j] - x[i]) <  (y[i] - y[j])) {
						stop[j] = true;
						cowsStopped[i]+= (1 + cowsStopped[j]);
					}
				}
			}
		}
        for (int i = 0; i < n; i++) { 
            System.out.println(cowsStopped[i]); 
        }
        scan.close();
    }
}