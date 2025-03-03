package USACOGuide;
import java.io.*;
import java.util.*;
public class sort {
    static class number implements Comparable<number>{
        int num,id;
        public number (int num ,int id){
            this.num = num;
            this.id = id; 
        }
        @Override
        public int compareTo(number n) {
            return Integer.compare(num, n.num);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new
        FileWriter("sort.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(st.nextToken());
        number[] arr = new number[n];
        int[] compress = new int[n];
        for(int i = 0; i <n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            arr[i] = new number(a, i);
            compress[i] = i;
        }
        Arrays.sort(arr);
        int max = 0;
        for(int i = 0; i <n; i++){
            if(Math.abs(arr[i].id - compress[i]) > max){
                max= Math.abs(compress[i] - arr[i].id);
            }
        }
        out.println(max+1);
        //scan.close();
        out.close();
        br.close();
    }
}