package bronze2020to2021;

import java.util.*;
public class DoYouKnowYourABCs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = 7;
        int[] numbers = new int[n];
        for(int i = 0; i <n ;i++){
            numbers[i] = scan.nextInt();
        }
        Arrays.sort(numbers);
        int a = numbers[0];
        int b = numbers[1];
        int c = numbers[6] - numbers[0] - numbers[1];
        System.out.println(a + " " + b + " " + c);        
        scan.close();
    }
}