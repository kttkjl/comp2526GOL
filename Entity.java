package ca.bcit.comp2526.a2a;

/**
 * A Master class of Object that resides in a Cell Object in a World.
 * @author Jacky
 * @version 1.0a
 */
public class Entity {
    private Cell location;
    //Add more as needed.
    private EntityType entityType;
    
    /**
     * Constructor.
     * @param cell - the cell this entity is created in.
     */
    public Entity(Cell cell) {
        super();
        this.location = cell;
    }
    
    /**
     * Sets the entityType of this Entity.
     * @param en the EntityType to set this Entity
     */
    public void setEntity(EntityType en) {
        this.entityType = en;
    }

    /**
     * Gets the EntityType of this Entity.
     * @return the entityType
     */
    public EntityType getEntityType() {
        return entityType;
    }
    
    /**
     * Returns the Cell where this Entity is located.
     * @return where this entity is located, in a Cell object
     */
    public Cell getEntityCell() {
        return location;
    }
    
    /**
     * Sets the cell this entity is located in.
     * @param c the Cell this Entity is going to reside in.
     */
    public void setLocation(Cell c) {
        location = c;
    }
    
    /**
    * Moves the Entity.
    * @param oldC     reference to the old Cell
    * @param newC     reference to the new Cell
    */
   protected void move(Cell oldC, Cell newC) {
       oldC.removeHerbivore();
       this.setLocation(newC);
       newC.insertEntity(this);
   }

}
