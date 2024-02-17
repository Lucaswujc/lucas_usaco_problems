package USACO_bronzeclass_problems;

import java.util.*;

public class EvenOrOdd {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int n = scan.nextInt();
            String a;
            int b;
            for(int i = 0; i < n; i++){
                a = scan.next();
                int len = a.length();
                b = a.charAt(len - 1);
                b = b - '0';
                if(b % 2 == 1)
                    System.out.println("odd");
                else
                    System.out.println("even");
            }
        }
    }
}