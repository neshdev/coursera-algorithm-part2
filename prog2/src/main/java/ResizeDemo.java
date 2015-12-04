import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ResizeDemo {
    public static void main(String[] args) {
        args = new String[] {
          "C:/coursera/Algorithms, Part II/assignments/seamCarving/chameleon.png",
          "0",
          "100"
        };
        
        if (args.length != 3) {
            StdOut.println("Usage:\njava ResizeDemo [image filename] [num cols to remove] [num rows to remove]");
            return;
        }

        Picture inputImg = new Picture(args[0]);
        int removeColumns = Integer.parseInt(args[1]);
        int removeRows = Integer.parseInt(args[2]); 

        StdOut.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        SeamCarver sc = new SeamCarver(inputImg);

        Stopwatch sw = new Stopwatch();

        for (int i = 0; i < removeRows; i++) {
            //StdOut.println("Start H time: " + sw.elapsedTime() + " seconds.");
            int[] horizontalSeam = sc.findHorizontalSeam();
            //StdOut.println("End H time: " + sw.elapsedTime() + " seconds.");
            
            sc.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < removeColumns; i++) {
            //StdOut.println("Start V time: " + sw.elapsedTime() + " seconds.");
            int[] verticalSeam = sc.findVerticalSeam();
            //StdOut.println("End V time: " + sw.elapsedTime() + " seconds.");
            sc.removeVerticalSeam(verticalSeam);
        }
        Picture outputImg = sc.picture();

        StdOut.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());

        StdOut.println("Resizing time: " + sw.elapsedTime() + " seconds.");
        inputImg.show();
        outputImg.show();
    }
    
}