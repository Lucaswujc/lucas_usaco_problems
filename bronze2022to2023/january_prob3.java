import java.io.*;
import java.util.*;

public class january_prob3 {
  public static void main (String [] args) throws IOException {
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("2023january_prob3.out")));
    StringTokenizer st = new StringTokenizer(scan.readLine());
    int t = Integer.parseInt(st.nextToken());
    while(t > 0){
        st = new StringTokenizer(scan.readLine());
        String s = st.nextToken();
        int olocate = -1;
        if(s.length() <= 3){
            olocate=1;
        }
        else{
            Boolean ok = false;
            for(int i = 0; i < s.length()-3;i++){
                if(((i < s.length()-3) && s.charAt(i+2) =='O') && (s.charAt(i) == 'M' && s.charAt(i+1) == 'O')){
                    olocate = i + 1;
                    ok = true;
                    break;
                }
            }
            if(!(ok)){
                ok = false;
                for(int i = 0; i < s.length()-2;i++){
                    if(((i < s.length()-2 )) && (s.charAt(i) == 'M' && s.charAt(i+1) == 'O')){
                        olocate = i + 1;
                        ok =true;
                        break;
                    }
                    if((i < s.length()-2 && i != 0) && (s.charAt(i) == 'O' && s.charAt(i+1) == 'O')){
                        olocate = i;
                        ok = true;
                        break;
                    }
                }
                if(!(ok)){
                    for(int i = 0; i < s.length() -1;i++){
                        if((s.charAt(i) == 'O' && i != 0)){
                            olocate = i;
                        }
                    }                
                }
            }
        }
        if(olocate == -1 || s.length() <3 || s.charAt(olocate) == 'M'){
            System.out.println(-1);
        }
        else{
            int op = 0;
            op = op + (olocate -1);
            op = op + (s.length() - (olocate + 1)-1);
            if(s.charAt(olocate -1) == 'O'){
                op++;
            }
            if(s.charAt(olocate+1) == 'M'){
                op++;
            }
            System.out.println(op);
        }
        t--;
    }
    out.close();                                  
  }
}

