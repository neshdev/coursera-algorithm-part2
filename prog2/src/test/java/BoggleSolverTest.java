import org.junit.Test;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolverTest
{

    private static String ALGS = "C:/coursera/Algorithms, Part II/assignments/boggle/dictionary-algs4.txt";
    private static String YAWL = "C:/coursera/Algorithms, Part II/assignments/boggle/dictionary-yawl.txt";

    private static String BOARD_4x4 = "C:/coursera/Algorithms, Part II/assignments/boggle/board4x4.txt";
    private static String BOARD_2x2 = "C:/coursera/Algorithms, Part II/assignments/boggle/board2x2.txt";

    public void runSolver(String dictionaryFile, String boardFile)
    {
        In in = new In(dictionaryFile);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(boardFile);
        int actual = 0;
        for (String word : solver.getAllValidWords(board))
        {
            actual += solver.scoreOf(word);
        }
        Integer expected = Integer.parseInt(boardFile
                .replace("C:/coursera/Algorithms, Part II/assignments/boggle/", "")
                .replace("board-points", "")
                .replace(".txt",
                        ""));

        StdOut.println(boardFile + " (" + expected + "," + actual + ")");
    }

    //@Test
    public void runAllTests()
    {
        String path = "C:/coursera/Algorithms, Part II/assignments/boggle/";
        String[] files = new String[]
        {
                path + "board-points0.txt",
                path + "board-points1.txt",
                path + "board-points100.txt",
                path + "board-points1000.txt",
                path + "board-points1111.txt",
                path + "board-points1250.txt",
                path + "board-points13464.txt",
                path + "board-points1500.txt",
                path + "board-points2.txt",
                path + "board-points200.txt",
                path + "board-points2000.txt",
                path + "board-points26539.txt",
                path + "board-points3.txt",
                path + "board-points300.txt",
                path + "board-points4.txt",
                path + "board-points400.txt",
                path + "board-points4410.txt",
                path + "board-points4527.txt",
                path + "board-points4540.txt",
                path + "board-points5.txt",
                path + "board-points500.txt",
                path + "board-points750.txt",
                path + "board-points777.txt"
        };
        for (String board : files) {
            runSolver(YAWL, board);
        }
    }

    //@Test
    public void standard()
    {
        In in = new In(YAWL);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(BOARD_4x4);
        StdOut.println("Words in board");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
    
    @Test
    public void debugger()
    {
        String boardFile = "C:/coursera/Algorithms, Part II/assignments/boggle/board-points777.txt";
        In in = new In(YAWL);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(boardFile);
        StdOut.println("Words in board");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

//    @Test
//    public void testV()
//    {
//        StdOut.println("Test V conversion");
//        BoggleBoard board = new BoggleBoard(BOARD_4x4);
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                int v = BoggleSolver.convertToV(i, j, board);
//                StdOut.println(v);
//
//            }
//        }
//    }
//
//    @Test
//    public void testI_J()
//    {
//        StdOut.println("Test I conversion");
//        BoggleBoard board = new BoggleBoard(BOARD_4x4);
//        for (int v = 0; v < 16; v++) {
//            int row = BoggleSolver.convertToI(v, board);
//            int column = BoggleSolver.convertToJ(v, board);
//            StdOut.println(row + "," + column);
//        }
//    }
//    
//    @Test
//    public void test_adj(){
//        StdOut.println("Test Adj conversion");
//        BoggleBoard board = new BoggleBoard(BOARD_4x4);
//        for (int v = 0; v < 16; v++) {
//            StdOut.print(v + ":");
//            for (Integer w : BoggleSolver.adj(v, board)) {
//                StdOut.print(w + " ");
//            }
//            StdOut.println();
//        }
//    }

}
