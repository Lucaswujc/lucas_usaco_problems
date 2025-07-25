package USACOGuide;

import java.io.*;
import java.util.*;
public class TasksAndDeadlines {
   static class Task implements Comparable<Task>{
        int time, deadline;
        public Task(int time, int deadline){
            this.time = time;
            this.deadline = deadline;
        }
        public int compareTo(Task t) {
            return Integer.compare(this.time, t.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[n];
        for(int i = 0; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            tasks[i] = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(tasks);
        long time = 0;
        long ans = 0;
        for(int i = 0; i < n; i++){
            time += tasks[i].time;
            ans += tasks[i].deadline - (time);
            
        }
        System.out.println(ans);
    }

}