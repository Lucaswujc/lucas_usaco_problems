package usaco_bronze_booster;

import java.util.Scanner;
//@formatter:off
/**
 * Triangles
 * [ Memory: 256 MB, CPU: 2 sec ]
 * <p>
 * Farmer John would like to create a triangular pasture for his cows.
 * There are 𝑁 fence posts (3≤𝑁≤100 ) at distinct points (𝑋1,𝑌1)…(𝑋𝑁,𝑌𝑁)
 * on the 2D map of his farm. He can choose three of them to form the
 * vertices of the triangular pasture as long as one of the sides of
 * the triangle is parallel to the 𝑥-axis and another side is parallel
 * to the 𝑦-axis.
 * <p>
 * What is the maximum area of a pasture that Farmer John can form? It
 * is guaranteed that at least one valid triangular pasture exists.
 * <p>
 * INPUT FORMAT:
 * <p>
 * The first line of the input contains the integer 𝑁. Each of the next 𝑁
 * lines contains two integers 𝑋𝑖 *  and 𝑌𝑖, each in the range −104…104
 * inclusive, describing the location of a fence post.
 * <p>
 * OUTPUT FORMAT:
 * <p>
 * As the area itself is not necessarily an integer, output two times
 * the maximum area of a valid triangle formed by the fence posts.
 * <p>
 * SAMPLE INPUT:
 * <p>
 * 4
 * 0 0
 * 0 1
 * 1 0
 * 1 2
 * SAMPLE OUTPUT:
 * <p>
 * 2
 * <p>
 * Posts at (0,0) , (1,0) , and (1,2)  form a triangle of area 1.
 * Thus, the answer is 2⋅1=2. There is only one other triangle,
 * with area 0.5.
 */
//@formatter:on

public class Triangles {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[][] posts = new int[N][2];
        for (int i = 0; i < N; i++) {
            posts[i][0] = scan.nextInt();
            posts[i][1] = scan.nextInt();
        }
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || posts[i][0] != posts[j][0]) continue;
                for (int k = 0; k < N; k++) {
                    if (i == k || j == k || posts[j][1] != posts[k][1]) continue;
                    int[] p1 = posts[i];
                    int[] p2 = posts[j];
                    int[] p3 = posts[k];
                    int area = Math.abs((p1[1] - p2[1]) * (p2[0] - p3[0]));
                    maxArea = area > maxArea ? area : maxArea;

                }
            }
        }
        System.out.println(maxArea);
    }
}
