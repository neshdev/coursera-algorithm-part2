package prog1;


import static org.junit.Assert.*;

import org.junit.Test;

import prog1.SAP;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest
{

    @Test
    public void test()
    {
        String fileName = "C:/coursera/Algorithms, Part II/assignments/wordnet-testing/wordnet/digraph1.txt";
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        
        int[] vList = new int[] {3, 9, 7, 1 };
        int[] wList = new int[] {11, 12, 2, 6 };
        int[] lList = new int[] {4, 3, 4, -1 };
        int[] aList = new int[] {1, 5, 0, -1 };
        
        for (int i = 0; i < aList.length; i++) {
            int v = vList[i];
            int w = wList[i];
            
            int length = sap.length(v, w);
            int ancestor  = sap.ancestor (v, w);
            
            assertEquals("Length - " + i + " :", lList[i] , length);
            assertEquals("Ancestor - " + i + " :", aList[i], ancestor);
        }
    }
}
