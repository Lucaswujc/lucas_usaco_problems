package USACOGuide;
import java.util.*;
public class WilliamAndRobot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < n ;i++){
            int a = scan.nextInt();
            numbers.add(a);
        }
        int sum = 0;
        for(int i = 0; i < n/2 ;i++){
            
            if(numbers.get(0) > numbers.get(1)){
              sum += numbers.get(0);
              numbers.remove(0);
              numbers.remove(0);  
            }
            else{
                int max = 0;
                int idx = 0;
                for(int j = 0; j < numbers.size(); j++){
                    if(numbers.get(j) > max){
                        max = numbers.get(j);
                        idx = j;
                    }
                }
                numbers.remove(idx);
                sum += max;
                numbers.remove(0);
            }
            
        }
        System.out.println(sum);
        scan.close();
    }
}