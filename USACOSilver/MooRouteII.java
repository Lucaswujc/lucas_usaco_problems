package USACOSilver;
import java.util.*;
public class MooRouteII {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int inf = Integer.MAX_VALUE;

        List<List<int[]>> flightsBase = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            flightsBase.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int c = scan.nextInt();
            int r = scan.nextInt();
            int d = scan.nextInt();
            int s = scan.nextInt();
            flightsBase.get(c).add(new int[]{r, d, s});
        }
        int[] layovers = new int[n];
        for (int i = 1; i < n; i++) {
            layovers[i] = scan.nextInt();
        }
        layovers[1] = 0;

        List<List<int[]>> flights = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            flights.add(new ArrayList<>());
        }

        for (int c = 1; c <= N; c++) {
            for (int[] flight : flightsBase.get(c)) {
                int r = flight[0] - layovers[c];
                int d = flight[1];
                int s = flight[2];
                flights.get(c).add(new int[]{r, d, s});
            }
            flights.get(c).sort((a, b) -> Integer.compare(b[0], a[0]));
        }

        int[] time = new int[N + 1];
        Arrays.fill(time, inf);
        time[1] = 0;

        int[] idx = new int[N + 1];
        Queue<int[]> q = new LinkedList<>();

        for (int[] flight : flights.get(1)) {
            q.add(flight);
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0], d = current[1], s = current[2];

            time[d] = Math.min(time[d], s);

            while (idx[d] < flights.get(d).size() && flights.get(d).get(idx[d])[0] >= s) {
                q.add(flights.get(d).get(idx[d]));
                idx[d]++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (time[i] != inf) {
                System.out.println(time[i]);
            } else {
                System.out.println(-1);
            }
        }

        scan.close();
    }
}