import java.util.*;

public class Dishwasher {
    static int[] arr;
    static List<Deque<Integer>> items = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int popped = 0;
        int ans = n;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            items.add(new ArrayDeque<>());
        }

        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            if (x < popped) {
                ans = i;
                break;
            }

            for (int j = x; j > 0 && arr[j] == 0; j--) {
                arr[j] = x;
            }
            Deque<Integer> stack = items.get(arr[x]);
            while (!stack.isEmpty() && stack.peekLast() < x) {
                popped = stack.pollLast();
            }

            stack.addLast(x);
        }

        System.out.println(ans);
        scan.close();
    }
}
