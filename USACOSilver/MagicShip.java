package USACOSilver;
import java.util.*;
public class MagicShip {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long x= scan.nextLong(); 
        long y = scan.nextLong();
        long tx = scan.nextLong();
        long ty = scan.nextLong();
        int n = scan.nextInt();
        String wind = scan.next();
        if(!reachable(x, y, tx, ty, wind, Long.MAX_VALUE/2)){
            System.out.println(-1);
			System.exit(0);
        }

        long lo = 0;
        long hi = Long.MAX_VALUE;
        long ans = -1;
        while(lo <= hi){
            long mid = (lo+hi)/2;
            if(reachable(x, y, tx, ty, wind, mid)){
                ans = mid;
                hi = mid-1;
            }
            else{
                lo = mid+1;
            }
        }
        System.out.println(ans);
        scan.close();
    }
    static boolean reachable(long x, long y, long tx, long ty, String wind, long time){
        long windX = 0;
		long windY = 0;
		for (int i = 0; i < wind.length(); i++) {
            char w = wind.charAt(i);
			switch (w) {
			case 'U':
				windY++;
				break;
			case 'D':
				windY--;
				break;
			case 'L':
				windX--;
				break;
			case 'R':
				windX++;
				break;
			}
		}
        windX *= time/wind.length();
        windY *= time/wind.length();
        long rem = time % wind.length();
        for (int i = 0; i < rem; i++) {
            char w = wind.charAt(i);
			switch (w) {
			case 'U':
				windY++;
				break;
			case 'D':
				windY--;
				break;
			case 'L':
				windX--;
				break;
			case 'R':
				windX++;
				break;
			}
		}
        return (Math.abs(x+windX-tx) + Math.abs(y+windY-ty)) <= time;
    }
}