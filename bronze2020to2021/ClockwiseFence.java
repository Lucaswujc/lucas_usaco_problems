package bronze2020to2021;

import java.util.*;
public class ClockwiseFence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for(int i = 0; i <t; i++){
            String s  = scan.next();
            int leftturns = 0;
            int rightturns = 0;
            for(int j = 0 ; j < s.length(); j++){
                if(s.charAt(j) == 'N' && s.charAt(((j +1) % s.length())) == 'E'){
                    rightturns++;   
                }
                else if(s.charAt(j) == 'E' && s.charAt(((j +1) % s.length())) == 'S'){
                    rightturns++;   
                }
                else if(s.charAt(j) == 'S' && s.charAt(((j +1) % s.length())) == 'W'){
                    rightturns++;   
                }
                else if(s.charAt(j) == 'W' && s.charAt(((j +1) % s.length())) == 'N'){
                    rightturns++;   
                }
                else if(s.charAt(j) == 'N' && s.charAt(((j +1) % s.length())) == 'W'){
                    leftturns++;   
                }
                else if(s.charAt(j) == 'E' && s.charAt(((j +1) % s.length())) == 'N'){
                    leftturns++;   
                }
                else if(s.charAt(j) == 'S' && s.charAt(((j +1) % s.length())) == 'E'){
                    leftturns++; 
                }
                else if(s.charAt(j) == 'W' && s.charAt(((j +1) % s.length())) == 'S'){
                    leftturns++;
                }
            }
            if(rightturns > leftturns){
                System.out.println("CW");
            }
            else{
                System.out.println("CCW");
            }
        }
        scan.close();
    }
}
