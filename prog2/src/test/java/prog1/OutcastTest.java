package prog1;
import static org.junit.Assert.*;

import org.junit.Test;

import prog1.Outcast;
import prog1.WordNet;
import edu.princeton.cs.algs4.In;


public class OutcastTest
{

    @Test
    public void Outcast5()
    {
        String synsets = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/synsets.txt";
        String hypernyms = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/hypernyms.txt";
        String outcastFile = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/outcast5.txt";
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        
        In in = new In(outcastFile);
        String[] nouns = in.readAllStrings();
        
        String actual = outcast.outcast(nouns);
        String expected = "table";
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void Outcast8()
    {
        String synsets = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/synsets.txt";
        String hypernyms = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/hypernyms.txt";
        String outcastFile = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/outcast8.txt";
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        
        In in = new In(outcastFile);
        String[] nouns = in.readAllStrings();
        
        String actual = outcast.outcast(nouns);
        String expected = "bed";
        
        assertEquals(expected, actual);
    }

    
    @Test
    public void Outcast11()
    {
        String synsets = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/synsets.txt";
        String hypernyms = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/hypernyms.txt";
        String outcastFile = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/outcast11.txt";
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        
        In in = new In(outcastFile);
        String[] nouns = in.readAllStrings();
        
        String actual = outcast.outcast(nouns);
        String expected = "potato";
        
        assertEquals(expected, actual);
    }


}
