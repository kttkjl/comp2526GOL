package ca.bcit.comp2526.a2a;

public class LocationPoint {
    private int x;
    private int y;
    
    public LocationPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
