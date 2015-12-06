import static org.junit.Assert.*;

import org.junit.Test;

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

}
