package USACOGuide;
import java.util.*;
import java.io.*;
public class diamond {
    public static int[] getRightmost(int[] arr, int k) {
		int[] ret = new int[arr.length];
		int j = arr.length - 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			while (j >= 0 && arr[j] - arr[i] > k) { j--; }
			ret[i] = j;
		}
		return ret;
	}

	public static int[] getLeftmost(int[] arr, int k) {
		int[] ret = new int[arr.length];
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			while (j < arr.length && arr[i] - arr[j] > k) { j++; }
			ret[i] = j;
		}
		return ret;
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
        //Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int[] leftmostIndex = getLeftmost(arr, k);
		int[] leftSize = new int[n];
		for (int i = 0; i < n; i++) {
			leftSize[i] = i - leftmostIndex[i] + 1;
			if (i > 0) { 
                leftSize[i] = Math.max(leftSize[i], leftSize[i - 1]); 
            }
		}

		int[] rightmostIndex = getRightmost(arr, k);
		int[] rightSize = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			rightSize[i] = rightmostIndex[i] - i + 1;
			if (i < n - 1) {
				rightSize[i] = Math.max(rightSize[i], rightSize[i + 1]);
			}
		}
		int ans = 0;
		for (int i = 0; i < n - 1; i++) {
			ans = Math.max(ans, leftSize[i] + rightSize[i + 1]);
		}
	    out.println(ans);
		out.close();
        br.close();
	}
}