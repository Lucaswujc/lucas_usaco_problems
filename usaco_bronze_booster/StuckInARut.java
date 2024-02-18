package usaco_bronze_booster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@formatter:off
/**
 * Stuck in a Rut [ Memory: 256 MB, CPU: 2 sec ]
 * 
 * Farmer John has recently expanded the size of his farm, so from the perspective of his COWs it is
 * effectively now infinite in size! The COWs think of the grazing area of the farm as an infinite
 * 2D grid of square "cells", each filled with delicious grass (think of each cell as a square in an
 * infinite chessboard). Each of Farmer John's 𝑁 COWs (1≤𝑁≤50 ) starts out in a different cell;
 * some start facing north, and some start facing east.
 * 
 * Every hour, every COW either
 * 
 * Stops if the grass in her current cell was already eaten by another COW. Eats all the grass in
 * her current cell and moves one cell forward according to the direction she faces. Over time, each
 * COW therefore leaves a barren "rut" of empty cells behind her.
 * 
 * If two COWs move onto the same grassy cell in the same move, they share the cell and continue
 * moving in their respective directions in the next hour.
 * 
 * Please determine the amount of grass eaten by each COW. Some COWs never stop, and therefore eat
 * an infinite amount of grass.
 * 
 * INPUT FORMAT:
 * 
 * The first line of input contains 𝑁 . Each of the next 𝑁 lines describes the starting location
 * of a COW, in terms of a character that is either N (for north-facing) or E (for east-facing) and
 * two nonnegative integers 𝑥 and 𝑦 (0≤𝑥≤109 , 0≤𝑦≤109 ) giving the coordinates of a cell. All
 * 𝑥 -coordinates are distinct from each-other, and similarly for the 𝑦 -coordinates. To be as
 * clear as possible regarding directions and coordinates, if a COW is in cell (𝑥,𝑦) and moves
 * north, she ends up in cell (𝑥,𝑦+1) . If she instead had moved east, she would end up in cell
 * (𝑥+1,𝑦) .
 * 
 * OUTPUT FORMAT:
 * 
 * Print 𝑁 lines of output. Line 𝑖 in the output should describe the number of cells worth of
 * grass that the 𝑖 th COW in the input eats. If a COW eats an infinite amount of grass, output
 * "Infinity" for that COW. SAMPLE INPUT:
 * 
 * 6 
 * E 3 5 
 * N 5 3 
 * E 4 6 
 * E 10 4 
 * N 11 2 
 * N 8 1 
 * 
 * SAMPLE OUTPUT:
 * 
 * 5 
 * 3 
 * Infinity 
 * Infinity 
 * 2 
 * 5 
 * 
 * SCORING:
 * 
 * In test cases 2-5, all coordinates are at most 100 . In test cases 6-10, there are no additional
 * constraints.
 */
//@formatter:on

enum Statuses {
    MOVING, STOP, INFINITE
}


enum Directions {
    NORTH, EAST
}



class COW {
    Directions direction;
    int x, y;
    int cnt = 1;
    Statuses status;
    String sname;
    String dname;

    public COW(String direction, int x, int y) {
        this.direction = direction.equals("E") ? Directions.EAST : Directions.NORTH;
        this.x = x;
        this.y = y;
        this.cnt = 0;
        status = Statuses.MOVING;
        sname = this.status.name();
        dname = this.direction.name();
    }

    public void eatAndMove() {
        this.cnt += 1;
        if (this.direction == Directions.NORTH) {
            this.y += 1;
        } else {
            this.x += 1;
        }
    }

    public void setStatus(Statuses status) {
        this.status = status;
        this.sname = this.status.name();
    }

    public void printResult() {
        String s = this.status == Statuses.INFINITE ? "Infinite" : String.valueOf(cnt);
        System.out.println(s);
    }

}


public class StuckInARut {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        COW[] COWs = new COW[N];
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            String line = scan.nextLine();
            String[] inputs = line.split(" ");
            int x = Integer.valueOf(inputs[1]);
            int y = Integer.valueOf(inputs[2]);
            COWs[i] = new COW(inputs[0].trim(), x, y);
        }
        simulate(COWs);
        for (COW c : COWs) {
            c.printResult();
        }
    }


    public static void simulate(COW[] COWs) {
        List<int[]> emptyCells = new ArrayList<int[]>();
        while (!areAllCOWsDone(COWs)) {
            for (COW c : COWs) {
                if(c.x == 11 && c.y == 4){
                    System.out.println();
                }
                // we only check all the cows from the previous
                // iteration that are in MOVING STATUS.
                // this check will set the status of these cows to
                // a new value which determins what should happen that that cow
                // while checking, we should NOT produce any movements, since
                // the check itself depends on the SNAPSHOT of the farm cells
                if (c.status == Statuses.MOVING) {
                    if (isCOWOnEmpty(c, emptyCells)) {
                        c.status = Statuses.STOP;
                        continue;
                    }
                    if (canCOWGoInfinite(c, COWs,emptyCells)) {
                        c.status = Statuses.INFINITE;
                        continue;
                    }

                }
            }
            // for all cows that are MOVING, move them
            for (COW c : COWs) {
                if (c.status != Statuses.STOP) {
                    emptyCells.add(new int[] {c.x, c.y});
                    c.eatAndMove();
                }
            }
        }
    }

    public static boolean isCOWOnEmpty(COW c, List<int[]> empCells) {
        boolean ret = false;
        for (int[] cell : empCells) {
            if (cell[0] == c.x && cell[1] == c.y)
                return true;
        }
        return ret;
    }

    public static boolean canCOWGoInfinite(COW COW, COW[] COWs, List<int[]> emptyCells) {
        // first check any of those empty cells along the X/Y axies (dpeneds on the direction)
        // whose x > cow.x or y> cows.y
        for (int[] cell : emptyCells) {
            if (COW.direction == Directions.NORTH) {
                if (cell[0] == COW.x && cell[1] > COW.y)
                    return false;
            } else {
                if (cell[1] == COW.y && cell[1] > COW.x)
                    return false;
            }
        }
        // by passing previous test, there are NO emtpy cells along the path of a cow
        // so we are comparing COW with remaining cows in the list to echeck
        // any of those moving cow CAN potentially block the current COW
        for (COW c : COWs) {
            if (c.direction != COW.direction) {
                if (c.status != Statuses.STOP) {
                    if (COW.direction == Directions.NORTH && c.x <= COW.x && c.y > COW.y)
                        return false;
                    if (COW.direction == Directions.EAST && c.y <= COW.y && c.x > COW.x)
                        return false;
                }
            }
        }
        return true;
    }

    public static boolean areAllCOWsDone(COW[] COWs) {
        boolean ret = true;
        for (COW c : COWs) {
            if (c.status == Statuses.MOVING) {
                ret = false;
                break;
            }
        }
        return ret;
    }
}
