package SortingAlgorithmns;

import java.util.*;

class Palindrome {
    int high;
    int low;
    int length;
    public Palindrome(int high, int low, int length) {
        this.high = high;
        this.low = low;
        this.length = length;
    }
}

public class LongPalindrome {
    static Palindrome findPalindrome(StringBuffer s, int i){
        int high = i;
        int low = i;
        int length = 0;

        while(true){
            while(true){
                if(!(high+1>s.length())){
                    int h= (int) s.charAt(high);
                    if((h >= 65 && h <= 90) ||(h>=97 && h<= 112)){
                        break;
                    }
                    high++;
                }
            }
            while(true){
                if(!(low-1<0)){
                    int l = (int) s.charAt(low);
                    if((l >=65 && l <= 90) || (l >=97 && l <= 112)){
                        break;
                    }
                    low--;
                }
            }
            if(s.charAt(high) == s.charAt(low)){
                continue;
            }
            high--;
            low++;
            length = high-low+1;            
            break;
        }
        Palindrome ret = new Palindrome(high, low, length);
        return ret;
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        StringBuffer s = new StringBuffer();
        while(true){
            try{
                s.append(scan.nextLine()+"\n");
            } catch (Exception e){
                break;   
            }
        }
        int left = 0;
        int right = 0;
        int len = 0;
        for(int i = 0; i < s.length(); i++){
            Palindrome temp = findPalindrome(s, i);
            int low = temp.low;
            int high = temp.high;
            int tempLen = temp.length;
            if(tempLen>len){
                len = tempLen;
                left= low;
                right = high;
            }
        }
        System.out.println(len);
        System.out.println(s.substring(left,right));
        scan.close();

    }
}