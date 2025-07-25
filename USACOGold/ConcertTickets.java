package USACOGold;

import java.util.*;

public class ConcertTickets {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        TreeMap<Integer, Integer> tickets = new TreeMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int price = scan.nextInt();
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            int price = scan.nextInt();
            Integer key = tickets.floorKey(price);
            if (key == null) {
                System.out.println(-1);
            } else {
                System.out.println(key);
                int count = tickets.get(key);
                if (count == 1) {
                    tickets.remove(key);
                } else {
                    tickets.put(key, count - 1);
                }
            }
        }
        scan.close();
    }
}