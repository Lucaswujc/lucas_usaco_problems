package USACOGuide;
import java.util.*;
public class SumofTwoValues {
    static class Pair implements Comparable<Pair>{
        int num, id;
        public Pair(int num, int id){
            this.num = num;
            this.id = id;
        }

        public int compareTo(Pair p) {
            return Integer.compare(this.num, p.num);
        }

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        Pair[] arr = new Pair[n];
        for(int i = 0; i < n; i++){
            int a = scan.nextInt();
            arr[i] = new Pair(a, i+1);
        }
        Arrays.sort(arr);
        int left = 0;
        int right = n-1;
        while (left < right){
            int sum = arr[left].num + arr[right].num;
            if(sum > x){
                right--;
            }
            else if (sum < x){
                left++;
            }
            else{
                System.out.println(arr[left].id + " " + arr[right].id);
                scan.close();
                return;
            }
        }
        System.out.println("IMPOSSIBLE");
        scan.close();
    }
}