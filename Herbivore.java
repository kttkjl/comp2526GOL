package ca.bcit.comp2526.a2a;

public class Herbivore extends Entity{
    private static int totalNum = 0;
    private final int MAXHP = 10;
    private int hitPoint;
    
    public Herbivore(Cell cell) {
        super(cell);
    }

    /**
     * Sets the background to Yellow.
     */
    public void init() {
        setEntity(EntityType.HERBIVORE);
        totalNum++;
        this.hitPoint = MAXHP;
    }
   
    /**
     * Removes a given cell's reference to a Plant object. 
     * resets hitPoint
     * @param plant
     */
    public void eatPlant(Cell cell, Plant plant) {
        cell.removePlant();
        resetHp();
    }
    
    public void minusHitpoint() {
        this.hitPoint--;
    }
    /**
     * Resets the current Herbivore HP to MAXHP
     */
    public void resetHp() {
        this.hitPoint = MAXHP;
    }
    
    public int getHp() {
        return this.hitPoint;
    }
    /**
     * Moves the Herbivore one cell
     * Eats plant if cell has a plant
     */
    public void move(Cell c) {
        updateLocation(c);
    }
}
