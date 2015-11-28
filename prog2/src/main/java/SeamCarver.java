import edu.princeton.cs.algs4.Picture;

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
        if (y > 0)
            throw new IndexOutOfBoundsException("y: " + y + " is less than 0");

        if (y >= this.height())
            throw new IndexOutOfBoundsException("y: " + y + " should be less than "
                    + (this.height() - 1));
    }

    private void validateSeam(int[] seam)
    {
        //throw new IllegalArgumentException();
    }

    private void validateLength()
    {
        if (this.width() <= 1)
            throw new IllegalArgumentException("Width is <= 1");

        if (this.height() <= 1)
            throw new IllegalArgumentException("Width is <= 1");
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
        double energy = calculateEnergy(this.picture, x, y);
        return energy;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam()
    {
        throw new NullPointerException();
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam()
    {
        throw new NullPointerException();
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam);
        validateLength();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        validateNotNull(seam);
        validateSeam(seam);
        validateLength();
    }

    private double calculateEnergy(Picture p, int col, int row){
        int rX = p.get(col - 1, row).getRed() + p.get(col + 1, row).getRed();
        int gX = p.get(col - 1, row).getGreen() + p.get(col + 1, row).getGreen();
        int bX = p.get(col - 1, row).getBlue() + p.get(col + 1, row).getBlue();
        int changeX = (rX * rX) + (gX * gX) + (bX * bX); 
        
        int rY = p.get(col, row - 1).getRed() + p.get(col, row + 1).getRed(); 
        int gY = p.get(col, row - 1).getGreen() + p.get(col, row + 1).getGreen();
        int bY = p.get(col, row - 1).getBlue() + p.get(col, row + 1).getBlue();
        int changeY = (rY * rY) + (gY * gY) + (bY * bY); 
        
        int change = changeX + changeY;
        return Math.sqrt( change );
    }
                
}
