import java.util.*;
import java.lang.Math.*;
import java.util.Arays.*;
import java.io.*;
class cows {
    int b;
    int e;
    public cows(int Bessie, int Elsie) {
        this.b = Bessie;
        this.e = Elsie;
    }
}

public class USACOProb1 {
    public static void main(String args[]) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        String s = st.nextToken();
        int possibilities =0;
        int baseexcite = 0;
        int bessiecount = 0;
        int elsiecount = 0;
        for(int i = 0; i < s.length(); i++){
            if(i != n-1){
                if((s.charAt(i) == 'B' && s.charAt(i+1) == 'B') || (s.charAt(i) == 'E' && s.charAt(i+1) == 'E')){
                    baseexcite += 1;
                }
            }
            if(s.charAt(i) != 'F'){
                continue;
            }
            if(i == 0){
                if(s.charAt(i+1) == 'E'){
                    cows excite = new cows(0,1);
                    excitement.add(excite);
                }
                if(s.charAt(i+1) == 'B'){
                    Bessiec
                }
            }
            else if(i == s.length()-1){
                if(s.charAt(i-1) == 'E'){
                    cows excite = new cows(0,1);
                    excitement.add(excite);
                }
                if(s.charAt(i-1) == 'B'){
                    cows excite = new cows(1,0);
                    excitement.add(excite);
                }
            }
            else{
                if((s.charAt(i-1) == 'B' && s.charAt(i+1) == 'E') || (s.charAt(i-1) == 'E' && s.charAt(i+1) == 'B')){
                    baseexcite += 1;
                }
                if((s.charAt(i-1) == 'B' && s.charAt(i+1) == 'B')){
                    cows excite = new cows(2,0);
                    excitement.add(excite);
                }
                if((s.charAt(i-1) == 'E' && s.charAt(i+1) == 'E')){
                    cows excite = new cows(0,2);
                    excitement.add(excite);
                }
            }
        }

    }
}