package USACOGuide;
import java.util.*;
public class TheyAreEverywhere {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.next();
        HashSet<Character> types = new HashSet<>();
		for (int i = 0; i < n; i++) { 
            types.add(s.charAt(i)); 
        }
        int ans = Integer.MAX_VALUE;
        int closestLeft = 0;
        HashMap<Character, Integer> caught = new HashMap<>();
        for (int left = 0; left < n; left++) {
			char c = s.charAt(left);
			caught.put(c,caught.getOrDefault(c, 0) + 1);
			while (closestLeft + 1 <= left && caught.getOrDefault(s.charAt(closestLeft), 0) > 1) {
				caught.put(s.charAt(closestLeft),caught.get(s.charAt(closestLeft)) - 1);
				closestLeft++;
			}
			if (caught.size() == types.size()) {
				ans =  Math.min(ans, left - closestLeft + 1);
			}
		}
        System.out.println(ans);
        scan.close();
    }
}