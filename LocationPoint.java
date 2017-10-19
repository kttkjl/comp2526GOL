package ca.bcit.comp2526.a2a;

public class LocationPoint {
    private int row;
    private int col;
    
    public LocationPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public void setPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
}
