package USACOGuide;
import java.io.*;
import java.util.*;
public class pairup {
    static class Cow implements Comparable<Cow>{
        int number, milkProduction;
        public Cow(int number, int milkProduction){
            this.number = number;
            this.milkProduction = milkProduction;
        }

        public int compareTo(Cow c) {
            return Integer.compare(this.milkProduction, c.milkProduction);
        }

    }
    public static void main(String[] args) throws IOException {
        //Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Cow[] cows = new Cow[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int milk = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(num, milk);
        }
        Arrays.sort(cows);
        int left = 0;
        int right = n-1;
        int max = 0;
        while(left <= right){
            max = Math.max(max, cows[left].milkProduction + cows[right].milkProduction);
            cows[left].number--;
            cows[right].number--;
            
            if(cows[left].number == 0){
                left++;
            }
            if(cows[right].number == 0){
                right--;
            }
        }
        out.println(max);
        //scan.close();
        br.close();
        out.close();
    }
}