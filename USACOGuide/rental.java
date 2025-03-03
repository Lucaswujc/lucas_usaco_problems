package USACOGuide;

import java.io.*;
import java.util.*;

public class rental {
	static class Shop implements Comparable<Shop>{
		public int price;
		public int amt;
		public Shop(int amt, int price) {
			this.amt = amt;
			this.price = price;
		}
        @Override
        public int compareTo(Shop s){
            return (-1)*(price - s.price);
        }
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new FileReader("rental.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		Integer[] milkAmt = new Integer[n];
		for (int i = 0; i < n; i++) {
			milkAmt[i] = Integer.parseInt(br.readLine());
		}

		Shop[] shops = new Shop[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			shops[i] = new Shop(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Integer[] rent = new Integer[r];
		for (int i = 0; i < r; i++) {
			rent[i] = Integer.parseInt(br.readLine());
		}
        
        Arrays.sort(milkAmt, Collections.reverseOrder());
        Arrays.sort(shops);
        Arrays.sort(rent, Collections.reverseOrder());
        int shopAt = 0; 
		int rentAt = 0;
		int cowAt = 0;
		long maxMoney = 0;
		while (cowAt < n) {
			int amt = milkAmt[cowAt];
			int soldMoney = 0;
			int currShop = shopAt;
			int last = 0;
			while (currShop < m) {
				int sold = Math.min(amt, shops[currShop].amt);
				soldMoney += shops[currShop].price * sold;
				amt -= sold;

				if (amt == 0) {
					last = sold;
					break;
				} else {
					currShop++;
				}
			}
			if (rentAt >= r || soldMoney > rent[rentAt]) {
				maxMoney += soldMoney;
				shopAt = currShop;
				if (shopAt < m) { shops[shopAt].amt -= last; }
				cowAt++;
			} else {
				maxMoney += rent[rentAt];
                n--;
				rentAt++;
			}
		}
        out.println(maxMoney);
        out.close();
        br.close();
    }
}