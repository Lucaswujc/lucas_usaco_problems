import java.util.*;

public class BarnTree {
    static class move {
        int source, destination;
        long bales;

        move(int source, int destination, long bales) {
            this.source = source;
            this.destination = destination;
            this.bales = bales;
        }
    }

    static long[] hay;
    static long[] subtrees;
    static List<List<Integer>> adj;
    static List<move> moves;
    static long avg;

    public static void dfs1(int node, int parent) {
        subtrees[node] =  hay[node] - avg;
        for (int child : adj.get(node)) {
            if (child != parent) {
                dfs1(child, node);
                subtrees[node] = subtrees[node] + subtrees[child];
            }
        }
    }

    public static void dfs2(int node, int parent) {
        for (int child : adj.get(node)) {
            if (child != parent) {
                if (subtrees[child] >= 0) {
                    dfs2(child, node);
                }
                if (subtrees[child] > 0) {
                    moves.add(new move(child+1, node +1, subtrees[child]));
                }
            }
        }
        for (int child : adj.get(node)) {
            if (child != parent && subtrees[child] < 0) {
                moves.add(new move(node+1, child+1, - subtrees[child]));
                dfs2(child, node);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        hay = new long[n];;
        subtrees = new long[n];
        adj = new ArrayList<>();
        moves = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        avg = 0;
        for (int i = 0; i < n; i++) {
            hay[i] = scan.nextLong();
            avg += hay[i];
        }

        avg /= n;

        for (int i = 0; i < n - 1; i++) {
            int a = scan.nextInt() - 1;
            int b = scan.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        dfs1(0, -1);
        dfs2(0, -1);

        System.out.println(moves.size());
        for (move move : moves) {
            System.out.println(move.source + " " + move.destination + " " + move.bales);
        }

        scan.close();
    }
}
