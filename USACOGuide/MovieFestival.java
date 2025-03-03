package USACOGuide;
import java.util.*;
public class MovieFestival {
    static class Movie implements Comparable<Movie>{
        int start,end;
        public Movie (int start, int end){
            this.start = start;
            this.end = end;
        }
        public int compareTo(Movie m) {
            return Integer.compare(this.end, m.end);
        }
        
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Movie[] movies = new Movie[n];
        for(int i = 0; i < n; i++){
            movies[i] = new Movie(scan.nextInt(), scan.nextInt());
        }
        Arrays.sort(movies);
        int currentend = -1;
        int ans = 0;
        for(int i  =0; i < n; i++){
            if(movies[i].start >= currentend){
                currentend = movies[i].end;
                ans++;
            }
        }
        System.out.println(ans);
        
        scan.close();
    }
}