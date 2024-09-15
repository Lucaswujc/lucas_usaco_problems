
import java.util.*;
public class RoundDance {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0){
            int n = scan.nextInt();
            List<List<Integer>> adj = new ArrayList<>();
	        int[] visited = new int[n];
            for (int i = 0; i < n; i++) { 
                adj.add(new ArrayList<>()); 
            }
            for(int i = 0; i < n; i++){
                int b =scan.nextInt()-1;
                adj.get(i).add(b);

            }
            Arrays.fill(visited, -1);
            int count =0;
			for (int i = 0; i < n; i++) {
				if (visited[i] == -1) {
					Stack<Integer> stack = new Stack<>();
					stack.push(i);
					while (!stack.isEmpty()) {
						int curr = stack.pop();
						if (visited[curr] != -1) continue;
						visited[curr] = count;

						for (int neighbor : adj.get(curr)) { 
                            stack.push(neighbor); 
                        }
					}
					count++;
				}
			}
            List<List<Integer>> components = new ArrayList<>();
			for (int i = 0; i < count; i++) {
				components.add(new ArrayList<>());
			}

			for (int i = 0; i < n; i++) { 
                components.get(visited[i]).add(i); 
            }
            int mincount = 0;
            for(int i =0; i < count; i++){
                int neighborcount= 0;
                for(int node : components.get(i)){
                    if(adj.get(node).size() == 1){
                        neighborcount++;
                    }
                }
                if(neighborcount == 1){
                    mincount++;
                }
            }
            System.out.println(Math.max(mincount-1,1) + " " + count);
            t--;
        }
        /**
        * [2,1,4,3,6,5]
        *  constract graph 1-2-1  3-4-3 5-6-5
        * it is possible to constrcut a single graph 
        * because vertex 2 can go vertex 3 , why ? because v2 has one neighbour 
        * vertex 1 can connec tto verx 5 because vertex1 has one neighbour 
        * with such observatin, 
        * we need to count the num of vertices which has only 1 ne3ighbour within 
        * a single component , 
        * if such count >=2 , then the componet itself can be connected to two 
        * oterh components and form a new connected component, 
        * the componet which has onely 1 vertext with 1 neighbour, the compoent itself 
        * will belong to a single newly connected component
        * each newly connected component can have two such single ones and as many as possible 
        * componets with >=2 such vertices. 
        * 
        * A B C D E F G
        * A --- 0 
        * B --- 2
        * C -- 3 
        * D ---1 
        * E -- 1
        * F --2
        * G -- 1
        * A cannot connect any body else 
        * D --B --C --F --E
        * G
        *
        * 
        * 
        */  
        scan.close();
    }
}
