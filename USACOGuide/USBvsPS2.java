package USACOGuide;
import java.util.*;
public class USBvsPS2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long usbn = scan.nextLong();
        long ps2n = scan.nextLong();
        long bothn = scan.nextLong();
        long n  = scan.nextLong();
        TreeMap<Long, String> mice = new TreeMap<>();
        for(long i = 0; i < n; i++){
            long p = scan.nextLong();
            String s = scan.next();
            mice.put(p, s);
        }
        long sum = 0;
        long ans = 0;
        for(Map.Entry<Long, String> m : mice.entrySet()){
            Boolean usb = m.getValue().equals("USB") ? true : false;
            if(usb){
                if(usbn > 0){
                    usbn--;
                    ans++;
                    sum += m.getKey();
                }
                else if(bothn > 0){
                    bothn--;
                    ans++;
                    sum += m.getKey();
                }
            }
            else{
                if(ps2n > 0){
                    ps2n--;
                    ans++;
                    sum += m.getKey();
                }
                else if(bothn > 0){
                    bothn--;
                    ans++;
                    sum += m.getKey();
                }
            }
        }
        System.out.println(ans + " " + sum);
        scan.close();
    }
}