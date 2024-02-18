package USACO_bronzeclass_problems;

import java.util.*;


public class FunWithOctals {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String hex = scan.next();
        int len = hex.length();
        String[] number = new String[len*4];
        int counter = 4*len-1;
        for(int i =0; i < len*4; i++){
            number[i] = "0";
        }
        for(int i = len; i > 0; i--){
            String digit = hex.substring(i-1,i);
            int digit2 = Integer.parseInt(digit, 16);
            String octal = Integer.toBinaryString(digit2);
            int octlen = octal.length();
            int counter2 = counter;
            for(int j =octlen; j > 0; j--){
                int add= Integer.parseInt(octal.substring(j-1,j));
                int add2 = Integer.parseInt(number[counter2]);
                add = add +add2;
                String complete = Integer.toString(add);
                number[counter2] = complete;
                counter2--;
            }
            counter--;
        }
        
        int carry = 0;
        for(int i = len*4-1; i > 0; i--){
            int num = Integer.parseInt(number[i]);
            if(i != len){
                num = num + carry;
                if(num > 8){
                    int newnum = num;
                    num = num % 8;
                    newnum = newnum - num;
                    newnum = newnum/8;
                    carry = newnum;
                    String finish = Integer.toString(num);
                    number[i] = finish;
                }
            }
        }
        int tracker = 0;
        for(int i = 0; i < len;i++){
            if(number[i] != "0"){
                tracker = i;
            }
        }
        for(int i = tracker; i < len; i++){
            System.out.print(number[i]);
        }
        scan.close();
    }
}