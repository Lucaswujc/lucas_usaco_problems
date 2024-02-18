package bronze2020to2021;

import java.util.*;
public class DaisyChains {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double[] petals = new double[n];
        for(int i = 0; i < n; i++){
            petals[i] = scan.nextDouble();
        }
        int counter = 0;
        for(int  i =0 ; i< n; i++){
            for(int  j = i; j < n; j++){
                double sum = 0;
                int leftbound = i;
                int rightbound = j;
                while(leftbound <= rightbound){
                    sum += petals[leftbound];
                    leftbound++;
                }
                sum = sum/(j - i + 1);
                leftbound = i;
                while(leftbound <= rightbound){
                    if(petals[leftbound] == sum){
                        counter++;
                        break;
                    }
                    leftbound++;
                }
            }
        }
        System.out.println(counter);
        scan.close();
    }
}
