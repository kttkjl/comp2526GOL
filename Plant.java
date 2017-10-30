package ca.bcit.comp2526.a2a;

public class Plant extends Entity{
    private boolean justSeeded;
    
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
    public Plant init() {
        setEntity(EntityType.PLANT);
        this.justSeeded = true;
        return this;
    }
    
    public boolean justSeeded() {
        return this.justSeeded;
    }
    /**
     * Puts the Plant on the specified Cell.
     * @param location
     */
    public void setCell(Cell location) {
        
    }

}
