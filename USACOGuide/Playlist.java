package USACOGuide;

import java.util.*;
import java.io.*;

public class Playlist {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int r = -1;
        HashSet<Integer> s = new HashSet<Integer>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (r < n - 1 && !s.contains(arr[r + 1]))
                s.add(arr[++r]);
            ans = Math.max(ans, r - i + 1);
            s.remove(arr[i]);
        }
        System.out.println(ans);
    }
}