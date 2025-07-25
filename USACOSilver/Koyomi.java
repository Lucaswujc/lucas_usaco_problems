package USACOSilver;
import java.util.*;
public class Koyomi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.next();
        int q = scan.nextInt();
        for(int i = 0; i < q; i++){
            int repaint  = scan.nextInt();
            char color = scan.next().charAt(0);
            int l = 0;
            int r = 0;
            int koyomity = 0;
            while (l < n && r < n){
                while(r < s.length()){
                    if(s.charAt(r)!=color){
                        if(repaint == 0){
                            break;
                        }
                        repaint--;
                    }
                    r++;
                }
                koyomity = Math.max(koyomity, r-1);
                while(s.charAt(l)!=color && l < s.length()){
                    l++;
                    repaint++;
                }
            }
            System.out.println(koyomity);
        }
        
        scan.close();
    }
}