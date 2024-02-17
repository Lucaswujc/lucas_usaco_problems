package bronze2020to2021;

import java.util.*;
import java.util.ArrayList;

public class YearOfTheCow {
    public static int year_difference(String start, String end, String direction){
        String s = "Ox, Tiger, Rabbit, Dragon, Snake, Horse, Goat, Monkey, Rooster, Dog, Pig, Rat";
        String[] zodiacs = s.split(", ");
        int idx= 0;
        int counter = 0;
        while(!zodiacs[idx].equals(start)){
            idx++;
        }
        if(direction.equals("previous")){
            counter--;
            idx--;
            idx = idx+12;
            idx = idx%12;
            while(!zodiacs[idx].equals(end)){
                counter--;
                idx--;
                idx = idx+12;
                idx = idx%12;
            }
        }
        else {
            counter = 1;
            idx++;
            idx = idx%12;
            while(!zodiacs[idx].equals(end)){
                counter++;
                idx++;
                idx = idx%12;
            }
        }
        return counter;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayList<String> zodiacs = new ArrayList<String>();
        names.add("Bessie");
        years.add(0);
        zodiacs.add("Ox");
        for(int i = 0; i  < n; i++){
            String cow1 = scan.next();
            scan.next(); scan.next();
            String direction = scan.next();
            String zodiac = scan.next();
            scan.next();scan.next();
            String cow2 = scan.next();
            names.add(cow1);
            zodiacs.add(zodiac);
            int info1 = 0;
            String info2 = "";
            for(int j = 0; j < names.size()-1; j++){
                if(names.get(j).equals(cow2)){
                    info1 = years.get(j);
                    info2 = zodiacs.get(j);
                }
            }
            int year = info1 + year_difference(info2, zodiac, direction);
            years.add(year);
            if(cow1.equals("Elsie")){
                int info = 0;
                for(int j = 0; j < names.size()-1; j++){
                    if(names.get(j).equals("Elsie")){
                        info = years.get(j);
                    }
                    
                }
                year = info;
                System.out.println(Math.abs(year));
                break;
            }
        }
        scan.close();
    }
}
