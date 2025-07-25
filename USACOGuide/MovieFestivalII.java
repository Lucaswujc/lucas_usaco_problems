package USACOGuide;

import java.io.*;
import java.util.*;

public class MovieFestivalII {
	static class Movie implements Comparable<Movie> {
		int start, end;

		public Movie(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Movie m) {
			return Integer.compare(this.end, m.end);
		}

	}

	public static void main(String[] args) {
		FastIO io = new FastIO();
		int n = io.nextInt();
		int k = io.nextInt();
		Movie[] movies = new Movie[n];
		for (int i = 0; i < n; i++) {
			movies[i] = (new Movie(io.nextInt(), io.nextInt()));
		}
		Arrays.sort(movies);
		int maxMovies = 0;
		TreeMap<Integer, Integer> endTimes = new TreeMap<>();
		endTimes.put(0, k);

		for (Movie movie : movies) {
			Integer lower = endTimes.floorKey(movie.start);
			if (lower != null) {
				maxMovies++;
				int lowerValue = endTimes.get(lower);
				if (lowerValue - 1 == 0) {
					endTimes.remove(lower);
				} else {
					endTimes.put(lower, lowerValue - 1);
				}
				endTimes.put(movie.end, endTimes.getOrDefault(movie.end, 0) + 1);
			}
		}
		System.out.println(maxMovies);
		io.close();
	}

	// FastIO class from usaco guide
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

		// to read in entire lines, replace c <= ' '
		// with a function that checks whether c is a line break
		public String next() {
			int c;
			do {
				c = nextByte();
			} while (c <= ' ');
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = nextByte();
			} while (c > ' ');
			return res.toString();
		}

		public int nextInt() { // nextLong() would be implemented similarly
			int c;
			do {
				c = nextByte();
			} while (c <= ' ');
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = nextByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = 10 * res + c - '0';
				c = nextByte();
			} while (c > ' ');
			return res * sgn;
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}