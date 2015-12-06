public class BaseballElimination
{
    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename)
    {

    }

    // number of teams
    public int numberOfTeams()
    {
        throw new NullPointerException();
    }

    // all teams
    public Iterable<String> teams()
    {
        throw new NullPointerException();
    }

    // number of wins for given team
    public int wins(String team)
    {
        throw new NullPointerException();
    }

    // number of losses for given team
    public int losses(String team)
    {
        throw new NullPointerException();
    }

    // number of remaining games for given team
    public int remaining(String team)
    {
        throw new NullPointerException();
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2)
    {
        throw new NullPointerException();
    }

    // is given team eliminated?
    public boolean isEliminated(String team)
    {
        throw new NullPointerException();
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team)
    {
        throw new NullPointerException();
    }
}
