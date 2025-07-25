package USACOGuide;
import java.util.*;
public class SumofThreeValues {
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
        for(int i = 0; i < n; i++){
            int left = 0;
            int right = n-1;
            while (left < right){
                int target = x - arr[i].num;
                int sum = arr[left].num + arr[right].num;
				if ((left != i && right != i) && (sum == target)) {
					System.out.println(arr[i].id + " " + arr[left].id + " " + arr[right].id);
					scan.close();
					return; 
				}
				if (sum < target) {
					left++;
				}
				else {
					right--;
				}
            }
        }
        System.out.println("IMPOSSIBLE");
        scan.close();
    }
}