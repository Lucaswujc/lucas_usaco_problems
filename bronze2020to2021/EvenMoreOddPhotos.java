package bronze2020to2021;

import java.util.*;
public class EvenMoreOddPhotos {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int evens = 0;
        int odds = 0;
        for(int i = 0; i < n; i++){
            int id = scan.nextInt();
            if(id % 2 ==0){
                evens++;
            }
            else{
                odds++;
            }
        }
        Boolean evenOrOdd = true;
        int groups = 0;
        //true for even, false for odd
        while(true){
            if(evenOrOdd){
                if(evens > 0){
                    evens--;
                    groups++;
                }
                else if(odds >= 2){
                    odds-= 2;
                    groups++;
                }
                else{
                    groups--;
                    break;
                }
                evenOrOdd = false;
            }
            else{
                if(odds>= 1){
                    odds--;
                    groups++;
                }
                else{
                    break;
                }
                evenOrOdd = true;
            }
            if(evens == 0 && odds == 0){
                break;
            }
        }
        System.out.println(groups);
        scan.close();
    }
}