package USACOGuide;
import java.util.*;;
public class CorrectPlacement {
    static class dimension implements Comparable<dimension>{
        public int id;
        public int h;
        public int w;
        public dimension(int w, int h, int id){
            this.h = h;
            this.w = w;
            this.id = id;
        }
        @Override
        public int compareTo(dimension d) {
            if (this.w == d.w){
                return Integer.compare(this.h, d.h);
            }
			return Integer.compare(this.w, d.w);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n  = scan.nextInt();
            dimension[] lengths = new dimension[n];
            for(int i =0 ; i < n; i++){
                int h = scan.nextInt();
                int w = scan.nextInt();
                lengths[i] = new dimension(Math.min(w, h), Math.max(w,h),i);
            }
            Arrays.sort(lengths);
            int ans[] = new int[n]; 
            Arrays.fill(ans, -1);
            int compH = Integer.MAX_VALUE;
            int compId = 0;
            int lowH = Integer.MAX_VALUE;
            int lowId = 0; 
            for (int i = 0; i < n; i++) {
                if (i > 0 && lengths[i].w != lengths[i - 1].w && compH < lowH) {
                    lowH = compH;
                    lowId = compId;
                }

                if (lowH < lengths[i].h)
                    ans[lengths[i].id] = lowId;

                if (lengths[i].h < compH) {
                    compH = lengths[i].h;
                    compId = lengths[i].id + 1;
                }
            }
            for(int  i =0 ; i < n ;i++){
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            t--;
        }
        scan.close();
    }
}