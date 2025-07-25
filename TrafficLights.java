import java.util.*;
import java.io.*;
public class TrafficLights {
    static TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();
    static void add(int x) {
        if (multiset.containsKey(x)) {
            multiset.put(x, multiset.get(x) + 1);
        } else {
            multiset.put(x, 1);
        }
    }
 
    static void remove(int x) {
        multiset.put(x, multiset.get(x) - 1);
        if (multiset.get(x) == 0) { multiset.remove(x); }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        TreeSet<Integer> pos = new TreeSet<>();
        pos.add(0);
        pos.add(x);
        add(x);
        st = new StringTokenizer(br.readLine());
        for(int i  =0; i < n; i++){
            int t = Integer.parseInt(st.nextToken());
            pos.add(t);
            int diff = pos.higher(t) - pos.lower(t);
            if(multiset.containsKey(diff)){
                remove(diff);
            }
            add(pos.higher(t)-t);
            add(t-pos.lower(t));
            System.out.print(multiset.lastKey()+ " ");
        }
        br.close();
    }
}
