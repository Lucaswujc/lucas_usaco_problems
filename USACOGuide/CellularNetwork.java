package USACOGuide;
import java.util.*;
public class CellularNetwork {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] cities = new int[n];
        int[] towers = new int[m];
        int max = 0;
        for(int i  =0 ; i < n; i++){
            cities[i] = scan.nextInt();
        }
        for(int i  =0 ; i < m; i++){
            towers[i] = scan.nextInt();
        }
        int left1 = 0;
        int left2 = 0;
        while(left1 < n && left2 < m){
            if(left2 < m-1){
                while(left2 < m-1 && (Math.abs(cities[left1] - towers[left2]) >= Math.abs(cities[left1] - towers[left2+1]))){
                    left2++;
                }
            }
            max = Math.max(max,Math.abs(cities[left1] - towers[left2]));
            left1++;
        }
        System.out.println(max);
        scan.close();
    }
}