import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class test {
    // Scan numbers
    // Divide numbers by 2 until one is even
    // If any number is even to start with answer is 0
    public static void main(String [] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int n = Integer.parseInt(st.nextToken);
        int[] want = new int[n];
        int[] curr = new int[n];
        st = new StringTokenizer(scan.readLine());
        for(int i =0; i < n; i++){
            want[i] = Integer.parseInt(st.nextToken);
        }
        st = new StringTokenizer(scan.readLine());
        for(int i =0; i < n; i++){
            want[i] = Integer.parseInt(st.nextToken);
        }
        for(int i = n-1; i > 0; i--){
            if(curr[i] == want[i]){
                continue;
            }
            else{
                
            }
        }
        /* Problem 3
        int n = Integer.parseInt(st.nextToken());
        String[] blocks = new String[4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(scan.readLine());
            blocks[i] = st.nextToken();
        }
        ArrayList<String> possible = new ArrayList<String>();
        Boolean[] used = new Boolean[4];
        for(int i =0; i < 4; i++) {
            used[i] = false;
        }
        for(int i = 0; i < 4; i++) {
            used[i] =true;
            for(int j = 0; j < 4; j++) {
                if(used[j]){
                    continue;
                }
                used[j] =true;
                for(int k = 0; k < 4; k++) {
                    if(used[k]){
                        continue;
                    }
                    used[k] =true;
                    for(int l = 0; l < 4; l++) {
                        if(used[l]){
                            continue;
                        }
                        used[l] = true;
                        for(int l1 = 0; l1 < 6; l1++){
                            for(int l2 = 0; l2 < 6; l2++){
                                for(int l3 = 0; l3 < 6; l3++){
                                    for(int l4 = 0; l4 < 6; l4++){
                                        String word = "";
                                        word += blocks[i].charAt(l1);
                                        word += blocks[j].charAt(l2);
                                        word += blocks[k].charAt(l3);
                                        word += blocks[l].charAt(l4);
                                        possible.add(word.substring(0,1));
                                        possible.add(word.substring(0,2));
                                        possible.add(word.substring(0,3));
                                        possible.add(word.substring(0,4));
                                    }
                                }
                            }
                        }
                        used[l] = false;
                    }
                    used[k] = false;
                }
                used[j] = false;
            }
            used[i] = false;
        }
        for(int j = 0; j < n; j++){
            st = new StringTokenizer(scan.readLine());
            String word = st.nextToken();
            Boolean found =false;
            for(int i = 0; i < possible.size(); i++) {
                if(word.equals(possible.get(i))){
                    found = true;
                    break;
                }
            }
            if(found){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
        */

        /* Problem 1
        int t = Integer.parseInt(st.nextToken());
        while(t>0){
            st = new StringTokenizer(scan.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr= new int[n];
            int sum = 0;
            st = new StringTokenizer(scan.readLine());
            for(int i = 0; i < n; i++){ 
                arr[i] =Integer.parseInt(st.nextToken());
                sum += arr[i];
            }
            for(int l = n; l >1; l--){
                Boolean break1 = false;
                if(sum%l!=0){
                    continue;
                }
                else{
                    int i = 0;
                    while(true){
                        int curr_sum = arr[i];
                        i +=1;
                        while(i < n && curr_sum < sum/l){
                            curr_sum += arr[i];   
                            i +=1;
                        }
                        if(curr_sum > sum/l){
                            break;
                        }
                        if(i ==n){
                            System.out.println(n-l);
                            break1 = true;
                            break;
                        }
                    }
                }
                if(break1){
                    break;
                }
            }
            t--;
        }
        */
    }
}