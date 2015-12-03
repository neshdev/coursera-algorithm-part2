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
        int[] seams = new int[this.width()];
        double[][] energy = new double[this.height()][this.width()];
        
        for (int row = 0; row < energy.length; row++) {
            for (int column = 0; column < energy[row].length; column++) {
                energy[row][column] = energy(column, row);
            }
        }
        
        int minRowIndex = -1;
        double minEnegy = Double.MAX_VALUE;
        for (int row = 0; row < this.height(); row++) {
            if ( minEnegy > energy[row][1] ){
                minEnegy = energy[row][1];
                minRowIndex = row;
            }
        }
        
        seams[0] = minRowIndex;
        seams[1] = minRowIndex;
        
        for (int column = 2; column < this.width() - 1; column++) {
            minRowIndex = getMinEnergyRowIndex(minRowIndex, column, energy);
            seams[column] = minRowIndex;
        }
        
        seams[this.width() - 1] = seams[this.width() - 2];
        
        return seams;
        
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam()
    {
        int[] seams = new int[this.height()];
        double[][] energy = new double[this.height()][this.width()];
        
        for (int row = 0; row < energy.length; row++) {
            for (int column = 0; column < energy[row].length; column++) {
                energy[row][column] = energy(column, row);
            }
        }
        
        int minColumnsIndex = -1;
        double minEnegy = Double.MAX_VALUE;
        for (int column = 0; column < this.width(); column++) {
            if ( minEnegy > energy[1][column] ){
                minEnegy = energy[1][column];
                minColumnsIndex = column;
            }
        }
        
        seams[0] = minColumnsIndex;
        seams[1] = minColumnsIndex;
        for (int row = 2; row < this.height() - 1; row++) {
            minColumnsIndex = getMinEnergyColumnIndex(row, minColumnsIndex, energy);
            seams[row] = minColumnsIndex;
        }
        
        seams[this.height() - 1] = seams[this.height() - 2];
        
        return seams;
    }
    
    private int getMinEnergyRowIndex(int row, int column, double[][] energy){
        int minRowIndex = -1;
        if ( row == 0){
            double midE = energy[row][column];
            double bottomE = energy[row + 1][column];
            minRowIndex = midE <= bottomE ? row : row + 1;
        } else if ( row == this.height() - 1) {
            double topE = energy[row - 1][column];
            double midE = energy[row][column];
            minRowIndex = midE <= topE ? row : row - 1;
        } else {
            double topE = energy[row - 1][column];
            double midE = energy[row][column];
            double bottomE = energy[row + 1][column];
            
            int midTopWinner = midE <= bottomE ? row : row + 1;
            int midBottomWinner = midE <= topE ? row : row - 1;
            minRowIndex = energy[midTopWinner][column] <= energy[midBottomWinner][column] ? midTopWinner : midBottomWinner;
        }
        return minRowIndex;
    }
    
    private int getMinEnergyColumnIndex(int row, int column, double[][] energy){
        int minColumnIndex = -1;
        if ( column == 0){
            double bottomE = energy[row][column];
            double rightE = energy[row][column + 1];
            minColumnIndex = bottomE <= rightE ? column : column + 1;
        } else if ( column == this.width() - 1) {
            double leftE = energy[row][column - 1];
            double bottomE = energy[row][column];
            minColumnIndex = bottomE <= leftE ? column : column - 1;
        } else {
            double leftE = energy[row][column - 1];
            double bottomE = energy[row][column];
            double rightE = energy[row][column + 1];
            
            int BottomRightWinner = bottomE <= rightE ? column : column +1;
            int BottomLeftWinner = bottomE <= leftE ? column : column - 1;
            minColumnIndex = energy[row][BottomRightWinner] <= energy[row][BottomLeftWinner] ? BottomRightWinner : BottomLeftWinner;
        }
        return minColumnIndex;
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
