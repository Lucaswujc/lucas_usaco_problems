package USACOGuide;
import java.util.*;
public class PreparingForMergeSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            arr.add(scan.nextInt());
        }
        int pos = 0;
        Boolean cont = true;
        int curr = 0;
        while(true){
            if(arr.size() ==0){
                break;
            }
            if(pos == arr.size() ){
                cont = false;
            }
            if(cont){
                if(arr.get(pos) < curr){
                    pos++;
                    continue;
                }
                System.out.print(arr.get(pos) + " ");
                curr = arr.get(pos);
                arr.remove(pos);

            }
            else{
                cont = true;
                pos = 0;
                curr = 0;
                System.out.println();
            }
        }
        scan.close();
    }
}