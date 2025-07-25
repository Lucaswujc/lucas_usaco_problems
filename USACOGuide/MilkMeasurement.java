package USACOGuide;

import java.io.*;
import java.util.*;

public class MilkMeasurement {
    static class Measure implements Comparable<Measure> {
        int day, cow, change;

        public Measure(int day, int cow, int change) {
            this.day = day;
            this.cow = cow;
            this.change = change;
        }

        @Override
        public int compareTo(Measure other) {
            return Integer.compare(this.day, other.day);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        int n = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        Measure[] measurements = new Measure[n];
        TreeMap<Integer, Integer> cows = new TreeMap<>();
        Set<Integer> maxCows = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            measurements[i] = new Measure(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            cows.put(measurements[i].cow, g);
            maxCows.add(measurements[i].cow);
        }
        Arrays.sort(measurements);
        int ans = 0;
        TreeMap<Integer, Integer> milk = new TreeMap<>();
        milk.put(g, n);
        for (Measure m : measurements) {
            int curr = cows.get(m.cow);
            boolean wasTop = curr == milk.lastKey();
            int prev = milk.get(curr);
            milk.put(curr, milk.get(curr) - 1);
            if (milk.get(curr) == 0) {
                milk.remove(curr);
            }
            curr += m.change;
            cows.put(m.cow, curr);
            milk.put(curr, milk.getOrDefault(curr, 0) + 1);

            boolean isTop = curr == milk.lastKey();
            int currNum = milk.get(curr);
            if (wasTop) {
                if (isTop && currNum == prev) {
                    continue;
                }
                ans++;
            } else if (isTop) {
                ans++;
            }
        }
        out.println(ans);
        out.close();
        br.close();
    }
}