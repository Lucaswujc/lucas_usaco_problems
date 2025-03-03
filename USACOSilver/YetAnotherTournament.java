package USACOSilver;
import java.util.*;
public class YetAnotherTournament {
    static class Opponent implements Comparable<Opponent> {
		int time;
		int idx;
		public Opponent(int time, int idx) {
			this.time = time;
			this.idx = idx;
		}
		@Override
		public int compareTo(Opponent c) {
			return Integer.compare(c.time, this.time);
		}
	}
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t>0){
            int n = scan.nextInt();
            int m = scan.nextInt();
            Opponent[] opponents = new Opponent[n];
            for(int  i= 0; i < n; i++){
                opponents[i] = new Opponent(scan.nextInt(), scan.nextInt());
            }
            Arrays.sort(opponents);
            int sum = 0;
            for(int i = 0; i < n; i++){
                if(sum > m){
                    sum -= opponents[i].time;
                }
                sum += opponents[i].time;
            }
        }
        scan.close();
    }
}