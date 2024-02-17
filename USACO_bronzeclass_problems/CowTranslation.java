package USACO_bronzeclass_problems;
import java.util.*;
public class CowTranslation {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        scan.nextLine();
        String[] translate = new String[m];
        for(int  i =0; i < m; i++){
            translate[i] = scan.nextLine();
        }
        int count = 0;
        scan.close();
        for(int i = 0; i < n; i++){
            String s = scan.nextLine();
            for(int j = 0; j < m; j++){
                if(translate[j].contains(s)){
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}