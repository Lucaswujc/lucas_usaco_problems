package USACOGuide;
import java.util.*;
import java.io.*;
public class WhyDidTheCowCrossTheRoad {
    static class Cow implements Comparable<Cow> {
		int start;
		int end;
		public Cow(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Cow c) {
			return Integer.compare(c.start, this.start);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int chickens = Integer.parseInt(st.nextToken());
        int cows = Integer.parseInt(st.nextToken());
        int[] chickenTime = new int[chickens];
        Cow[] cowTime = new Cow[cows];
        for(int i = 0; i < cows; i++){
            st = new StringTokenizer(br.readLine());
            chickenTime[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < chickens; i++){
            st = new StringTokenizer(br.readLine());
            cowTime[i] = new Cow(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(chickenTime);
        Arrays.sort(cowTime);
        int ans = 0;
        out.println(ans);
        out.close();
        br.close();
    }
}