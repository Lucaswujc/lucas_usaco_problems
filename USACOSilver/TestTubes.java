package USACOSilver;
import java.util.*;
public class TestTubes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            int p = scan.nextInt();
            int[] tower1 = new int[n];
            int[] tower2 = new int[n];
            ArrayList<Integer> compress1 = new ArrayList<>();
            ArrayList<Integer> compress2 = new ArrayList<>();
            int prev = 0;
            String s = scan.next();
            for(int i = 0; i < n; i++){
                tower1[i] = s.charAt(i) == '1' ?  1 : 2;
                if(tower1[i] != prev && i != 0){
                    compress1.add(prev == 1 ? 1 : 2);
                }
                prev = tower1[i];
            }
            compress1.add(tower1[n-1]);
            s = scan.next();
            for(int i = 0; i < n; i++){
                tower2[i] = s.charAt(i) == '1' ?  1 : 2;
                if(tower2[i] != prev && i != 0){
                    compress2.add(prev == 1 ? 1 : 2);
                }
                prev = tower2[i];
            }
            compress2.add(tower2[n-1]);
            if(p == 1){
                ArrayList<Integer> copy1 = copy(compress1);
                ArrayList<Integer> copy2 = copy(compress2);
                if(copy1.get(0) == 1){
                    copy1.remove(0);
                }
                if(copy2.get(0) == 2){
                    copy2.remove(0);
                }
                int size = copy1.size() + copy2.size();
                int ans = size == 1 ? 1 : (size + 1);
                if(compress1.get(0) == 2){
                    compress1.remove(0);
                }
                if(compress2.get(0) == 1){
                    compress2.remove(0);
                }
                size = compress1.size() + compress2.size();
                ans = size == 1 ? 1 : Math.min(ans, (size + 1));
                System.out.println("-------------------  \n" + String.valueOf(ans));
            }
            else if(p == 2){
                ArrayList<Integer> beaker = new ArrayList<>();
                ArrayList<String> moves = new ArrayList<>();
                while(compress2.size() > 0){
                    if(!(compress2.size() == 0)){
                        if(compress2.get(compress2.size()-1) == 2){
                            move(compress2, compress1);
                            moves.add("2 1");
                        }
                        else{
                            move(compress2, beaker);
                            moves.add("2 3");
                        }
                    }
                }
                while(compress1.size() > 0){
                    if(!(compress1.size() == 0)){
                        if(compress1.get(compress1.size()-1) == 2){
                            move(compress1, compress2);
                            moves.add("1 2");
                        }
                        else{
                            move(compress1, beaker);
                            moves.add("1 3");
                        }
                    }
                }
                move(beaker, compress1);
                moves.add("3 1");
                System.out.println("-------------------  \n" + moves.size());
                for(String element : moves){
                    System.out.println(element);
                }
            }
            else{
                ArrayList<Integer> beaker = new ArrayList<>();
                ArrayList<String> moves = new ArrayList<>();
                if(compress1.get(compress1.size()-1) == compress2.get(compress2.size()-1)){
                    if(compress1.size() > 1){
                        move(compress1, compress2);
                        moves.add("1 2");
                    }
                    else if(compress2.size() > 1){
                        move(compress2, compress1);
                        moves.add("2 1");
                    }
                }
                if (!(compress1.size() == 1 && compress2.size() == 1)){
                    if(compress1.size() > 1){
                        move(compress1, beaker);
                        moves.add("1 3");
                    }
                    else if(compress2.size() > 1){
                        move(compress2, beaker);
                        moves.add("2 3");
                    }
                }
                if(beaker.get(beaker.size()-1) != compress1.get(compress1.size()-1)){
                    while(compress1.size() > 1){
                        if(compress1.get(compress1.size()-1) == beaker.get(beaker.size()-1)){
                            move(compress1, beaker);
                            moves.add("1 3");
                        }
                        else{
                            move(compress1, compress2);
                            moves.add("1 2");
                        }
                    }
                    while(compress2.size() > 1){
                        if(compress2.get(compress1.size()-1) == beaker.get(beaker.size()-1)){
                            move(compress2, beaker);
                            moves.add("2 3");
                        }
                        else{
                            move(compress2, compress1);
                            moves.add("2 1");
                        }
                    }
                }
                else{
                    while(compress2.size() > 1){
                        if(compress2.get(compress1.size()-1) == beaker.get(beaker.size()-1)){
                            move(compress2, beaker);
                            moves.add("2 3");
                        }
                        else{
                            move(compress2, compress1);
                            moves.add("2 1");
                        }
                    }
                    while(compress1.size() > 1){
                        if(compress1.get(compress1.size()-1) == beaker.get(beaker.size()-1)){
                            move(compress1, beaker);
                            moves.add("1 3");
                        }
                        else{
                            move(compress1, compress2);
                            moves.add("1 2");
                        }
                    }
                    
                }   
            }
            t--;
        }
        scan.close();
    }
    static void move(ArrayList<Integer> source, ArrayList<Integer> dest){
        int val = source.get(source.size()-1);
        source.remove(source.size()-1);
        if(dest.size() != 0){
            if(dest.get(dest.size()-1) == val){
                return;
            }
            else{
                dest.add(val);
            }
        }
        else{
            dest.add(val);
        }
    }
    static ArrayList<Integer> copy (ArrayList<Integer> original){
        ArrayList<Integer> copy = new ArrayList<>();
        for(int element : original){
            copy.add(element);
        }
        return copy;
    }
}