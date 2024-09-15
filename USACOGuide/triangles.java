package USACOGuide;
import java.util.*;
import java.io.*;

public class triangles {
    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        //Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("5.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new
        FileWriter("triangles.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Integer, ArrayList<Point>> xcoords = new HashMap<>();
        Map<Integer, ArrayList<Point>> ycoords = new HashMap<>();
        HashSet<Point> anchorPoints = new HashSet<>();
        long totalArea = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point p = new Point(x, y);
            if (!xcoords.containsKey(p.x)) {
                xcoords.put(p.x, new ArrayList<Point>());
            }
            xcoords.get(p.x).add(p);
            if (!ycoords.containsKey(p.y)) {
                ycoords.put(p.y, new ArrayList<Point>());
            }
            ycoords.get(p.y).add(p);

            anchorPoints.add(p);
        }

        HashMap<Integer, Long> baseSumLkup = new HashMap<>();
        HashMap<Integer, Long> heightSumLkup = new HashMap<>();
        for (Point anchorPoint : anchorPoints) {
            int xCounts = xcoords.get(anchorPoint.x).size();
            int yCounts = ycoords.get(anchorPoint.y).size();
            if (xCounts >= 2 && yCounts >= 2) {
                long xtotal = 0;
                if (baseSumLkup.containsKey(anchorPoint.y))
                    xtotal = baseSumLkup.get(anchorPoint.y);
                else {
                    for (Point otherPoints : ycoords.get(anchorPoint.y)) {
                        if (otherPoints == anchorPoint) {
                            continue;
                        }
                        xtotal += Math.abs(otherPoints.x - anchorPoint.x);
                    }
                    baseSumLkup.put(anchorPoint.y, xtotal);
                }
                long ytotal = 0;
                if (heightSumLkup.containsKey(anchorPoint.x))
                    ytotal = heightSumLkup.get(anchorPoint.x);
                else {
                    for (Point otherPoints : xcoords.get(anchorPoint.x)) {
                        if (otherPoints == anchorPoint) {
                            continue;
                        }
                        ytotal += Math.abs(otherPoints.y - anchorPoint.y);
                    }
                    heightSumLkup.put(anchorPoint.x, ytotal);
                }
                ytotal %= 1000000007;
                xtotal %= 1000000007;
                long a = xtotal * ytotal;
                a %= 1000000007;
                totalArea += a;
                totalArea %= 1000000007;
            }
            else {
                // this is not an anchor point, skip
                continue;
            }
        }
        System.out.println(totalArea);
        br.close();
        out.close();
        //scan.close();
    }
}