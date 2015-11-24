import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;


public class WordNetTest
{

    @Test
    public void DistanceTest()
    {
        String synsets = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/synsets.txt";
        String hypernyms = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/hypernyms.txt";
        WordNet wn = new WordNet(synsets, hypernyms);
        String[] nounAList = new String[] { "white_marlin", "Black_Plague", "American_water_spaniel", "Brown_Swiss"  };
        String[] nounBList = new String[] { "mileage", "black_marlin", "histology", "barrel_roll"  };
        int[] expectedDistances = new int[] { 23, 33, 27, 29 };
        for (int i = 0; i < expectedDistances.length; i++) {
            String nounA = nounAList[i];
            String nounB = nounBList[i];
            int expected = expectedDistances[i];
            int actual = wn.distance(nounA, nounB);
            StdOut.println("Sample i:" + i + "Expected==Actual" +  expected +" "+ actual);
            //assertEquals("Sample i:" + i, expected, actual);
        }
        
    }

}
