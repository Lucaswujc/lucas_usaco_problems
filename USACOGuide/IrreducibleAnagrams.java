package USACOGuide;
import java.util.*;
public class IrreducibleAnagrams {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s  = scan.next();
        int q =scan.nextInt();
        while(q > 0){
            int l = scan.nextInt() - 1;
            int r = scan.nextInt() - 1;
            if(l != r){
                String s1 = s.substring(l, r+1);
                Boolean reducible = true;
                if(s1.charAt(0) != s1.charAt(s1.length()-1)){
                    reducible = false;
                }
                Set<Integer> chars = new HashSet<>();
                for(int i = 0; i < s1.length(); i++){
                    chars.add((int) s1.charAt(i));
                    if(chars.size() == 3){
                        reducible = false;
                        break;
                    }
                }
                if(reducible){
                    System.out.println("NO");
                }
                else{
                    System.out.println("YES");
                }
            }
            else{
                System.out.println("YES");
            }
            q--;
        }
        scan.close();
    }
}