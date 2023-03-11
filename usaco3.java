import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class usaco3 {
    /*
     * Scan n & k
     * Create array days of size n
     * Scan n numbers
     * Create Boolean Arr[n-1]
     * loop from 0 to n-1
     * if i is 0
     * compare two days
     * compare 2 + 2k & days[i+1]-days[i] + k
     * if 2 + 2k is greater assign false to arr[i]
     * assign true to arr[i] if there should be consecutive subscription
     * if i != 0
     * if(arr[i-1] is false)
     * compare two days
     * compare 2 + 2k & days[i+1]-days[i] + k
     * if 2 + 2k is greater assign false to arr[i]
     * assign true to arr[i] if there should be consecutive subscription
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usaco3.out")));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long[] days = new long[(int) n];
        st = new StringTokenizer(scan.readLine());
        for (int i = 0; i < n; i++) {
            days[i] = Long.parseLong(st.nextToken());
        }
        daddySolution(days, n, k);
    }

    /**
     * from days[] array, we will visit each and every element from index 0 to n-2
     * at a location i, we will calculate days[i+1]-days[i], the difference of two days
     * in the array representing the incremental cost if we want to include the days[i+1] in to 
     * the current subscription. 
     * if incrementalCost > k , then it is better of stop the current subscription and put the 
     * days[i+1] into its own subscription . 
     * We can use a variable to track the totalCost at each visit, the previous logic will change 
     * the total cost depends on the decision. 
     * Notice that when we visit the ith location in days[i], the days[i] is already considered in the subscription
     * and we are deciding whether to put days[i+1] into the subsciption
     *  
     * @param days this array stores the day that Bessie want to watch movie
     * @param n total number of days Bessie want to watch movies, n is the size of days array
     * @param k fixed cost to start subsrciption 
     */
    public static void daddySolution(long[] days, long n, long k ){
        long totalCost = k+1;//first element in the days array will always cost k+1 
        for(int i = 0 ; i < n-1; i++){
            long incrementalCost = days[i+1] - days[i];
            if(incrementalCost > k){
                //days[i+1] is in its own subscritption
                totalCost = totalCost + k+1;
            }else{
                totalCost += incrementalCost;
            }
        }
        System.out.println(totalCost);
    }

    /**
     * use an array to remember consecutive subscription status as boolean
     * iterate through the days array and fill the true/false into such array to 
     * represent that at location i, true means days[i] should be in a consecutive subscription
     * the size of this array is n-1 , and careful: we actually need to visit the nth elemnt 
     * because the last element in days[n] can be a non-consecutive subscrition (very dangerous risky )
     * @param n
     * @param days
     * @param k
     */
    public static void lucasSolution(int n, long[] days, long k) {
        Boolean[] consec = new Boolean[(int) n - 1];
        long money = 0;
        for (int i = 0; i < n - 1; i++) {
            // if i is 0
            if (i == 0) {
                if ((2 + 2 * k) < (days[i + 1] - days[i] + 1 + k)) {
                    consec[i] = false;
                } else {
                    consec[i] = true;
                }
            } else { // if i is nto 0
                if (consec[i - 1] == false) {
                    if ((2 + 2 * k) < (days[i + 1] - days[i] + 1 + k)) {
                        consec[i] = false;
                    } else {
                        consec[i] = true;
                    }
                } else {
                    if ((1 + k) < (days[i + 1] - days[i])) {
                        consec[i] = false;
                    } else {
                        consec[i] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                if (consec[i] == false) {
                    money += (1 + k);
                } else {
                    int temp = i;
                    while (((i + 1) != n - 1) && consec[i + 1] == true) {
                        i++;
                    }
                    i++;
                    long add = (days[i] - days[temp] + 1 + k);
                    money += add;
                }
            } else {
                money += (1 + k);
            }
        }
        System.out.println(money);
        // out.close();
    }
}
