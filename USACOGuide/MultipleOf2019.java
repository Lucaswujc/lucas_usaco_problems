package USACOGuide;
import java.util.*;
class MultipleOf2019 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int[] remainders = new int[2019];
        int prefix = 0;
        int multiply = 1;
        remainders[0] = 1;
        for(int i = s.length()-1; i>= 0; i--){
            prefix= (prefix + multiply * (s.charAt(i) - '0')) % 2019;
            remainders[prefix]++;
            multiply = multiply * 10 % 2019; 
        }
        long ans = 0;
        for(int i = 0 ;i < 2019;i++){
            ans += (long) remainders[i] * (remainders[i] - 1)/2;
        }
        System.out.println(ans);
        scan.close();
    }
}