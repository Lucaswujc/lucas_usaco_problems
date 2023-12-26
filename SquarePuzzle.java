import java.util.*;
import java.lang.Math.*;
import java.util.Arrays.*;
class result {
    String parent; 
    int transform; 

    public result(String parent, int transform) {
        this.parent = parent;
        this.transform = transform;
    }

}
public class SquarePuzzle {
    static int[][] transformA(int[][] arr){
        int[][] arr1 = new int[2][4];
        arr1[0] = arr[1];
        arr1[1] = arr[0];
        return arr1;
    }
    static int[][] transformB(int[][] arr){
        int[][] arr1 = new int[2][4];
        for(int i =0; i < 2;i++){
            for(int j =0; j < 4; j++){
                arr1[i][j] = arr[i][j];
            }
        }
        for(int i = 0; i < 2; i++){
            int temp = arr1[i][1];
            arr1[i][1] = arr1[i][0];
            int temp2 = arr1[i][2];
            arr1[i][2] = temp;
            temp = arr1[i][3];
            arr1[i][3] = temp2;
            arr1[i][0] = temp;
        }
        return arr1;
    }
    static int[][] transformC(int[][] arr){
        int[][] arr1 = new int[2][4];
        for(int i =0; i < 2;i++){
            for(int j =0; j < 4; j++){
                arr1[i][j] = arr[i][j];
            }
        }
        int temp = arr1[0][1];
        arr1[0][1] = arr1[1][1];
        int temp2 = arr1[0][2];
        arr1[0][2] = temp;
        temp = arr[1][2];
        arr1[1][2] = temp2;
        arr1[1][1]=temp;
        return arr1;
    }
    static String arrayToString(int[][] arr){
        String s = "";
        for(int i = 0; i < 2; i++){
            for(int j = 0; j <4 ; j++){
                s += Integer.toString(arr[i][j]);
            }
        }
        return s;
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int[][] res = new int[2][4];
        for(int j = 0; j < 4; j++){
            res[0][j] = scan.nextInt();
        }
        for(int j = 3; j > 0; j--){
            res[1][j] = scan.nextInt();
        }
        ArrayList<int[][]>  tracker = new ArrayList<>();
        int[][] start = new int[][] {{1,2,3,4},{8,7,6,5}};
        HashMap<String, result> visited = new HashMap<>();
        String s = arrayToString(start);
        result a= new result(null, 0);
        visited.put(s, a);
        tracker.add(start);
        while(!(tracker.isEmpty())){
            int[][] parent = tracker.get(0);
            tracker.remove(0);
            String p = arrayToString(parent);
            int[][] child = transformA(parent);
            String c = arrayToString(child);
            if(visited.get(c) == null){
                tracker.add(child);
                result v = new result(c, 1);
                visited.put(p, v);
            }
            child = transformB(parent);
            c = arrayToString(child);
            if(visited.get(c) == null){
                tracker.add(child);
                result v = new result(c, 1);
                visited.put(p, v);
            }
            child = transformC(parent);
            c = arrayToString(child);
            if(visited.get(c) == null){
                tracker.add(child);
                result v = new result(c, 1);
                visited.put(p, v);
            }
        }
        if(visited.get(arrayToString(res)) != null){
            System.out.println("Found");
        }
    }
}