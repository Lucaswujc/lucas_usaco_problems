package bronze2019to2020;
import java.util.*;
import java.util.ArrayList;

public class LivestockLineup {
    public static ArrayList<int[]> solutions = new ArrayList<int[]>();
    public static void heapsPermute(int[] array,int start, int end, int[][] rules, String[] cows){ 
        if(start == end){
            if(ruleCheck(array, rules)){
                solutions.add(Arrays.copyOf(array, 8));
            }
        }
        else{
            for(int i = start; i <= end; i++){
                int temp = array[i];
                array[i] = array[start];
                array[start] = temp;
                heapsPermute(array, start+1, end, rules, cows);
                array[start] = array[i];
                array[i] = temp;
            }
        }
        
    }
    public static Boolean ruleCheck(int[] array, int[][] rules){
        for(int i = 0; i < rules.length; i++){
            int cow1 = 0;
            int cow2 = 0;
            for(int j = 0; j<8; j++){
                if(array[j] == rules[i][0]){
                    cow1 = j;
                }
                if(array[j] == rules[i][1]){
                    cow2 = j;
                }
            }
            if((cow2-cow1)*(cow2-cow1) != 1){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        // Take in/sort/convert cows to numbers
        String[] cows = "Bessie,Buttercup,Belinda,Beatrice,Bella,Blue,Betsy,Sue".split(",");
        Arrays.sort(cows);
        int[] initialArray = new int[8];
        HashMap<String,Integer> dict = new HashMap<>();
        for(int i = 0; i < cows.length;i++){
            dict.put(cows[i],i);
        }
        for (int i = 0; i < initialArray.length; i++) initialArray[i]=i;
        //input and create 
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] rules = new int[n][2];
        for(int k = 0; k < n; k++){
            String cow = scan.next();
            rules[k][0] = dict.get(cow);
            for(int j = 0; j < 5; j++){
                cow = scan.next();
            }
            rules[k][1] = dict.get(cow);
        }
       
        heapsPermute(initialArray, 0, 7, rules, cows);
        String[] comparing = new String[solutions.size()];
        for(int i = 0; i<solutions.size(); i++){
            int[] thingy = solutions.get(i);
            String a = "";
            for(int j = 0; j < 8; j++){
                String s = Integer.toString(thingy[j]);
                a = a + s;
            }
            comparing[i] = a;
        }
        Arrays.sort(comparing);
        String sol = comparing[0];
        for(int i = 0; i < 8; i++){     
            int idx = Integer.parseInt(sol.substring(i,i+1));
            System.out.println(cows[idx]);
        }
        scan.close();
    }    
}
