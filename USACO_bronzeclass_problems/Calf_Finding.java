package USACO_bronzeclass_problems;

import java.util.*;
public class Calf_Finding {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        int distance = 1;
        int travel = 0;
        int num = 0;
        int currentx = x;
        Boolean done = false;
        if(x==y){
            travel = 0;
        }
        else{
            while(x != y){
                
                if(num % 2 == 0){
                    while(x!= (distance + currentx)){
                        x = x +1;
                        travel = travel + 1;
                        if(x == y){
                            done = true;
                            break;
                        }
                    }
                }
                else{
                    while(x != (distance +currentx)){
                        x = x - 1;
                        travel = travel + 1;
                        if(x == y){
                            done = true;
                            break;
                        }
                    }
                }
                if(done== true){
                    break;
                }
                distance = distance *(-2);
                num++;
            }
        }
        System.out.println(travel);
        scan.close();
    }
}

