package bronze2019to2020;

import java.util.*;
public class MadScientist {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n =scan.nextInt();
        String a = scan.next();
        String b = scan.next();
        int counter= 0 ;
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i)){
                while((a.charAt(i) != b.charAt(i)) && (i < n)){
                    i++;
                }
                counter++;
            }
        }
        System.out.println(counter);
        scan.close();
    }
}