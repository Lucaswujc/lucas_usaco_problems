package USACOGuide;
import java.io.*;
import java.util.*;

public class mountains {
    static class coord implements Comparable<coord>{
		public int start;
		public int end;
		public coord(int start, int end) {
			this.start = start;
			this.end = end;
		}
        @Override
        public int compareTo(coord s){
            if(this.start != s.start){
                return Integer.compare(this.start, s.start);
            }
            return Integer.compare(s.end, this.end);
        }
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        coord[] points = new coord[n];
        for(int i = 0; i <n ;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new coord(x-y,x+y);
        }
        Arrays.sort(points);
        int maxright = 0;
        int ans = 0;
        for(int i =0; i < n; i++){
            if(points[i].end> maxright){
                maxright = points[i].end;
                ans++;
            }
        }
        out.println(ans);
        br.close();
        out.close();
    }
}
