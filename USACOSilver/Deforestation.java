package USACOSilver;

/*Farmer John is expanding his farm! He has identified the perfect location in the Red-Black Forest, which consists of N
trees (1≤N≤105) on a number line, with the i-th tree at position xi
(−10^9≤xi≤10^9).

Environmental protection laws restrict which trees Farmer John can cut down to make space for his farm. There are K
restrictions (1≤K≤105) specifying that there must always be at least ti
trees (1≤ti≤N) in the line segment [li,ri], including the endpoints (−10^9≤li≤ri≤10^9)). 
It is guaranteed that the Red-Black Forest initially satisfies these restrictions.

Farmer John wants to make his farm as big as possible. Please help him compute the maximum number of trees he can cut down while still satisfying all the restrictions!

INPUT FORMAT (input arrives from the terminal / stdin):
Each input consists of T
(1≤T≤10) independent test cases. It is guaranteed that the sums of all N
and of all K within an input each do not exceed 3⋅10^5.

The first line of input contains T. Each test case is then formatted as follos:

The first line contains integers N and K.
The next line contains the N integers x1,…,xN.
Each of the next Klines contains three space-separated integers: li, riand ti.
OUTPUT FORMAT (print output to the terminal / stdout):
For each test case, output a single line with an integer denoting the maximum number of trees Farmer John can cut down.

SAMPLE INPUT:
3
7 1
8 4 10 1 2 6 7
2 9 3
7 2
8 4 10 1 2 6 7
2 9 3
1 10 1
7 2
8 4 10 1 2 6 7
2 9 3
1 10 4
SAMPLE OUTPUT:
4
4

3*/
import java.util.*;
public class Deforestation {
    //set up a union find to find overlap
    //learnt from gfg
    static int[] parent;
    static int find(int a) {
        //find root of the set that includes element a
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
    static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if (findA != findB) {
            parent[findA] = findB;
        }
    }
    static int[] tree;
    //fenwick Tree leart from geekdsforgeeks
    //best way to optimize and store the tree values
    static void update(int size, int index, int val) {
            while (index <= size) {
                tree[index] += val;
                index += index & (-index);
            }
        }
    //function to update
    static int getSum(int index) {
        int sum=  0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }
        return sum;
    }
    //gets sum
    static int prefixSum(int l, int r) {
        if (l > r) return 0;
        return getSum(r) - getSum(l - 1);
    }
    //gets sum over a range
    static int binLow(Tree[] trees, long a){
        //bin search for the loer bound of the restriction
        int lo = 0; 
        int hi = trees.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (trees[mid].pos < a){
                lo = mid + 1;
            }
            else{
                hi = mid;
            }
        }
        return lo;
    }
    static int binHigh(Tree[] trees, long a){
        //binary search for hier bound
        int lo = 0; 
        int hi = trees.length;
        while (lo < hi) {
            int mid = (hi + lo) / 2;
            if (trees[mid].pos <= a){
                lo = mid + 1;
                
            }
            else{
                hi = mid;
            }
        }
        return lo;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int test = scan.nextInt();
        while(test > 0){
            //input
            int n = scan.nextInt();
            int k = scan.nextInt();
            Tree[] trees = new Tree[n];
            for(int i =0; i < n ; i++){
                long pos = scan.nextInt();
                trees[i] = new Tree(pos, i);
            }
            Arrays.sort(trees);
            //sort
            //take in restrictions
            Restriction[] restrictions = new Restriction[k];
            for (int i = 0; i < k; i++) {
                long l = scan.nextInt();
                long r = scan.nextInt();
                int t = scan.nextInt();
                int left = binLow(trees, l) + 1;
                int right = binHigh(trees, r);
                //find left and right indices
                if (left > right){
                    continue;
                    //skip if l > r
                }
                //find number of trees able to remove
                int num = right - left + 1;
                int allowed = Math.max(0, num - t);
                restrictions[i] = (new Restriction(left, right, allowed));
            }
            Arrays.sort(restrictions);
            //set up fenwick tree
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                update(n, i, 1);
            }
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
            long ans = 0;
            //iterate through each restriction
            for (int i = 0; i < k; i++) {
                Restriction res = restrictions[i];
                int left = res.left;
                int right = res.right;
                int allowed = res.trees;
                //find num of curr trees
                int curr = prefixSum(left, right);
                if (curr > allowed) {
                    int removeN = curr - allowed;
                    while (removeN > 0) {
                        int pos = find(right);
                        if (pos < left) {
                            break;
                        }
                        //remove the cut at pos
                        update(n, pos, -1);
                        ans++;
                        //union this position to the left
                        union(pos, pos - 1);
                        removeN--;
                    }
                }
            }
            System.out.println(n - ans);
            test--;
        }
        scan.close();
    }
    static class Tree implements Comparable<Tree>{
        long pos;
        int index;
        Tree(long p , int i){
            this.pos = p;
            this.index = i;
        }
        public int compareTo(Deforestation.Tree t){
            return Long.compare(this.pos, t.pos);
        }

    }
    static class Restriction implements Comparable<Restriction>{
        int left;
        int right;
        int trees;
        Restriction(int l, int r, int t){
            this.left = l;
            this.right = r;
            this.trees = t;
        }
        public int compareTo(Deforestation.Restriction r){
            return Integer.compare(this.right, r.right);
        }
    }
}