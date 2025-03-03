package USACOSilver;
import java.util.*;
public class FindAndReplace {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            String orig = scan.next();
            String transform = scan.next();
            int[] graph = new int[52];
            Arrays.fill(graph, -1);
            boolean works = true;
            Set<Character> appear = new HashSet<>();
            for (int i = 0; i < orig.length(); i++) {
                int a = 0;
                if('a' <= orig.charAt(i) && orig.charAt(i) <= 'z'){
                    a = orig.charAt(i) - 'a';
                }
                else{
                    a = 26 + orig.charAt(i) - 'A';
                }
                int b = 0;
                if('a' <= orig.charAt(i) && orig.charAt(i) <= 'z'){
                    b = transform.charAt(i) - 'a';
                }
                else{
                    b = 26 + transform.charAt(i) - 'A';
                }
                appear.add(transform.charAt(i));
                if (graph[a] != -1 && graph[a] != b) {
                    works = false;
                }
                graph[a] = b;
            }
            if (appear.size() == 52) {
                works = false;
            }
            if (orig.equals(transform)) {
                works = true;
            }
            int ans = 0;
            if (works) {
                int[] inDegree = new int[52];
                for (int a = 0; a < 52; a++) {
                    if (graph[a] != -1 && graph[a] != a) {
                        inDegree[graph[a]]++;
                    }
                }
 
                for (int a = 0; a < 52; a++) {
                    if (graph[a] != -1 && graph[a] != a) {
                        ans ++;
                    }
                }
 
                int[] seen = new int[52];
                for (int r = 0; r < 52; r++) {
                    if (seen[r] == 0) {
                        int a = r;
                        while (a != -1 && seen[a] == 0) {
                            seen[a] = r + 1;
                            
                            a = graph[a];
                        }
                        if (a != -1 && a != graph[a] && seen[a] == r + 1) {
                            int s = a;
                            boolean skip = false;
                            do {
                                seen[a] = 2;
                                if (inDegree[a] > 1) {
                                    skip = true;
                                }
                                a = graph[a];
                            } 
                            while (a != s);
                            if (!skip) {
                                ans++;
                            }
                        }
                    }
                }
                System.out.println(ans);
            } 
            else {
                System.out.println(-1);
            }
            t--;
        }
        scan.close();
    }

}