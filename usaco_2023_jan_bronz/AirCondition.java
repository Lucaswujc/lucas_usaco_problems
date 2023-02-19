package usaco_2023_jan_bronz;

import java.util.*;

/**
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1276
 */
public class AirCondition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // # of cows
        int N = scanner.nextInt();
        // # of air conditions
        int M = scanner.nextInt();
        CowStalls[] cowStalls = new CowStalls[N];
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            int[] tokens = Arrays.stream(line.split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
            cowStalls[i] = new CowStalls(tokens[0], tokens[1], tokens[2]);
        }
        AirConditioner[] airConditioners = new AirConditioner[M];
        for (int i = 0; i < M; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
            airConditioners[i] = new AirConditioner(tokens[0], tokens[1], tokens[2], tokens[3]);
        }

        //brutal force ???
        //build a 101010 binary array to represent whether an air conditioner at location is used or not. Here i plan to use
        // recursive functions
        //once we have such binary array, then we can iterate through this array and calculate the cost + whether
        // all cows are happy. then identify the min
        List<boolean[]> allConditions = new ArrayList<boolean[]>();
        buildFullConditions(allConditions, null ,0, M);
        // iterate through the all conditions list and find the minimal cost , store result into a 
        // map<>
        Map<boolean[], Result> results = calculateResult(allConditions, airConditioners, cowStalls);
        for (Map.Entry<boolean[], Result> e : results.entrySet()) {
            System.out.println("conditioner array: " + Arrays.toString(e.getKey()) + "\t Result:" + e.getValue().toString());

        }
    }

    private static Map<boolean[], Result> calculateResult(List<boolean[]> allConditions, AirConditioner[] airConditioners, CowStalls[] cowStalls) {
        Map<boolean[], Result> rs = new HashMap<>();
        for (boolean[] conditions : allConditions) {
            int totalcost = 0;
            int[] stallsCoolUnits = new int[100];
            for (int i = 0; i < conditions.length; i++) {
                totalcost += conditions[i] ? airConditioners[i].cost : 0;
                for (int j = airConditioners[i].from; j <= airConditioners[i].to; j++) {
                    stallsCoolUnits[j] += airConditioners[i].coolUnit;
                }
            }
            Result r = new Result(true, totalcost);
            outerloop:
            for (int cowIdx = 0; cowIdx < cowStalls.length; cowIdx++) {
                CowStalls c = cowStalls[cowIdx];
                // is cow happy?
                for (int j = c.from; j <= c.to; j++) {
                    if (stallsCoolUnits[j] < c.requiredCoolUnit) {
                        r.areCowsHappy = false;
                        r.firstUnhappyCow = cowIdx;
                        //quick exit without checking rest of cows
                        break outerloop;
                    }
                }
            }
            rs.put(conditions, r);
        }
        return rs;
    }

    private static void buildFullConditions(List<boolean[]> allCodnitions, boolean[] binaryArray, int currentIdx, int M) {
        if (currentIdx == 0) {
            //initialize the size M binary array 
            boolean[] b = new boolean[M];
            binaryArray = b;
        }
        if (currentIdx == M) {
            //do nothing.. we are done add a copy to the list
            allCodnitions.add(Arrays.copyOf(binaryArray, binaryArray.length));
            return;
        } else {
            binaryArray[currentIdx] = true;
            buildFullConditions(allCodnitions, binaryArray, currentIdx + 1, M);
            binaryArray[currentIdx] = false;
            buildFullConditions(allCodnitions, binaryArray, currentIdx + 1, M);
        }
    }

    static class Result {
        boolean areCowsHappy;
        int cost;
        //debug only attribute
        int firstUnhappyCow = -1;

        Result(boolean areCowsHappy, int cost) {
            this.areCowsHappy = areCowsHappy;
            this.cost = cost;
        }

        public String toString() {
            return "areCowsHappy:" + String.valueOf(areCowsHappy) + "\t" + "cost:" + String.valueOf(this.cost) + "\t" + "firstUnhappyCow:" + this.firstUnhappyCow;
        }
    }

    static class AirConditioner {
        int from, to, coolUnit, cost;

        AirConditioner(int from, int to, int coolUnit, int cost) {
            this.from = from;
            this.to = to;
            this.coolUnit = coolUnit;
            this.cost = cost;
        }
    }

    static class CowStalls {
        int from, to, requiredCoolUnit;

        CowStalls(int from, int to, int requiredCoolUnit) {
            this.from = from;
            this.to = to;
            this.requiredCoolUnit = requiredCoolUnit;
        }
    }
}
