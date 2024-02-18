package bronze2020to2021;

import java.util.*;
public class UdderedButNotHerd {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cowphabet  = scan.next();
        HashMap<Character, Integer> cowphabetnums = new HashMap<>();
        for(int i = 0; i < 26; i++){
            cowphabetnums.put(cowphabet.charAt(i), i+1);
        } 
        String s = scan.next();
        int counter = 1;
        for(int i = 0 ; i < s.length()-1; i++){
            if(cowphabetnums.get(s.charAt(i)) - cowphabetnums.get(s.charAt(i+1)) >= 0){
                counter++;
            }
        }
        System.out.println(counter);
        scan.close();
    }
}