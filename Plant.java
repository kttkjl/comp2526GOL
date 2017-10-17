package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Plant extends Entity{
    /**
     * Creates a Plant Object.
     * @param location
     */
    public Plant(Cell cell) {
        super(cell);
    }
    /**
     * Sets the background to Green.
     */
    public void init() {
        super.setColor(Color.GREEN);
        super.setEntity(EntityType.PLANT);
    }
    
    /**
     * Puts the Plant on the specified Cell.
     * @param location
     */
    public void setCell(Cell location) {
        
    }

}
