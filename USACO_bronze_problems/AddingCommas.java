package USACO_bronze_problems;

import java.util.*;

public class AddingCommas {
    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            String s = scan.next();
            int len = s.length();
            int extra;
            //If mod 3 of length = 0 then descrease loop iterartions
            if(len % 3 == 0)
                extra = len/3 - 1; 
            else 
                extra = len/3;
            String s1 = "";
            int x = 1;
            for(int i = 1; i < len+1+extra; i++){
                if(i % 4 == 0)
                    s1 = ',' + s1;
                else{
                    s1 = s.charAt(len - x) + s1;
                    x++;
                }
            }
            System.out.println(s1);
        }
    }
}