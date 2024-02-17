package bronze2022to2023;

import java.io.PrintStream;
import java.util.Scanner;

public class StampProblem {
    static String sampleStamp =
            "10101" + "\n" +
                    "00000" + "\n" +
                    "10011";
    static PrintStream out = System.out;
    public static void main(String[] args) {
        /**
         * functionsn to implement
         * 1. rotate a stamp
         * 2. check whether stamp can be print at the location boolean checkStampAtLocation(i,j,stamp,targetCanvas)
         * 3. stamp on canvas  void stampOnCanvas(i,j,stamp,currentCanvas)
         * 4. boolean compareCanvases (currentCanvas, targetCanvase)
         *
         * Data structure,
         * targetCanvase : char[N][N]
         * currentCanvase: cahr[N][N]
         * stamp char[M][M]
         *
         */
        testRotateStamp();
        testCheckStampAtLocation();
//        testStampOnCanvas();
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int M = scan.nextInt();
//        char[][] targetCanvase = createCanvasFromStrings(getLines(scan,N));
//        char[][] stamp = createCanvasFromStrings(getLines(scan,M));
//


    }

    static String getLines(Scanner scan, int numOfLines){
        StringBuffer buffer = new StringBuffer();
        for(int n =0; n< numOfLines; n++){
            String line = scan.nextLine();
            buffer.append(line);
            if(n != numOfLines-1) buffer.append("\n");
        }
        return buffer.toString();
    }
    static boolean compareCanvases (char[][] c1, char[][]c2){
        if(c1.length != c2.length ) return  false;
        for(int i = 0; i < c1.length; i++){
            if(c1[i].length != c2[i].length) return false;
            for(int j =0; j < c1[i].length; j++){
                if (c1[i][j] != c2[i][j]) return false;
            }
        }
        return true;
    }
    /**
     * modify the character in current canvas
     * @param i
     * @param j
     * @param stamp
     * @param currentCanvas
     */
    static void stampOnCanvase(int i, int j , char[][] stamp, char[][] currentCanvas){
        // you can do a check to assert that the stamp will not go outside of the
        // canvas
        assert i+stamp.length <= currentCanvas.length;
        assert j + stamp[0].length <= currentCanvas[0].length;

        for(int stampi =0; stampi < stamp.length; stampi++){
            for(int stampj = 0 ; stampj < stamp.length; stampj++){
                currentCanvas[i+stampi][j+stampj] = stamp[stampi][stampj];
            }
        }
    }

    static void testStampOnCanvas(){
        char[][] canvas =createCanvasFromStrings("0000\n0000\n0000\n0000");
        char[][] stamp = createCanvasFromStrings("11\n11");
        out.println("shape of the stamp");
        printCanvas(stamp);
        out.println("before print");
        printCanvas(canvas);
        int i = 1, j = 1;
        stampOnCanvase(i,j,stamp,canvas);
        out.println(String.format("after print at location %d,%d",i,j));
        printCanvas(canvas);
    }

    /**
     * given a location i,j, check whether stamp can be printed
     * iterate through the stamp shape , at stampi, stampj, get the stamp
     * character, compare it with the targetcanvase character at i+stampi, j+stampj
     * if not same, then break and quit wtih false
     * @param i
     * @param j
     * @param stamp
     * @param targetCanvase
     * @return
     */
    static boolean checkStampAtLocation(int i , int j , char[][] stamp, char[][] targetCanvase){
        boolean canPrint = true;
        outsiderloop:
        for(int stampi = 0; stampi < stamp.length; stampi++){
            for(int stampj = 0; stampj < stamp[stampi].length; stampj++){
                if (stamp[stampi][stampj] == 0) continue; //if stamp current location is blank, do not check
                if(stamp[stampi][stampj] != targetCanvase[i+stampi][j+stampj]){
                    canPrint = false;
                    break outsiderloop;
                }
            }
        }
        return canPrint;
    }

    static void testCheckStampAtLocation(){
        char[][] stamp = createCanvasFromStrings("11");
        char[][] canvas = createCanvasFromStrings("01100\n00110");
        out.println("Test testCheckStatementAtLocation starting");
        assert checkStampAtLocation(0,0,stamp,canvas) ==false;
        assert checkStampAtLocation(0,1,stamp,canvas) ==true;
        assert checkStampAtLocation(0,2,stamp,canvas) ==false;
        assert checkStampAtLocation(1,1,stamp,canvas) ==false;
        assert checkStampAtLocation(1,2,stamp,canvas) ==true;
        out.println("Test testCheckStatementAtLocation passed with no error");

    }

    /**
     * rotate the stamp by 90 degree
     * for example
     * 1 2
     * 3 4
     * will become
     * 3 1
     * 4 2
     * AxB array will become BxA array
     * essentially a element at i,j will be stored in (j, A-i-1)
     *
     * @param stamp
     * @return
     */
    static char[][] rotateStamp(char[][] stamp) {
        // create array, here we are not assuming the stamp is square, what if it is
        // a rectangle A X B rotate will becomes B x A

        int A = stamp.length;
        int B = stamp[0].length;
        char[][] newStamp = new char[B][A];
        for (int i = 0; i < stamp.length; i++) {
            for (int j = 0; j < stamp[i].length; j++) {
                newStamp[j][A - i - 1] = stamp[i][j];
            }
        }
        return newStamp;
    }


    static char[][] createCanvasFromStrings(String s) {
        String[] lines = s.split("\n");
        char[][] ret = new char[lines.length][];
        for (int lineno = 0; lineno < lines.length; lineno++) {
            int len = lines[lineno].length();
            ret[lineno] = new char[len];
            for (int charpos = 0; charpos < len; charpos++) {
                ret[lineno][charpos] = lines[lineno].charAt(charpos);
            }
        }
        return ret;
    }

    /**
     * utility function , print out a canvas or a stamp
     *
     * @param shape
     */
    static void printCanvas(char[][] shape) {
        out.println(String.format("Size of the shape is: %d x %d", shape.length, shape[0].length));
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                System.out.print(shape[i][j]);
            }
            out.println();
        }
    }

    static void testRotateStamp() {
        char[][] stamp = createCanvasFromStrings(sampleStamp);
        char[][] rotatedStamp = rotateStamp(stamp);
        System.out.print("original stamp");
        printCanvas(stamp);
        System.out.print("rorated stamp");
        printCanvas(rotatedStamp);
    }
}
