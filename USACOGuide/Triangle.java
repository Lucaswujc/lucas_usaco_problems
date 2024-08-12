package USACOGuide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Triangle {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Point>> xmap = new HashMap<Integer, ArrayList<Point>>();
        HashMap<Integer, ArrayList<Point>> ymap = new HashMap<Integer, ArrayList<Point>>();
        while (true) {
            Point p = readline(x, y);
            if !xmap.contains(p.x):
                xmap.put(x, new ArrayList<>());
            xmap.get(x).append(p);
        }


    }
}

