package ca.bcit.comp2526.a2a;

/**
 * An object with x and y coordinates of a 2D array.
 * @author Jacky
 * @version 1.0a
 */
public class LocationPoint {
    private int row;
    private int col;
    
    /**
     * Creates a LocationPoint at given row and column.
     * @param r   row # in the 2d array.
     * @param c   column # in the 2d array.
     */
    public LocationPoint(int r, int c) {
        this.row = r;
        this.col = c;
    }
    
    /**
     * Sets the LocationPoint object to specified params.
     * @param r   row # in the 2d array.
     * @param c   column # in the 2d array.
     */
    public void setPoint(int r, int c) {
        this.row = r;
        this.col = c;
    }
    
    /**
     * Gets the row# of this LocationPoint.
     * @return  (int) the row# of this LocationPoint.
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * Gets the column# of this LocationPoint.
     * @return  (int) the column# of this LocationPoint.
     */
    public int getCol() {
        return this.col;
    }
}
