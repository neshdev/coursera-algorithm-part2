package prog3;
import static org.junit.Assert.*;

import org.junit.Test;

import prog3.BaseballElimination;
import edu.princeton.cs.algs4.StdOut;

public class BaseballEliminationTest
{

    @Test
    public void LoadFile()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        assertNotNull(be);
    }

    private static int TEAMS4_COUNT = 4;
    private static String[] TEAMS4_TEAMS = new String[] { "Atlanta", "Philadelphia", "New_York",
            "Montreal" };
    private static int[] TEAM4_WINS = new int[] { 83, 80, 78, 77 };
    private static int[] TEAM4_LOSSES = new int[] { 71, 79, 78, 82 };
    private static int[] TEAM4_REMAIN = new int[] { 8, 3, 6, 3 };
    private static int TEAM4_ATLvsPHL_REMAIN = 1; 
    private static int TEAM4_ATLvsNYC_REMAIN = 6;
    private static int TEAM4_ATLvsMON_REMAIN = 1;
    

    @Test
    public void validateSize()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        assertEquals(TEAMS4_COUNT, be.numberOfTeams());
    }

    @Test
    public void validateTeams()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        int i = 0;
        for (String team : be.teams()) {
            assertEquals(TEAMS4_TEAMS[i++], team);
        }
    }
    
    @Test
    public void validateWinsLossesAndRemaining(){
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        
        for (int i = 0; i < TEAMS4_TEAMS.length; i++) {
            String team = TEAMS4_TEAMS[i];
            assertEquals(TEAM4_WINS[i], be.wins(team));
            assertEquals(TEAM4_LOSSES[i], be.losses(team));
            assertEquals(TEAM4_REMAIN[i], be.remaining(team));
        }
    }
    
    @Test
    public void validateAgainst(){
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        assertEquals(TEAM4_ATLvsPHL_REMAIN, be.against("Atlanta", "Philadelphia"));
        assertEquals(TEAM4_ATLvsNYC_REMAIN, be.against("Atlanta", "New_York"));
        assertEquals(TEAM4_ATLvsMON_REMAIN, be.against("Atlanta", "Montreal"));
    }
    
    @Test
    public void IsPhiliadelphiaEliminated(){
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        be.isEliminated("Philadelphia");
    }
    
    @Test
    public void PhiliadelphiaCertofElimination(){
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        for (String team : be.certificateOfElimination("Philadelphia")) {
            StdOut.println(team);
        }
    }
    
    @Test
    public void AtlantaCertofElimination(){
        String filename = "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt";
        BaseballElimination be = new BaseballElimination(filename);
        for (String team : be.certificateOfElimination("Atlanta")) {
            StdOut.println(team);
        }
    }
    
    private static void runFile(String filename){
        StdOut.println("Running for file:" + filename);
        String[] args = new String[] { filename };
        BaseballElimination.main(args);
        StdOut.println();
    }
    
    @Test
    public void testFile4(){
        String[] files = new String[]
                {
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams1.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams10.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams12-allgames.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams12.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams24.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams29.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams30.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams32.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams36.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams4.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams42.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams48.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams4a.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams4b.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams5.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams50.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams54.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams5a.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams5b.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams5c.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams60.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams7.txt",
                "C:/coursera/Algorithms, Part II/assignments/baseball/teams8.txt",
                };
        for (String filename : files) {
            runFile(filename);
        }
        
    }

}
