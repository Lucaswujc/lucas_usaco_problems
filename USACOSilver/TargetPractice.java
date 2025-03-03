package USACOSilver;
import java.util.*;
public class TargetPractice {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int c = scan.nextInt();
        boolean[] targets = new boolean[2*c+2];
        for (int i = 0; i < t; i++){
            targets[scan.nextInt()+c] = true;
        }
        char[] command = scan.next().toCharArray();
        HashMap<Integer, Integer> prefix = new HashMap<>();
        HashMap<Integer,Integer> prefixPos = new HashMap<>();
        int pos = c;
        for (int i = 0; i < c; i++){
            if (command[i] == 'F'){
                if (targets[pos] && !prefix.containsKey(pos)){
                    prefix.put(pos, i);
                    prefixPos.put(i,pos);
                }
            }
            pos += (command[i] == 'L') ? -1 : 0;
            pos += (command[i] == 'R') ? 1 : 0;
        }
        int ans = prefix.size();
        HashSet[] suffix = new HashSet[5];
        for (int i = 0; i < 5; i++){
            suffix[i] = new HashSet<Integer>();
        }
        HashSet[] toAdd = new HashSet[5];
        for (int i = 0; i < 5; i++) {
            toAdd[i] = new HashSet<Integer>();
        }
        for (int i = c-1; i >= 0; i--){
            if (prefixPos.containsKey(i)){
                prefix.remove(prefixPos.get(i));
                prefixPos.remove(i);
                for (int j = 0; j < 5; j++){
                    if (toAdd[j].contains(pos)){
                        suffix[j].add(pos);
                        toAdd[j].remove(pos);
                    }
                }
            }
            pos += (command[i] == 'L') ? 1 : 0;
            pos += (command[i] == 'R') ? -1 : 0;
            if( command[i] == 'L'){
                int add = targets[pos] && !prefix.containsKey(pos) && !suffix[3].contains(pos)? 1 : 0;
                ans = Math.max(ans, prefixPos.size()+add+suffix[3].size());
                ans = Math.max(ans, prefixPos.size()+suffix[4].size());
            }
            else if(command[i] == 'R'){
                int add = targets[pos] && !prefix.containsKey(pos) && !suffix[1].contains(pos) ? 1 : 0;
                ans = Math.max(ans, prefixPos.size()+add+suffix[1].size());
                ans = Math.max(ans, prefixPos.size()+suffix[0].size());
            }
            else if(command[i] == 'F'){
                ans = Math.max(ans, prefixPos.size()+suffix[1].size());
                ans = Math.max(ans, prefixPos.size()+suffix[3].size());
            }
            if (command[i] == 'F') {
                for (int j = pos - 2; j <= pos + 2; j++) {
                    if (j < 0 || j >= targets.length) continue;
                    if (targets[j]){
                        if (prefix.containsKey(j)) {
                            toAdd[j - pos + 2].add(j);
                        }
                        else{
                            suffix[j-pos+2].add(j);
                        }
                    }
                }
            }
 
        }
        System.out.println(ans);
        scan.close();
    }
}