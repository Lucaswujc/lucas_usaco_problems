package USACOGuide;
import java.util.*;
public class TheMeetingPlaceCannotBeChanged {
    static final double MAX_ERROR = 10e-7;
    static boolean left_has_max = false;
	static boolean right_has_max = false;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double[] pos = new double[n];
        double[] speed = new double[n];

        double high = 0;
        double low = 0;
        for(int i  = 0; i < n; i++){
            pos[i] = scan.nextDouble();
            high = Math.max(high, pos[i]);
        }
        for(int i  = 0; i < n; i++){
            speed[i] = scan.nextDouble();
        }
        double ans = Double.MAX_VALUE;
	    double currmin = 0;
        while(high - low >= MAX_ERROR){
            double mid = (high + low) / 2;
			currmin = time(pos, speed, mid);
			ans = Math.min(ans, currmin);
			if (left_has_max && right_has_max) {
				break;
			} else if (left_has_max) {
				high = mid;
			} else {
				low = mid;
			}
		}
        System.out.println(ans);
        scan.close();
    }
    static double time(double pos[] , double[] speed, double mid){
        double max_time = 0;
        for (int i = 0; i < pos.length; i++) {
			if (pos[i] == mid) { continue; }
			double i_time = Math.abs(pos[i] - mid) / speed[i];
			if (i_time > max_time) {
				left_has_max = false;
				right_has_max = false;
				if (pos[i] > mid) {
					right_has_max = true;
				} else {
					left_has_max = true;
				}
				max_time = i_time;
			} else if (i_time == max_time) {
				if (pos[i] > mid) {
					right_has_max = true;
				} else {
					left_has_max = true;
				}
			}
		}
		return max_time;
    }   
}