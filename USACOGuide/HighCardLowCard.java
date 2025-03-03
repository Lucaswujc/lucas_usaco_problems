package USACOGuide;
import java.io.*;
import java.util.*;
public class HighCardLowCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean[] elsieOwns = new boolean[2 * n];
        ArrayList<Integer> front = new ArrayList<>();
        ArrayList<Integer> back = new ArrayList<>();
        ArrayList<Integer> bessie = new ArrayList<>();
		for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			elsieOwns[a-1] = true;
			if (i < n / 2){ 
                front.add(a); 
            }
			else{
                back.add(a);
            }
		}
        for(int i = 0; i < 2*n; i++){
            if(!elsieOwns[i]){
                bessie.add(i+1);
            }
        }
        Collections.sort(front);
        Collections.sort(back);
        int idx = bessie.size()-1;
        int score = 0;
        for(int i = front.size()-1; i >= 0; i--){
            if(front.get(i) < bessie.get(idx)){
                score++;
                idx--;
            }
        }
        idx = 0;
        for(int i = 0; i < back.size(); i++){
            if(bessie.get(idx) < back.get(i)){
                score++;
                idx++;
            }
        }
        out.println(score);
        out.close();
        br.close();
    }
}