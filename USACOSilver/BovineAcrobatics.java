package USACOSilver;
import java.util.*;
public class BovineAcrobatics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m  = scan.nextInt();
        int k = scan.nextInt();
        Cow[] cowGroups = new Cow[n];
        for (int j = 0; j < n; j++) {
            int weight = scan.nextInt();
            int num = scan.nextInt();
            cowGroups[j] = new Cow(weight, num);
        }
        Arrays.sort(cowGroups);
 
        TreeSet<Cow> treeSet = new TreeSet<>();
        int towers = 0;
        long answer = 0;
        for (Cow group : cowGroups) {
            int num = group.num;
            while (num > 0) {
                Cow floor = treeSet.floor(new Cow(group.weight - k, 0));
                if (floor == null) {
                    break;
                }
                treeSet.remove(floor);
                if (floor.num <= num) {
                    num -= floor.num;
                }
                else {
                    treeSet.add(new Cow(floor.weight, floor.num - num));
                    num = 0;
                }
            }
            towers -= group.num - num;
            int add = Math.min(group.num, m - towers);
            towers += add;
            answer += add;
            treeSet.add(new Cow(group.weight, add));
        }
        System.out.println(answer);
        scan.close();
    }
 
    static class Cow implements Comparable<Cow>{
        final int weight;
        final int num;
 
        Cow(int weight, int num) {
            this.weight = weight;
            this.num = num;
        }

        @Override
        public int compareTo(Cow c) {
            return Integer.compare(this.weight, c.weight);
        }
    }
}