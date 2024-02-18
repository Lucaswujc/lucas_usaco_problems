package usaco_bronze_parta;
import java.util.*;
public class WallPainting {
    // Scan numbers
    // Divide numbers by 2 until one is even
    // If any number is even to start with answer is 0
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        //check if N or M is equal
        // If yes print 0
        // Else continue 
        int paintedSquares = 0;
        if(!(N % 2==0 || M % 2 == 0)){
            while(true){
                paintedSquares = paintedSquares * 4 + 1;
                N = N/2;
                M = M/2;
                if(N%2==0 || M %2 ==0){
                    break;
                }
            }
        }
        System.out.println(paintedSquares);
        scan.close();
    }
}
