package USACOSilver;
import java.util.*;
public class TargetPracticeII {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t  = scan.nextInt();
        while(t >0){
            int n = scan.nextInt();
            int x = scan.nextInt();

            List<Integer> yvalues = new ArrayList<>();
            List<Coord> pos = new ArrayList<>();
            List<Coord> neg = new ArrayList<>();
            for(int i = 0; i < n;i++){
                int y1 = scan.nextInt();
                int y2 = scan.nextInt();
                int x2 = scan.nextInt();

                yvalues.add(y1);
                yvalues.add(y2);
                pos.add(new Coord(x2, y1));
                neg.add(new Coord(x2, y2));
            }
            List<Integer> slopes = new ArrayList<>();
            for (int i = 0; i < 4 * n; i++) {
                slopes.add(scan.nextInt());
            }
            List<Integer> slopeNeg = new ArrayList<>();
            List<Integer> slopePos = new ArrayList<>();

            for (int s : slopes) {
                if (s < 0) {
                    slopeNeg.add(s);
                } else {
                    slopePos.add(s);
                }
            }
            if (neg.size() < n || pos.size() < n) {
                System.out.println(-1);
                continue;
            }
    
            Collections.sort(yvalues);
    
            for (int y : yvalues) {
                if (neg.size() < slopeNeg.size()) {
                    neg.add(new Coord(x, y));
                } 
                else {
                    pos.add(new Coord(x, y));
                }
            }
            double min = solveMin(pos, slopePos);
            List<int[]> reversed = new ArrayList<>();
            for (Coord point : neg) {
                reversed.add(new int[]{point.x, -point.y});
            }
            List<Integer> reversedSlope = new ArrayList<>();
            for (int s : slopeNeg) {
                reversedSlope.add(-s);
            }
            double max = -solveMin(neg, slopeNeg);
            System.out.println(max-min);
            t--;
        }
        scan.close();
    }
    static class Coord implements Comparable<Coord>{
        int x, y;
        Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(Coord c) {
            if(this.x == c.x){
                return Integer.compare(c.y, this.y);
            }
            return Integer.compare(c.x, this.x);
        }
        
    }
    public static double solveMin(List<Coord> pos, List<Integer> slope) {
        Collections.sort(pos);
        double min = Double.MAX_VALUE;

        for (int s : slope) {
            double bestY = -1 * Double.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < pos.size(); i++) {
                Coord point = pos.get(i);
                int x1 = point.x; 
                int y = point.y;
                double value = y - x1 * s;

                if (value > bestY) {
                    bestY = value;
                    idx = i;
                }
            }
            if(bestY < min){
                min = Math.min(min, bestY);
                pos.remove(idx);
            }
            
        }

        return min;
    }

}