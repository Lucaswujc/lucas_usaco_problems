package bronze2019to2020;

import java.util.*;
public class CowntractTracing {
    public static void sortbyColumn(int arr[][]){ 
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0],b[0])); 
    }
    static int patientzeros = 0;
    static int newpatientzero = -1;
    static int mink = Integer.MAX_VALUE;
    static int maxk = 0;
    public static void simulate(int patientZero, int k, boolean[] cows, int[][] events){
        boolean[] simulateInfect = new boolean[cows.length];
        Arrays.fill(simulateInfect, false);
        int[] infectionsLeft = new int[cows.length];
        simulateInfect[patientZero] = true;
        infectionsLeft[patientZero] = k;
        for(int i = 0; i < events.length; i++){
            int cow1 = events[i][1]-1;
            int cow2 = events[i][2]-1;
            if(simulateInfect[cow1] && !simulateInfect[cow2]){
                if(infectionsLeft[cow1] > 0){
                    infectionsLeft[cow1]--;
                    simulateInfect[cow2] = true;
                    infectionsLeft[cow2] = k;
                }
            }
            else if(simulateInfect[cow2] && !simulateInfect[cow1]){
                if(infectionsLeft[cow2] > 0){
                    infectionsLeft[cow2]--;
                    simulateInfect[cow1] = true;
                    infectionsLeft[cow1] = k;
                }
            }
            else if(simulateInfect[cow2] && simulateInfect[cow1]){
                infectionsLeft[cow1]--;
                infectionsLeft[cow2]--;
            }
        }
        if(Arrays.equals(simulateInfect, cows)){
            if(k < mink){
                mink = k;
            }
            if(k > maxk){
                maxk = k;
            }
            if(patientZero != newpatientzero){
                patientzeros++;
                newpatientzero = patientZero;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int t = scan.nextInt();
        String cows = scan.next();
        boolean[] infected = new boolean[n];
        Arrays.fill(infected, false);
        int[][] touched = new int[t][3];
        for(int i = 0; i < n; i++){
            if(cows.charAt(i) == '1'){
                infected[i] = true;
            }
        }
        for(int i = 0; i < t; i++){
            touched[i][0] = scan.nextInt();
            touched[i][1] = scan.nextInt();
            touched[i][2] = scan.nextInt();
        }
        sortbyColumn(touched);
        for(int i = 0; i <n; i++){
            for(int k = 0; k <= t; k++){
                simulate(i, k, infected, touched);
            }
        }
        if(maxk == t){
            System.out.println(patientzeros+ " " + mink + " " + "Infinity");
        }
        else{
            System.out.println(patientzeros+ " " + mink + " " + maxk);
        }
        scan.close();
    }
}