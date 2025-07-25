package USACOSilver;
import java.util.*;
public class Moorbles {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt(), m = scan.nextInt(), k = scan.nextInt();
            ArrayList<Integer>[] even = new ArrayList[m]; 
            ArrayList<Integer>[] odd = new ArrayList[m]; 
            for (int i = 0; i < m; i++) { 
                even[i] = new ArrayList<Integer>(); 
                odd[i] = new ArrayList<Integer>(); 
            }
            for(int i = 0; i < m; i++){
                for(int j = 0; j < k; j++){
                    int a = scan.nextInt();
                    if(a % 2 == 0){
                        even[i].add(a);
                    }
                    else{
                        odd[i].add(a);
                    }
                }
                if(!(even[i].size() == 0)){
                    Collections.sort(even[i]);
                }
                if(!(odd[i].size() == 0)){
                    Collections.sort(odd[i]);
                }
            }
            int curr = n;
            int pos = 0;
            boolean reverse = false;
            boolean work = true;
            ArrayList<String> moves = new ArrayList<>();
            ArrayList<Integer> moveDist = new ArrayList<>();
            while(pos <= n-1 && pos > -1){
                if(!reverse){
                    if(even[pos].size() == 0){
                        curr = curr - odd[pos].get(odd[pos].size()-1);
                        moves.add("Even");
                        moveDist.add(-1*odd[pos].get(odd[pos].size()-1));
                        if(curr < 0){
                            curr = curr + odd[pos].get(0);
                            moves.remove(pos);
                            moves.add("Odd");
                            moveDist.remove(pos);
                            moveDist.add(odd[pos].get(0));
                        }
                    }
                    else if(odd[pos].size() == 0){
                        curr = curr + even[pos].get(0);
                        moves.add("Even");
                        moveDist.add(even[pos].get(0));
                    }
                    else{
                        curr = curr - even[pos].get(even[pos].size()-1);
                        moves.add("Even");
                        moveDist.add(-1*even[pos].get(even[pos].size()-1));
                        if(curr < 0){
                            curr = curr + even[pos].get(even[pos].size()-1);
                            reverse = true;
                            pos--;
                            moves.remove(pos);
                            moveDist.remove(pos);
                        }
                    }
                    pos++;
                }
                else{
                    if(pos < 0){
                        work = false;
                        break;
                    }
                    if(optimal(even[pos], odd[pos], moves,moveDist, pos)){
                        pos--;
                        continue;
                    }
                    else{

                    }   
                }
            }
            t--;
        }
        scan.close();
    }
    static boolean optimal(ArrayList<Integer> even, ArrayList<Integer> odd, ArrayList<String> moves, ArrayList<Integer> moveDist, int pos){
        if(moves.get(pos).equals("Even")){
            
        }

        return true;
    }
}