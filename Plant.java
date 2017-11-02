package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * a Plant object that resides inside a Cell. 
 * Can duplicate under certain conditions.
 * @author Jacky
 * @version 1.0b
 */
public class Plant extends Entity {
    private static ArrayList<Plant> allP = new ArrayList<Plant>();
    private static int totalNum;
    private boolean justSeeded = true;
    
    /**
     * Creates a Plant Object.
     * @param cell the Cell in which this Plant resides
     */
    public Plant(Cell cell) {
        super(cell);
        setEntity(EntityType.PLANT);
        totalNum++;
    }
    
    /**
     * Sets that this Plant is not just seeded.
     * @return the newly created Plant object
     */
    public Plant init() {
        allP.add(this);
        this.justSeeded = false;
        return this;
    }
    
    /**
     * Determines if this Plant object is just created.
     * @return true if Plant newly seeded.
     */
    public boolean justSeeded() {
        return this.justSeeded;
    }
    
    /**
     * Removes this herbivore from index of herbivores.
     */
    public void removeFromAllPlants() {
        allP.remove(this);
        totalNum--;
    }
    
    /**
     * Grabs all Plants.
     * @return an array of all Plants
     */
    public static Plant[] getAllPlants() {
        return allP.toArray(new Plant[allP.size()]);
    }
    
    /**
     * Returns the total amount of Plants.
     * @return total amount of Plants.
     */
    public static int getTotalNum() {
        return totalNum;
    }
}
