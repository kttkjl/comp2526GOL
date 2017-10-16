package ca.bcit.comp2526.a2a;

public class Herbivore extends Entity{
    private static int totalNum = 0;
    public Cell location;
    private int hitPoint;
    
    public Herbivore(Cell location) {
        totalNum++;
        this.location = location;
    }
    /**
     * Sets the background to Yellow.
     */
    public void init() {
        
    }
    
    /**
     * Puts the Herbivore to the specified Cell
     * @param location
     */
    public void setCell(Cell location) {
        
    }
    
    /**
     * Moves the Herbivore one cell
     * Eats plant if cell has a plant
     */
    public void move() {
        
    }
}
