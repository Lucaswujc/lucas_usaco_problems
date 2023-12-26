import java.util.*;
import java.lang.Math.*;
import java.util.Arrays.*;
public class program {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Stack<Integer> dirty = new Stack<Integer>();
        Stack<Integer> dry = new Stack<Integer>();
        Stack<Integer> clean = new Stack<Integer>();
        for(int i = n; i <= n; i++){
            dirty.push(i);
        }
        while(scan.hasNext()){
            int worker = scan.nextInt();
            int amount = scan.nextInt();
            if(worker == 1){
                while(!(dirty.isEmpty())){
                    amount--;
                    if(amount < 0){
                        break;
                    }
                    int dish = dirty.peek();
                    dirty.pop();
                    dry.push(dish);
                }
            }
            else{
                while(!(dry.isEmpty())){
                    amount--;
                    if(amount < 0){
                        break;
                    }
                    int dish = dry.peek();
                    dry.pop();
                    clean.push(dish);
                }
            }
        }
        while(!(clean.isEmpty())){
            System.out.println(clean.peek());
            clean.pop();
        }
    }
}