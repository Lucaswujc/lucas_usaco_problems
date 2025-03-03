package USACOGuide;
import java.util.*;
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int n = scan.nextInt();
        TreeSet<Integer> pos = new TreeSet<>();
        pos.add(0);
        pos.add(x);
        add(x);
        for(int i  =0; i < n; i++){
            int t = scan.nextInt();
            pos.add(t);
            int diff = pos.higher(t) - pos.lower(t);
            if(multiset.containsKey(diff)){
                remove(diff);
            }
            add(pos.higher(t)-t);
            add(t-pos.lower(t));
            System.out.print(multiset.lastKey()+ " ");
        }
        scan.close();
    }
}