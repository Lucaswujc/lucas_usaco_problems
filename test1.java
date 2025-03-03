import java.util.*;
public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double max = 0;
        for(double x = 0; x <= 1; x += 0.0000001 ){
            double y = 1 - x*x;
            y = Math.sqrt(y);
            max = Math.max(max, 2*x+y);
        }
        System.out.println(max);
        System.out.println(Math.sqrt(3)+0.5);
        scan.close();
    }
}