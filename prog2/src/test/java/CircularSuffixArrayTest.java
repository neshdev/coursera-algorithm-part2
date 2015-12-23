import static org.junit.Assert.*;

import org.junit.Test;

public class CircularSuffixArrayTest
{

    @Test
    public void TestSortOrder()
    {
        String s = "ABRACADABRA!";
        CircularSuffixArray c = new CircularSuffixArray(s);
        int[] sortOrder = new int[] { 11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2 };
        for (int i = 0; i < s.length(); i++) {
            assertEquals(sortOrder[i], c.index(i));
        }
    }

    @Test
    public void TestConstructorNullException()
    {
        try
        {
            CircularSuffixArray c = new CircularSuffixArray(null);
            fail("should not enter here");
        }
        catch (Exception ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void TestIndex_LessThan_0()
    {
        try
        {
            String s = "ABRACADABRA!";
            CircularSuffixArray c = new CircularSuffixArray(s);
            c.index(-1);
            fail("should not enter here");
        }
        catch (IndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void TestIndex_Equals_0()
    {
        String s = "ABRACADABRA!";
        CircularSuffixArray c = new CircularSuffixArray(s);
        Integer results = c.index(0);
        assertNotNull(results);
    }

    @Test
    public void TestIndex_Equals_N()
    {
        try
        {
            String s = "ABRACADABRA!";
            CircularSuffixArray c = new CircularSuffixArray(s);
            c.index(s.length());
            fail("should not enter here");
        }
        catch (IndexOutOfBoundsException ex) {
            assertNotNull(ex);
        }
    }

}
