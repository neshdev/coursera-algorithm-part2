import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast
{
    private WordNet wordnet;
    
    //constructor takes a WordNet object
    public Outcast(WordNet wordnet)
    {
        this.wordnet = wordnet;
    }

    //given an array of WordNet nouns, return an outcast
    //Assume that argument to outcast() contains only valid wordnet nouns 
    //(and that it contains at least two such nouns).
    public String outcast(String[] nouns)
    {
        int champDistnace = Integer.MIN_VALUE;
        int champIndex = Integer.MIN_VALUE;
        for (int i = 0; i < nouns.length; i++) {
            String nounA = nouns[i];
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                if ( i != j){
                    String nounB = nouns[j];
                    dist = dist + wordnet.distance(nounA, nounB);
                }
            }
            if (dist > champDistnace ){
                champDistnace = dist;
                champIndex = i;
            }
        }
        String outcast = nouns[champIndex];
        return outcast;
    }

    //see test client below
    public static void main(String[] args)
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
