package USACOGuide;
import java.util.*;
public class RoomAllocation {
    static class Customer implements Comparable <Customer> {
		int arrival, departure, index;
		public Customer(int arrival, int departure, int index) {
			this.arrival = arrival;
			this.departure = departure;
			this.index = index;
		}
        @Override
        public int compareTo(Customer c) {
            return Integer.compare(c.arrival, this.arrival);
        }
	}
    static class Room implements Comparable <Room>{
		int departure, number;

		Room(int departure, int number) {
			this.departure = departure;
			this.number = number;
		}

        @Override
        public int compareTo(Room r) {
            return Integer.compare(r.departure, this.departure);
        }
	}
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Customer[] customers = new Customer[n];
        for(int i = 0; i < n; i++){
            customers[i] = new Customer(scan.nextInt(), scan.nextInt(), i);
        }
        Arrays.sort(customers);
        PriorityQueue<Room> pq = new PriorityQueue<>();
        int[] rooms = new int[n];
        int last = 1;
        int ans = 0;
        rooms[customers[0].index] = last;
        pq.add(new Room(customers[0].departure, last));
        for(int  i = 0; i < n; i++){
            Room min = pq.peek();
            if(min.departure < customers[i].departure){
                pq.remove();
                pq.add(new Room(customers[i].departure, min.number));
				rooms[customers[i].index] = min.number;
			} else {
				last++;
				pq.add(new Room(customers[i].departure, last));
				rooms[customers[i].index] = last;
			}       
            ans = Math.max(ans,pq.size());
        }
        System.out.println(ans);
        for(int r : rooms){
            System.out.println(r+" ");
        }
        scan.close();
    }
}