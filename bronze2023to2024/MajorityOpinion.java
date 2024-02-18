package bronze2023to2024;
import java.io.*;
import java.util.*;
public class MajorityOpinion {
    
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int[] hay =new int[n]; 
            for(int i = 0; i <n ; i++){
                hay[i] = scan.nextInt();
            }
            if(n == 0){
                out.println(-1);
                t--;
                continue;
            }
            else if(n ==1){
                out.println(hay[0]);
                t--;
                continue;
            }
            else if(n == 2){
                if(hay[0] == hay[1]){
                    out.println(hay[1]);
                }
                else{
                    out.println(-1);
                }
                t--;
                continue;
            }
            ArrayList<Integer> ans = new ArrayList<>();
            for(int i =0; i <n-2; i++){
                if(hay[i] == hay[i+1]){
                    if(!ans.contains(hay[i])){
                        ans.add(hay[i]);
                    }
                }
                else if(hay[i] == hay[i+2]){
                    if(!ans.contains(hay[i])){
                        ans.add(hay[i]);
                    }
                }
                else if(hay[i+2] == hay[i+1]){
                    if(!ans.contains(hay[i+1])){
                        ans.add(hay[i+1]);
                    }
                }
            }
            Collections.sort(ans);
            for(int i =0 ; i < ans.size(); i++){
                out.print(ans.get(i));
                if(i != ans.size()-1){
                    out.print(" ");
                }
            }
            if(ans.size() == 0){
                out.print(-1);
            }
            out.println();
            t--;
        }
        out.close();
        scan.close();
    }
}
