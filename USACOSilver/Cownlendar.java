package USACOSilver;
import java.util.*;
public class Cownlendar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Set<Long> months = new HashSet<>();
        long max = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            long month = scan.nextLong();
            months.add(month);
            max = Math.min(max, month/ (long) 4);
        }
        if (months.size() <= 3) {
            System.out.println((max * (max + (long) 1)) / (long) 2);
        } 
        else {
            Set<Long> values = new HashSet<>();
            Long[] monthsSet = months.toArray(new Long[0]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < i; j++) {
                    long diff = Math.abs(monthsSet[j] - monthsSet[i]);
                    for (long x = 1; x <= 100000; x++) {
                        if (diff % x == (long) 0 ) {
                            values.add(x);
                            values.add(diff / x);
                        }
                    }
                }
            }
 
            long answer = 0;
            for (long l : values) {
                if (l <= max) {
                    Set<Long> seen = new HashSet<>();
                    for (long month : monthsSet) {
                        seen.add(month % l);
                    }
                    if (seen.size() <= 3) {
                        answer += l;
                    }
                }
            }
            System.out.println(answer);
        }
        scan.close();
    }
}