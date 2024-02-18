package USACO_bronzeclass_problems;

import java.util.*;

import static java.lang.System.out;

public class RubicsCube {
    public static void main(String[] args) {
        int[][] x = {
                {1, 2, 3, 4},
                {8, 7, 6, 5}
        };
        @SuppressWarnings("unused")
        int[][] y = {
                {1, 2, 3, 4},
                {8, 7, 6, 5}
        };
        int[][] target = {
                {2, 4, 7, 5},
                {1, 3, 8, 6}
        };
        //print(transformC(x));
//        HashMap<int[][], String> test = new HashMap<>();
//        test.put(x, "A");
//        out.print(test.get(y));
//        FakeKey key1 = new FakeKey();
//        key1.keyval = String.valueOf(1);
//        FakeKey key2 = new FakeKey();
//        key2.keyval = String.valueOf(2);
//        out.println(key2.equals(key1));
//        HashMap<FakeKey,String> test2 = new HashMap<>();
//        test2.put(key1,"A");
//        out.println(test2.get(key2));
        out.println(bfs(x, target));

    }

    static class FakeKey {
        String keyval ;

        @Override
        public int hashCode() {
            return 100;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    static class Result {
        int[][] parent;
        int[][] self;
        int level;
        HashMap<String, String> edges;

        public Result(int[][] self) {
            this.self = self;
            edges = new HashMap<String, String>();
        }
    }

    public static String arrayToString(int[][] input) {
        String ret = "";
        for (int[] r : input) {
            for (int c : r) {
                ret = ret + c;
            }
        }
        return ret;
    }

    public static String bfs(int[][] input, int[][] target) {
        String[] transforms = {"A", "B", "C"};
        String ret = "";

        Set<int[][]> currentLevel = new HashSet<int[][]>();
        HashMap<String, Result> visited = new HashMap<>();
        int level = 0;
        Result r = new Result(input);
        r.level = level;
        r.parent = null;
        visited.put(arrayToString(input), r);
        currentLevel.add(input);
        outerloop:
        while (!currentLevel.isEmpty()) {
            HashSet<int[][]> temp = new HashSet<int[][]>();
            for (int[][] x : currentLevel) {
                int[][] transformresult = null;
                for (String t : transforms) {
                    switch (t) {
                        case "A":
                            transformresult = transformA(x);
                            break;
                        case "B":
                            transformresult = transformB(x);
                            break;
                        case "C":
                            transformresult = transformC(x);
                            break;
                    }
                    String key = arrayToString(transformresult);
                    if (!visited.containsKey(key)) {
                        temp.add(transformresult);
                        r = new Result(transformresult);
                        r.level = level+1;
                        r.parent = x;
                        visited.put(key, r);
                        visited.get(arrayToString(x)).edges.put(key, t);

                        if (isEqual(transformresult, target)) {
                            //we can shortcut to stop here
                            out.println("found");
                            break outerloop;
                        }
                    }
                }
            }
            level += 1;
            currentLevel = temp;
            out.println(level);
        }



        //final step to get the result printed to the string
        r = visited.get(arrayToString(target));
        Deque<String> solution = new ArrayDeque<>();
        while (r.parent != null) {
            Result p = visited.get(arrayToString(r.parent));
            String transform = p.edges.get(arrayToString(r.self));
            solution.push(transform);
            r = p;
        }
        while (!solution.isEmpty()) {
            ret = ret + solution.pop();
        }
        return ret;
    }

    public static boolean isEqual(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * transform A up and own switch
     */
    public static int[][] transformA(int[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0; i < input[0].length; i++) {
            result[0][i] = input[1][i];
            result[1][i] = input[0][i];
        }
        return result;
    }

    /**
     * transform B , shift all columns to right
     */
    public static int[][] transformB(int[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                result[0][i + 1] = input[0][i];
                result[1][i + 1] = input[1][i];
            } else {
                result[0][0] = input[0][i];
                result[1][0] = input[1][i];
            }
        }
        return result;
    }

    /**
     * transform C rotate the center matrix clock wise
     */
    public static int[][] transformC(int[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                result[i][j] = input[i][j];
            }
        }
        result[0][1] = input[1][1];
        result[0][2] = input[0][1];
        result[1][1] = input[1][2];
        result[1][2] = input[0][2];
        return result;
    }

    public static void print(int[][] input) {
        for (int[] row : input) {
            for (int c : row) {
                out.print(String.format("%d\t", c));
            }
            out.println();
        }
        out.println("-----------------------");
    }
}
