package prog2;
import static org.junit.Assert.*;

import org.junit.Test;

import prog2.SeamCarver;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;


public class SeamCarver2Test
{

//    @Test
//    public void test()
//    {
//        //width by height ex: 3x4
//        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
//        Picture picture = new Picture(filename);
//        SeamCarver c = new SeamCarver(picture);
//        
//        int width = 3;
//        int height = 4;
//        for (int row = 0; row < height; row++) {
//            for (int col = 0; col < width; col++) {
//                StdOut.print(c.convertToV(col, row) + " ");
//            }
//            StdOut.println();
//        }
//    }
//    
//    @Test
//    public void test2(){
//        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
//        Picture picture = new Picture(filename);
//        SeamCarver c = new SeamCarver(picture);
//        
//        for (int v = 1; v < 13; v++) {
//            c.energy(v);
//        }
//    }
//    
//    @Test
//    public void FindAdj(){
//        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/3x4.png";
//        Picture picture = new Picture(filename);
//        SeamCarver c = new SeamCarver(picture);
//        
//        for (int v = 0; v < c.width() * c.height() + 2; v++) {
//            StdOut.print(v + ": ");
//            for (Integer w : c.adj(v)) {
//                StdOut.print(w + " ");
//            }
//            StdOut.println();
//        }
//    }
    
    @Test
    public void FindHorizontalSeam1(){
        StdOut.println("Find H Seam 6x5");
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/6x5.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findHorizontalSeam();
        for (int i = 0; i < actual.length; i++) {
            StdOut.println(actual[i]);
        }
        StdOut.println("End H Find Seam 6x5");
    }
    
    @Test
    public void FindVerticalSeam1(){
        StdOut.println("Find V Seam 6x5");
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/6x5.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        for (int i = 0; i < actual.length; i++) {
            StdOut.println(actual[i]);
        }
        StdOut.println("End V Find Seam 6x5");
    }
    
    @Test
    public void Find8x1(){
        StdOut.println("Find V Seam 8x1");
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/8x1.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        for (int i = 0; i < actual.length; i++) {
            StdOut.println(actual[i]);
        }
        StdOut.println("End V Find Seam 8x1");
    }
    
    @Test
    public void Find1x8(){
        StdOut.println("Find V Seam 1x8");
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/1x8.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        for (int i = 0; i < actual.length; i++) {
            StdOut.println(actual[i]);
        }
        StdOut.println("End V Find Seam 1x8");
    }
    
    @Test
    public void Find1x1(){
        StdOut.println("Find V Seam 1x1");
        String filename = "C:/coursera/Algorithms, Part II/assignments/seamCarving/1x1.png";
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);
        int[] actual = sc.findVerticalSeam();
        for (int i = 0; i < actual.length; i++) {
            StdOut.println(actual[i]);
        }
        StdOut.println("End V Find Seam 1x1");
    }

}
