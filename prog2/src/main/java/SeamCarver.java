import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver
{

    private void validateNotNull(Object obj)
    {
        if (obj == null)
            throw new NullPointerException("obj cannot be null");
    }

    private void validateXBounds(int x)
    {
        if (0 > x)
            throw new IndexOutOfBoundsException("x: " + x + " is less than 0");

        if (x >= this.width())
            throw new IndexOutOfBoundsException("x: " + x + " should be less than "
                    + (this.width() - 1));
    }

    private void validateYBounds(int y)
    {
        if (0 > y)
            throw new IndexOutOfBoundsException("y: " + y + " is less than 0");

        if (y >= this.height())
            throw new IndexOutOfBoundsException("y: " + y + " should be less than "
                    + (this.height() - 1));
    }

    private void validateSeam(int[] seam, int length)
    {
        //throw new IllegalArgumentException();
        if (seam.length != length)
            throw new IllegalArgumentException("Seam is longer. " + "Actual: " + seam.length
                    + ", Expected : " + length);

        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i - 1]) != 1) {
                throw new IllegalArgumentException("Seam at index " + i + ":" + seam[i]
                        + " differrs from seam at index " + (i - 1) + ":" + seam[i - 1]);
            }
        }
    }

    private void validateLength()
    {
        if (this.width() <= 1)
            throw new IllegalArgumentException("Width is <= 1");

        if (this.height() <= 1)
            throw new IllegalArgumentException("Height is <= 1");
    }

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture)
    {
        validateNotNull(picture);

        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture()
    {
        return this.picture;
    }

    // width of current picture
    public int width()
    {
        return this.picture.width();
    }

    // height of current picture
    public int height()
    {
        return this.picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y)
    {
        validateXBounds(x);
        validateYBounds(y);
        double energy = calculateEnergy(x, y);
        return energy;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam()
    {
        throw new NullPointerException();

    }

    private void connectTop(EdgeWeightedDigraph g)
    {
        for (int i = 0; i < this.width(); i++) {
            g.addEdge(new DirectedEdge(0, i + 1, 100));
        }
    }

    private void connectBottom(EdgeWeightedDigraph g)
    {
        for (int i = this.width() * (this.height() - 1); i < this.width() * this.height(); i++) {
            g.addEdge(new DirectedEdge(i + 1, g.V() - 1, 100));
        }
    }

    private double energy(int v)
    {
        int colRem = v % this.width();
        int col = colRem == 0 ? this.width() - 1 : colRem - 1 ;
        int row = colRem == 0 ?  v / this.width() -1 : v / this.width();
        //StdOut.println("v : " + v + " " + "(col,row) -> (" + col + "," + row + ")" );

        double energy = energy(col, row);
        return energy;
    }

    private void connect(EdgeWeightedDigraph g, int v)
    {
        int colRem = v % this.width();

        int w = v + this.width();

        if (colRem == 1) {
            g.addEdge(new DirectedEdge(v, w, energy(w) - energy(v)));
            g.addEdge(new DirectedEdge(v, w + 1, energy(w + 1) - energy(v)));
        }
        else if (colRem == 0) {
            g.addEdge(new DirectedEdge(v, w - 1, energy(w - 1) - energy(v)));
            g.addEdge(new DirectedEdge(v, w, energy(w) - energy(v)));
        }
        else {
            g.addEdge(new DirectedEdge(v, w - 1, energy(w - 1) - energy(v)));
            g.addEdge(new DirectedEdge(v, w, energy(w) - energy(v)));
            g.addEdge(new DirectedEdge(v, w + 1, energy(w + 1) - energy(v)));
        }
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam()
    {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(this.width() * this.height() + 2);

        connectTop(g);
        
        for (int v = 1; v <= this.width() * (this.height() - 1); v++) {
            connect(g, v);
        }
        
        connectBottom(g);
        
        AcyclicSP sp = new AcyclicSP(g, 0);
        Iterable<DirectedEdge> edges = sp.pathTo(g.V() - 1);
        Queue<Integer> q = new  Queue<Integer>();
        for (DirectedEdge edge : edges) {
            q.enqueue(edge.from());
        }
        
        //q.dequeue();

        int[] seam = new int[q.size()];
        for (int i = 0; i < seam.length; i++) {
            int v = q.dequeue();
            int colRem = v % this.width();
            int col = colRem == 0 ? this.width() - 1 : colRem - 1 ;
            seam[i] = col;
        }
        
        //q.pop();

        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam, this.width());
        validateLength();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam, this.height());
        validateLength();

    }

    private double calculateEnergy(int col, int row)
    {
        if ((col == 0) || (col == this.width() - 1))
            return 1000;

        if ((row == 0) || (row == this.height() - 1)) {
            return 1000;
        }

        int rX = picture.get(col + 1, row).getRed() - picture.get(col - 1, row).getRed();
        int gX = picture.get(col + 1, row).getGreen() - picture.get(col - 1, row).getGreen();
        int bX = picture.get(col + 1, row).getBlue() - picture.get(col - 1, row).getBlue();
        int changeX = (rX * rX) + (gX * gX) + (bX * bX);

        int rY = picture.get(col, row + 1).getRed() - picture.get(col, row - 1).getRed();
        int gY = picture.get(col, row + 1).getGreen() - picture.get(col, row - 1).getGreen();
        int bY = picture.get(col, row + 1).getBlue() - picture.get(col, row - 1).getBlue();
        int changeY = (rY * rY) + (gY * gY) + (bY * bY);

        int change = changeX + changeY;
        return Math.sqrt(change);
    }

}
