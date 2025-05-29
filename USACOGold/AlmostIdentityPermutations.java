package USACOGold;
import java.util.Scanner;

public class AlmostIdentityPermutations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        long[][] c = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            c[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }

        long ans = 0;
        for (int i = 0; i <= k; i++) {
            int[] a = new int[i];
            for (int j = 0; j < i; j++) {
                a[j] = j;
            }

            int amt = 0;
            do {
                boolean valid = true;
                for (int j = 0; j < i; j++) {
                    if (a[j] == j) {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    amt++;
            } while (nextPermutation(a));

            ans += c[n][i] * amt;
        }

        System.out.println(ans);
        scan.close();
    }

    // Implements the next_permutation function from C++
    public static boolean nextPermutation(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1])
            i--;
        if (i < 0)
            return false;

        int j = a.length - 1;
        while (a[j] <= a[i])
            j--;

        // swap
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // reverse suffix
        int left = i + 1, right = a.length - 1;
        while (left < right) {
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }

        return true;
    }
}
