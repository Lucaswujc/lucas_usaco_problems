package USACOSilver;

import java.io.*;
import java.util.*;

public class FamilyTree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("family.in"));
		PrintWriter out = new PrintWriter("family.out");

		StringTokenizer st = new StringTokenizer(in.readLine());
		int relNum = Integer.parseInt(st.nextToken());
		String cow1 = st.nextToken();
		String cow2 = st.nextToken();
		String[][] relations = new String[relNum][2];
		for (int i = 0; i < relNum; i++) { 
            st = new StringTokenizer(in.readLine());
            relations[i][0] = st.nextToken(); 
            relations[i][1] = st.nextToken();
        }
		in.close();

		int dist1 = 0;
		int dist2 = 0;
		String comAnc = cow1;
		while (comAnc != null) {
			if (getDist(comAnc, cow2, relations) != -1) {
				dist2 = getDist(comAnc, cow2, relations);
				break;
			}
			comAnc = getMom(comAnc, relations);
			dist1++;
		}

		if (comAnc == null) {
			out.println("NOT RELATED");
		}
		else if (dist1 > 1 && dist2 > 1) {
			out.println("COUSINS");
		}
		else if (dist1 == 1 && dist2 == 1) {
			out.println("SIBLINGS");
		}
		else if (dist1 == 0 || dist2 == 0) {
			boolean xIsAncestor = dist1 == 0;
			out.print(String.format("%s is the ", comAnc));
			for (int i = 0; i < (xIsAncestor ? dist2 : dist1) - 2; i++) {
				out.print("great-");
			}
			if ((xIsAncestor ? dist2 : dist1) > 1) { out.print("grand-"); }
			out.println(String.format("mother of %s", xIsAncestor ? cow2 : cow1));
		}
		else {
			boolean auntIsX = dist1 == 1;
			out.print(String.format("%s is the ", auntIsX ? cow1 : cow2));
			for (int i = 0; i < (auntIsX ? dist2 : dist1) - 2; i++) {
				out.print("great-");
			}
			out.println(String.format("aunt of %s", auntIsX ? cow2 : cow1));
		}
		out.close();
	}
	private static String getMom(String child, String[][] relations) {
		for (String[] pair : relations) {
			if (child.equals(pair[1])) { return pair[0]; }
		}
		return null;
	}

	private static int getDist(String start, String end, String[][] relations) {
		int dist = 0;
		while (end != null) {
			if (end.equals(start)) { return dist; }
			dist++;
			end = getMom(end, relations);
		}
		return -1;
	}
}