import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet
{

    private HashMap<Integer, String> synsets;
    private HashMap<String, ArrayList<Integer>> nouns;
    private Digraph graph;
    private SAP sap;
    private int verticies;

    private void addNouns(String[] keys, Integer value)
    {
        for (int i = 0; i < keys.length; i++) {
            addNoun(keys[i], value);
        }
    }

    private void addNoun(String key, Integer value)
    {
        if (!nouns.containsKey(key)) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(value);
            nouns.put(key, list);
        } else{
            nouns.get(key).add(value);
        }
    }

    private void addSynset(Integer key, String value)
    {
        if (!synsets.containsKey(key)) {
            synsets.put(key, value);
        }
        
    }

    private void validateNotNullArgument(Object obj)
    {
        if (obj == null)
            throw new NullPointerException();
    }
    
    private void validateAsylic(Digraph g){
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.hasCycle()){
            throw new IllegalArgumentException("Graph has a cycle");
        }
    }
    
    private void validateRooted(Digraph g){
        int count = 0;
        for (int v = 0; v < g.V(); v++) {
            if ( !(g.adj(v).iterator().hasNext())) count++;
            if ( count > 1) break;
                
        }
        if ( count > 1){
            throw new IllegalArgumentException("Has more than one root");
        }
    }

    private void validateNounExists(String noun)
    {
        if (!this.isNoun(noun)) {
            throw new IllegalArgumentException(noun + " does not exists in the rooted dag");
        }
    }

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        validateNotNullArgument(synsets);
        validateNotNullArgument(hypernyms);
        readAndParseSynsets(synsets);
        readAndParseHypernyms(hypernyms);
        
        validateAsylic(graph);
        validateRooted(graph);
        
        this.sap = new SAP(graph);
    }

    private void readAndParseSynsets(String synsets)
    {
        this.synsets = new HashMap<Integer, String>();
        this.nouns = new HashMap<String, ArrayList<Integer>>();

        In in = new In(synsets);
        String line = null;
        while ((line = in.readLine()) != null) {
            String[] items = line.split(",");
            Integer id = Integer.parseInt(items[0]);
            String synonyms = items[1];
            String[] synonymSet = items[1].split(" ");
            String gloss = items[2];

            addSynset(id, synonyms);
            addNouns(synonymSet, id);
            verticies++;
        }
    }

    private void readAndParseHypernyms(String hypernyms)
    {
        this.graph = new Digraph(verticies);
        In in = new In(hypernyms);
        String line = null;
        while ((line = in.readLine()) != null) {
            String[] items = line.split(",");
            int v = Integer.parseInt(items[0]);
            for (int i = 1; i < items.length; i++) {
                int w = Integer.parseInt(items[i]);
                this.graph.addEdge(v, w);
            }
        }

        
    }

    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        validateNotNullArgument(word);
        //todo, not sure is this what they are asking for?
        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        validateNotNullArgument(nounA);
        validateNotNullArgument(nounB);
        validateNounExists(nounA);
        validateNounExists(nounB);

        Iterable<Integer> v = nouns.get(nounA);
        Iterable<Integer> w = nouns.get(nounB);

        return sap.length(v, w);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // sap = shortest ancestral path
    public String sap(String nounA, String nounB)
    {
        validateNotNullArgument(nounA);
        validateNotNullArgument(nounB);
        validateNounExists(nounA);
        validateNounExists(nounB);

        Iterable<Integer> v = nouns.get(nounA);
        Iterable<Integer> w = nouns.get(nounB);

        int ancestor = sap.ancestor(v, w);
        return synsets.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args)
    {

    }
}
