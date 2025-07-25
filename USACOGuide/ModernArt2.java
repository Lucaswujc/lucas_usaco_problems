package USACOGuide;

import java.io.*;
import java.util.*;

public class ModernArt2 {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new FileWriter("art2.out"));
		int N = Integer.parseInt(in.readLine());
		int[] start = new int[N + 1];
		int[] end = new int[N + 1];
		Arrays.fill(start, Integer.MAX_VALUE);
		Arrays.fill(end, Integer.MIN_VALUE);

		int[] color = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			color[i] = Integer.parseInt(in.readLine());
		}

		for (int i = 1; i <= N; i++) {
			// Update the first and last appearance of the color.
			start[color[i]] = Integer.min(start[color[i]], i);
			end[color[i]] = Integer.max(end[color[i]], i);
		}

		start[0] = 0;
		end[0] = N + 1;
		int ans = 0;
		// The contents of the stack are the colors of the currently open
		// segments.
		Stack<Integer> stack = new Stack<>();
		for (int idx = 0; idx <= N; idx++) {
			int curColor = color[idx];

			// Process the start point of the color segment.
			if (idx == start[curColor]) {
				stack.push(curColor);
				ans = Integer.max(ans, stack.size());
			}

			/*
			 * Invalid because there are 2 partially intersecting segments.
			 * For instance if we have a finished painting of 1 1 2 1 2,
			 * it is impossible if we can only paint 1 once.
			 */
			if (stack.get(stack.size() - 1) != curColor) {
				out.println(-1);
				out.close();
				System.exit(0);
			}

			// Remove the color from the stack since the segment has closed.
			if (idx == end[curColor]) {
				stack.pop();
			}
		}

		// Subtract the color '0' segment.
		out.println(ans - 1);
		out.close();
	}

	static class FastIO extends PrintWriter {
		private InputStream stream;
		private byte[] buf = new byte[1 << 16];
		private int curChar, numChars;

		// standard input
		public FastIO() {
			this(System.in, System.out);
		}

		public FastIO(InputStream i, OutputStream o) {
			super(o);
			stream = i;
		}

		// file input
		public FastIO(String i, String o) throws IOException {
			super(new FileWriter(o));
			stream = new FileInputStream(i);
		}

		// throws InputMismatchException() if previously detected end of file
		private int nextByte() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars == -1)
					return -1; // end of file
			}
			return buf[curChar++];
		}
	}
}
