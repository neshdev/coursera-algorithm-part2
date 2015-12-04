import java.util.Arrays;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

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
            throw new IllegalArgumentException("Seam is not the same. " + "Actual: " + seam.length
                    + ", Expected : " + length);

        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) {
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

    private int convertToV(int col, int row)
    {
        int x = (col % this.width()) + 1;
        int v = (row + 1) * x;
        return v;
    }

    private Iterable<Integer> adj(int v)
    {
        Queue<Integer> q = new Queue<Integer>();

        if (v == 0) {
            for (int x = 1; x <= this.width(); x++) {
                q.enqueue(x);
            }
            return q;
        }

        if (v == this.width() * this.height() + 1)
            return q;

        int max = this.width() * (this.height() - 1);
        if (max < v) {
            q.enqueue(this.width() * this.height() + 1);
            return q;
        }

        if (v % this.width() == 1) {
            q.enqueue(v + this.width());
            q.enqueue(v + this.width() + 1);
        }
        else if (v % this.width() == 0) {
            q.enqueue(v + this.width() - 1);
            q.enqueue(v + this.width());
        }
        else {
            q.enqueue(v + this.width() - 1);
            q.enqueue(v + this.width());
            q.enqueue(v + this.width() + 1);
        }

        return q;
    }

    private void relax(int v, int w)
    {
        if (distTo[w] > distTo[v] + energy(v)) {
            edgeTo[w] = v;
            distTo[w] = distTo[v] + energy(v);
        }
    }

    private double energy(int v)
    {
        if (v == 0 || v == this.width() * this.height() + 1)
            return 0;
        int rowRem = v % this.width();
        int col = rowRem == 0 ? this.width() - 1 : (v % this.width()) - 1;
        int row = rowRem == 0 ? (v / width()) - 1 : v / this.width();
        return energy(col, row);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam()
    {
        Picture orig = picture;
        this.transpose();
        int[] seams = this.findVerticalSeam();
        this.picture = orig;
        return seams;
    }

    private void transpose(){
        Picture transpose = new Picture(this.height(), this.width());
        transpose.setOriginUpperLeft();
        for (int col = 0; col < this.width(); col++) {
            for (int row = 0; row < this.height(); row++) {
                transpose.set(row, col, this.picture.get(col, row));
            }
        }
        
        this.picture = transpose;
    }
    
    private int[] edgeTo;
    private double[] distTo;

    // sequence of indices for vertical seam
    public int[] findVerticalSeam()
    {
        int source = 0;
        int sourceWeight = 0;
        int verticies = this.width() * this.height();
        edgeTo = new int[verticies + 2];
        distTo = new double[verticies + 2];

        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = sourceWeight;

        for (int v = 0; v < verticies + 2; v++) {
            for (Integer w : this.adj(v)) {
                relax(v, w);
            }
        }

        int endVertex = verticies + 1;

        int[] seams = converToColIndexSeam(pathTo(endVertex));
        return seams;
    }
    
    private int convertToX(int v){
        if (v == 0 || v == this.width() * this.height() + 1)
            return -1;
        int rowRem = v % this.width();
        int col = rowRem == 0 ? this.width() - 1 : (v % this.width()) - 1;
        return col;
    }
    
    private int[] converToColIndexSeam(Iterable<Integer> vSeams){
        int[] seams = new int[this.height() + 1];
        int i = 0;
        for (int v : vSeams) {
            seams[i++] = convertToX(v);
        }
        
        return Arrays.copyOfRange(seams, 0, this.height());
    }

    private Iterable<Integer> pathTo(int s)
    {
        Stack<Integer> stack = new Stack<Integer>();
        for (int x = s; x != 0; x = edgeTo[x]) {
            stack.push(x);
        }
        return stack;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam, this.width());
        validateLength();

        Picture p = new Picture(this.width(), this.height() - 1);
        p.setOriginUpperLeft();
        for (int col = 0; col < this.width(); col++) {
            for (int row = 0; row < seam[col]; row++) {
                p.set(col, row, this.picture.get(col, row));
            }

            for (int row = seam[col]; row < this.height() - 1; row++) {
                p.set(col, row, this.picture.get(col, row + 1));
            }
        }

        this.picture = p;

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam, this.height());
        validateLength();

        Picture p = new Picture(this.width() - 1, this.height());
        p.setOriginUpperLeft();
        for (int row = 0; row < this.height(); row++) {
            for (int col = 0; col < seam[row]; col++) {
                p.set(col, row, this.picture.get(col, row));
            }
            for (int col = seam[row]; col < this.width() - 1; col++) {
                p.set(col, row, this.picture.get(col + 1, row));
            }
        }

        this.picture = p;
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
