package USACOGuide;
import java.io.*;
import java.util.*;
public class ConcertTickets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();
        st = new StringTokenizer(br.readLine());
        for(int i  = 0; i < n ;i++){
            int a = Integer.parseInt(st.nextToken());
            if (multiset.containsKey(a)) {
                multiset.put(a, multiset.get(a) + 1);
            } else {
                multiset.put(a, 1);
            }
        }
        Map.Entry<Integer, Integer> val;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int p = Integer.parseInt(st.nextToken());
            val = multiset.lowerEntry(p+1);
            if(val!=null){
                System.out.println(val.getKey());
                multiset.put(val.getKey(), multiset.get(val.getKey()) - 1);
	            if (multiset.get(val.getKey()) == 0) { 
                    multiset.remove(val.getKey()); 
                }
                
            }
            else{
                System.out.println(-1);
            }   
        }
        br.close();
        out.close();
    }
}