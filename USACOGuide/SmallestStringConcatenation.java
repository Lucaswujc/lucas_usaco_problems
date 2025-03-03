package USACOGuide;
import java.util.*;
public class SmallestStringConcatenation {
    static class str implements Comparable<str>{
        public String s;
        public str(String s){
            this.s = s;
        }
        @Override
        public int compareTo(str o) {
            return (s+o.s).compareTo(o.s+s);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        str[] arr  = new str[n];
        for(int i = 0; i < n ;i++){
            String s = scan.next();
            arr[i] = new str(s);
        }
        Arrays.sort(arr);
        for(int  i =0 ; i < n ;i++){
            System.out.print(arr[i].s);
        } 
        
        scan.close();
    }
}