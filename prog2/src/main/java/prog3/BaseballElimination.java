package prog3;
import java.util.HashMap;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

// immutable data type BaseballElimination that represents a sports division and determines which teams are mathematically eliminated
public class BaseballElimination
{
    private Queue<String> teams;
    private HashMap<String, Integer> teamToIndex;
    private ST<Integer, String> indexToTeam;
    private int[][] gamesRemaining;
    private int[] wins;
    private int[] losses;
    private int[] remaining;

    private void validateTeam(String team)
    {
        if (team == null) {
            throw new IllegalArgumentException("team cannot be null");
        }

        if (!teamToIndex.containsKey(team)) {
            throw new IllegalArgumentException("Not a valid team");
        }
    }

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename)
    {
        In in = new In(filename);
        int length = in.readInt();

        teamToIndex = new HashMap<String, Integer>();
        indexToTeam = new ST<Integer, String>();
        
        teams = new Queue<String>();
        wins = new int[length];
        losses = new int[length];
        remaining = new int[length];
        gamesRemaining = new int[length][length];
        
        for (int i = 0; i < length; i++) {
            String name = in.readString();
            int win = in.readInt();
            int loss = in.readInt();
            int remain = in.readInt();

            for (int j = 0; j < length; j++) {
                gamesRemaining[i][j] = in.readInt();
            }
            
            teamToIndex.put(name, i);
            indexToTeam.put(i, name);
            teams.enqueue(name);
            wins[i] = win;
            losses[i] = loss;
            remaining[i] = remain;
        }
    }

    // number of teams
    public int numberOfTeams()
    {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams()
    {
        return teams;
    }

    // number of wins for given team
    public int wins(String team)
    {
        validateTeam(team);
        Integer i = teamToIndex.get(team);
        return wins[i];
    }

    // number of losses for given team
    public int losses(String team)
    {
        validateTeam(team);
        Integer i = teamToIndex.get(team);
        return losses[i];
    }

    // number of remaining games for given team
    public int remaining(String team)
    {
        validateTeam(team);
        Integer i = teamToIndex.get(team);
        return remaining[i];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2)
    {
        validateTeam(team1);
        validateTeam(team2);

        Integer i = teamToIndex.get(team1);
        Integer j = teamToIndex.get(team2);
        return gamesRemaining[i][j];
    }

    private static int SINK_VERTEX_COUNT = 1;
    private static int SOURCE_VERTEX_COUNT = 1;
    
    // is given team eliminated?
    public boolean isEliminated(String team)
    {
        validateTeam(team);
        int winsX = wins(team);
        int remainX = remaining(team);
        int xIndex = teamToIndex.get(team);
        
        //trivial case
        for (int i = 0; i < this.numberOfTeams(); i++) {
            if ( i != xIndex){
                if ( (winsX + remainX) < wins[i] )
                    return true;
            }
        }
        
        int verticies = SOURCE_VERTEX_COUNT + SINK_VERTEX_COUNT + (this.numberOfTeams() - 1) * (this.numberOfTeams() - 2) + this.numberOfTeams();
        int sourceVertex = verticies - 1;
        int sinkVertex = verticies - 2;
        
        FlowNetwork g = new FlowNetwork(verticies);
        
        ST<Integer, String> gameVertexMap = new ST<Integer, String>();
        
        int gameVertex = this.numberOfTeams();
        for (int i = 0; i < gamesRemaining.length; i++) {
            for (int j = i+1; j < gamesRemaining.length; j++) {
                if ( (xIndex != i) && (xIndex != j)){
                    g.addEdge(new FlowEdge(sourceVertex, gameVertex, gamesRemaining[i][j] ));
                    g.addEdge(new FlowEdge(gameVertex, i, Double.POSITIVE_INFINITY));
                    g.addEdge(new FlowEdge(gameVertex, j, Double.POSITIVE_INFINITY));
                }
                
                gameVertexMap.put(gameVertex, i + "," + j);
                gameVertex++;
            }
            if ( xIndex != i ){
                g.addEdge(new FlowEdge(i,sinkVertex, winsX + remainX - wins[i]  ));
            }
        }
        
        FordFulkerson ff = new FordFulkerson(g, sourceVertex, sinkVertex);
//        StdOut.println("Running for team " + team);
//        StdOut.println("Run ford");
//        StdOut.println(g);
//        
//        StdOut.println("Min cut");
//        for (int v = 0; v < this.numberOfTeams(); v++) {
//            if (ff.inCut(v)){
//                StdOut.print( v + " ");
//            }
//        }
//        StdOut.println();
        
        for (FlowEdge e : g.adj(sourceVertex)) {
            if (e.capacity() != e.flow())
                return true;
        }
        
        return false;
    }
        
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team)
    {
        validateTeam(team);
        int winsX = wins(team);
        int remainX = remaining(team);
        int xIndex = teamToIndex.get(team);
        Queue<String> q = new Queue<String>();
        
        //trivial case
        for (int i = 0; i < this.numberOfTeams(); i++) {
            if ( i != xIndex){
                if ( (winsX + remainX) < wins[i] )
                    q.enqueue(indexToTeam.get(i));
            }
        }
        
        if (!q.isEmpty()) return q;
        
        int verticies = SOURCE_VERTEX_COUNT + SINK_VERTEX_COUNT + (this.numberOfTeams() - 1) * (this.numberOfTeams() - 2) + this.numberOfTeams();
        int sourceVertex = verticies - 1;
        int sinkVertex = verticies - 2;
        
        FlowNetwork g = new FlowNetwork(verticies);
        
        ST<Integer, String> gameVertexMap = new ST<Integer, String>();
        
        int gameVertex = this.numberOfTeams();
        for (int i = 0; i < gamesRemaining.length; i++) {
            for (int j = i+1; j < gamesRemaining.length; j++) {
                if ( (xIndex != i) && (xIndex != j)){
                    g.addEdge(new FlowEdge(sourceVertex, gameVertex, gamesRemaining[i][j] ));
                    g.addEdge(new FlowEdge(gameVertex, i, Double.POSITIVE_INFINITY));
                    g.addEdge(new FlowEdge(gameVertex, j, Double.POSITIVE_INFINITY));
                }
                
                gameVertexMap.put(gameVertex, i + "," + j);
                gameVertex++;
            }
            if ( xIndex != i ){
                g.addEdge(new FlowEdge(i,sinkVertex, winsX + remainX - wins[i]  ));
            }
        }
        
        
        FordFulkerson ff = new FordFulkerson(g, sourceVertex, sinkVertex);
        for (int v = 0; v < this.numberOfTeams(); v++) {
            if (ff.inCut(v)){
                q.enqueue(indexToTeam.get(v));
            }
        }
        
        return q.isEmpty() ?  null : q;
    }

    public static void main(String[] args)
    {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
