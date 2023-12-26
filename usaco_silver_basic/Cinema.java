package usaco_silver_basic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The Simpsons have constructed a cinema with R rows (4 <= R <= 500) of seats in the shape of a
 * rectangle with an odd width W (7 <= W <= 501). The distance between two adjacent seats in the
 * same row is equal to the distance between two adjacent seats in adjacent rows that are in front
 * of/behind each other.
 * 
 * The Simpsons want to sell tickets over the internet and have each ticket automatically assigned
 * to a seat.
 * 
 * Because ticket buyers are always looking for the best seat, each seat has its own 'priority'
 * (even if some seats might appear to the layman to have the same priority). The best priority is
 * for the middle seat of the first row (closest to the stage), which is located at 1,(W+1)/2. The
 * seats closest to it (euclidean distance!) have the next best set of values. The seats immediately
 * adjacent to you have slightly lower values, and so on.
 * 
 * All seats in the first row with the same distance are better than all seats in the second row
 * with the same distance (and so on).
 * 
 * Because some seats are equally distant from the best seat, those in the same row closest to seat
 * number 1 (starting from the left-most seat when viewed from the stage) are given higher priority
 * (see diagram below).
 * 
 * The 'priority' of the seats is shown in this diagram of a small (7 columns x 4 rows) theater,
 * with #1 being the best:
 * 
 * Seat Number 1 2 3 4 5 6 7 -- -- -- -- -- -- -- Row 4 | 27 25 21 18 22 26 28 Row 3 | 23 14 12 9 13
 * 15 24 Row 2 | 19 10 5 4 6 11 20 Row 1 | 16 7 2 1 3 8 17 Front Write a program that prints the
 * seating priority chart for a supplied width and row count.
 * 
 * INPUT FORMAT
 * 
 * Line 1: Two space-separated integers: W and R
 * 
 * OUTPUT FORMAT
 * 
 * Lines 1..R: Line i contains W space-separated integers that are the seating priorities for row
 * R-i+1
 * 
 * SAMPLE INPUT
 * 
 * 7 4 SAMPLE OUTPUT
 * 
 * 27 25 21 18 22 26 28 
 * 23 14 12 9  13 15 24 
 * 19 10 5  4  6  11 20 
 * 16 7  2  1  3  8  17
 */
public class Cinema {
    public static void main(String[] argsv) {
        Scanner scan = new Scanner(System.in);
        int w = scan.nextInt();
        int r = scan.nextInt();
        Seat[] seats = new Seat[w * r ];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < w; j++) {
                Seat s = new Seat(i, j);
                s.setDistance(0, (w + 1) / 2 - 1);
                seats[i * w + j] = s;
            }
        Arrays.sort(seats);
        Seat[][] cinema = new Seat[r][w];
        for (int i = 0; i < seats.length ; i++) {
            Seat s = seats[i];
            s.setPriority(i + 1);
            cinema[s.row][s.col] = s;
        }
        for (int i = r - 1; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                System.out.print(String.format("%2d  ", cinema[i][j].getPriority()));
            }
            System.out.println();
        }

    }
}


class Seat implements Comparable<Seat> {
    int row;
    int col;
    private int distance;
    private int priority;

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setDistance(int orow, int ocol) {
        distance = (this.row - orow) * (this.row - orow) + (this.col - ocol) * (this.col - ocol);
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(Seat o) {
        Seat other = (Seat) o;
        if (this.distance != other.distance) {
            return this.distance - other.distance;
        }
        if (this.row != other.row) {
            return this.row - other.row;
        }
        return this.col - other.col;
    }

}
