package USACO_bronzeclass_problems;
import java.util.*;

public class WorkingTime {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();
        int[] tasks = new int[n];
        int[] questions = new int[q];
        for(int i = 0 ; i < n; i++){
            tasks[i] = scan.nextInt();
        }
        for(int i = 0 ; i < q; i++){
            questions[i] = scan.nextInt();
        }
        int total = 0;
        for(int i = 0; i < n; i++){
            total = total + tasks[i];
        }
        int[] time = new int[total];
        int[] answers = new int[q];
        int track = 0;
        for(int i = 0; i < n; i++){
            int x = tasks[i];
            for(int j = 0;j <x; j++){
                time[track] = i+1;
                track++;
            }
        }
        for(int i = 0; i < q; i++){
            answers[i] = time[questions[i] - 1];
            System.out.println(answers[i]);
        }
        scan.close();
    }
}