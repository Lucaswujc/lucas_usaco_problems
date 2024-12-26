import java.util.*;
public class TargetPractice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int c = scan.nextInt();
        ArrayList<Integer> targets = new ArrayList<>();
        for(int i = 0; i < t; i++){
            targets.add(scan.nextInt());
        }
        char[] moves = new char[c];
        String s = scan.next();
        for(int i = 0; i < c; i++){
            moves[i] = s.charAt(i);
        }
        ArrayList<Integer> copy = deepcopy(targets);
        int ans = simulate(copy, moves);
        for(int i = 0; i < c; i++){
            char orig = moves[i];
            copy = deepcopy(targets);
            moves[i] = 'L';
            ans = Math.max(simulate(copy, moves), ans);
            copy = deepcopy(targets);
            moves[i] = 'R';
            ans = Math.max(simulate(copy, moves), ans);
            copy = deepcopy(targets);
            moves[i] = 'F';
            ans = Math.max(simulate(copy, moves), ans);
            moves[i] = orig;
        }
        System.out.println(ans);
        scan.close();
    }
    static ArrayList<Integer> deepcopy(ArrayList<Integer> orig){
        ArrayList<Integer> copy = new ArrayList<>();
        for(int element: orig){
            copy.add(element);
        }
        return copy;
    }
    static int simulate(ArrayList<Integer> targets, char[] moves){
        int ans = 0;
        int pos = 0;
        for(int i = 0; i < moves.length; i++){
            if(moves[i] == 'L'){
                pos--;
            }
            else if(moves[i] == 'R'){
                pos++;
            }
            else{
                if(targets.contains(pos)){
                    targets.remove(targets.indexOf(pos));
                    ans++;
                }
            }
        }
        return ans;
    }
}