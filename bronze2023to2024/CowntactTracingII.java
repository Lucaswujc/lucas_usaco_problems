package bronze2023to2024;
import java.util.*;
import java.io.*;
public class CowntactTracingII {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Boolean[] cows = new Boolean[n];
        String s = scan.next();
        for(int i = 0; i < n; i++){
            cows[i] = s.charAt(i) == '1' ? true : false;
        }
        int len = 0;
        ArrayList<Integer> lengths = new ArrayList<>();
        Boolean infectedcows = false;
        for(int i = 0; i < n; i++){
            if(cows[i] == true){
                len++;
                infectedcows  = true;
            }
            else{
                if(len > 0){
                    lengths.add(len);
                }
                len = 0;
            }
        }
        if(len > 0){
            lengths.add(len);
        }
        if(!infectedcows){
            out.println(0);
        }
        else{
            int infected = 0;
            int l = lengths.size();
            
            int maxdays = Math.min(lengths.get(0), lengths.get(l-1)) -1;
            if(!cows[0]){
                maxdays = Math.min(maxdays, (lengths.get(0)-1)/2);
            }
            if(!cows[n-1]){
                maxdays = Math.min(maxdays, (lengths.get(l-1)-1)/2);
            }
            for(int i = 1; i < l-1; i++){
                maxdays = Math.min(maxdays, (lengths.get(i)-1)/2);
            }
            for(int i = 0; i < l; i++){
                infected += lengths.get(i)/(2 * maxdays + 1);
                if(lengths.get(i) % (2*maxdays+1) != 0){
                    infected++;
                } 
            }
            out.println(infected);
        }
        scan.close();
        out.close();
    }
}