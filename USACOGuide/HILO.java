package USACOGuide;

import java.util.*;

public class HILO {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // solution 1
        int n = scan.nextInt();
        int[] pos = new int[200001];
        int[] prv = new int[200001];
        boolean[] hilo = new boolean[200001];
        for (int i = 1; i <= n; i++) {
            int p = scan.nextInt();
            pos[p] = i;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = n; i > 0; i--) {
            while (stack.peek() > pos[i]) {
                stack.pop();
            }
            prv[pos[i]] = stack.peek();
            stack.push(pos[i]);
        }

        while (stack.size() != 1) {
            stack.pop();
        }

        System.out.println(0);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            while (stack.peek() > pos[i]) {
                ans -= hilo[stack.peek()] ? 1 : 0;
                stack.pop();
            }
            hilo[pos[i]] = prv[pos[i]] != 0 && (stack.peek() == 0 || prv[pos[i]] != prv[stack.peek()]);
            stack.push(pos[i]);
            ans += hilo[pos[i]] ? 1 : 0;
            System.out.println(ans);
        }
        scan.close();
    }
}