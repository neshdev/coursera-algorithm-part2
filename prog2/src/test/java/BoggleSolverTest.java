import static org.junit.Assert.*;

import java.io.Console;

import org.junit.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolverTest
{

    private static String ALGS = "C:/coursera/Algorithms, Part II/assignments/boggle/dictionary-algs4.txt";
    private static String YAWL = "C:/coursera/Algorithms, Part II/assignments/boggle/dictionary-yawl.txt";

    private static String BOARD_4x4 = "C:/coursera/Algorithms, Part II/assignments/boggle/board4x4.txt";

//    @Test
//    public void test()
//    {
//        In in = new In(ALGS);
//        String[] dictionary = in.readAllStrings();
//        BoggleSolver solver = new BoggleSolver(dictionary);
//        BoggleBoard board = new BoggleBoard(BOARD_4x4);
//        int score = 0;
//        for (String word : solver.getAllValidWords(board))
//        {
//            StdOut.println(word);
//            score += solver.scoreOf(word);
//        }
//        StdOut.println("Score = " + score);
//    }

    @Test
    public void testV()
    {
        StdOut.println("Test V conversion");
        BoggleBoard board = new BoggleBoard(BOARD_4x4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int v = BoggleSolver.convertToV(i, j, board);
                StdOut.println(v);

            }
        }
    }

    @Test
    public void testI_J()
    {
        StdOut.println("Test I conversion");
        BoggleBoard board = new BoggleBoard(BOARD_4x4);
        for (int v = 0; v < 16; v++) {
            int row = BoggleSolver.convertToI(v, board);
            int column = BoggleSolver.convertToJ(v, board);
            StdOut.println(row + "," + column);
        }
    }
    
    @Test
    public void test_adj(){
        StdOut.println("Test Adj conversion");
        BoggleBoard board = new BoggleBoard(BOARD_4x4);
        for (int v = 0; v < 16; v++) {
            StdOut.print(v + ":");
            for (Integer w : BoggleSolver.adj(v, board)) {
                StdOut.print(w + " ");
            }
            StdOut.println();
        }
    }

}
