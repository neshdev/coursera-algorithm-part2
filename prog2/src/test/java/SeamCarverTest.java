
import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarverTest
{

    @Test
    public void TestEnergyFunctionAt_column1_row2()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        double actual = sc.energy(1, 2);
        double expected = Math.sqrt(52024);
        assertEquals(expected, actual, .0001);
    }

    @Test
    public void TestEnergyFunctionAt_column1_row1()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        double actual = sc.energy(1, 1);
        double expected = Math.sqrt(52225);
        assertEquals(expected, actual, .0001);
    }

    @Test
    public void TestEnergyFunctionAtCorners_Return_1000()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        assertEquals(1000, sc.energy(0, 0), .0001);
        assertEquals(1000, sc.energy(0, sc.height() - 1), .0001);
        assertEquals(1000, sc.energy(0, sc.width() - 1), .0001);
        assertEquals(1000, sc.energy(sc.width() - 1, sc.height() - 1), .0001);
    }

    @Test
    public void printEnergy()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
        Picture picture = new Picture(filename);
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(),
                picture.height());

        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");

        for (int j = 0; j < sc.height(); j++) {
            for (int i = 0; i < sc.width(); i++)
                StdOut.printf("%9.0f ", sc.energy(i, j));
            StdOut.println();
        }
    }

    @Test
    public void findVerticalSeams_3x4()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        int[] exepected = new int[] { 1, 1, 1, 1 };
        for (int i = 0; i < exepected.length; i++) {
            assertEquals(exepected[i], actual[i]);
        }
    }

    @Test
    public void findVerticalSeams_6x5()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/6x5.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        int[] exepected = new int[] { 4, 4, 3, 2, 2 };
        for (int i = 0; i < exepected.length; i++) {
            assertEquals(exepected[i], actual[i]);
        }
    }
    
    @Test
    public void removeVerticalSeams_6x5()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/6x5.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] seam = sc.findVerticalSeam();
        sc.removeVerticalSeam(seam);
        sc.picture().show();
    }

    @Test
    public void printSeams()
    {
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/6x5.png";
        Picture picture = new Picture(filename);
        StdOut.printf("%s (%d-by-%d image)\n", filename, picture.width(), picture.height());
        StdOut.println();
        StdOut.println("The table gives the dual-gradient energies of each pixel.");
        StdOut.println("The asterisks denote a minimum energy vertical or horizontal seam.");
        StdOut.println();

        SeamCarver carver = new SeamCarver(picture);

        StdOut.printf("Vertical seam: { ");
        int[] verticalSeam = carver.findVerticalSeam();
        for (int x : verticalSeam)
            StdOut.print(x + " ");
        StdOut.println("}");
        printSeam(carver, verticalSeam, VERTICAL);

        StdOut.printf("Horizontal seam: { ");
        int[] horizontalSeam = carver.findHorizontalSeam();
        for (int y : horizontalSeam)
            StdOut.print(y + " ");
        StdOut.println("}");
        printSeam(carver, horizontalSeam, HORIZONTAL);
    }

    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL = false;

    private static void printSeam(SeamCarver carver, int[] seam, boolean direction)
    {
        double totalSeamEnergy = 0.0;

        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction == HORIZONTAL && row == seam[col]) ||
                        (direction == VERTICAL && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                StdOut.printf("%7.2f%s ", energy, marker);
            }
            StdOut.println();
        }
        // StdOut.println();
        StdOut.printf("Total energy = %f\n", totalSeamEnergy);
        StdOut.println();
        StdOut.println();
    }

}
