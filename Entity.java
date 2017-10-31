package ca.bcit.comp2526.a2a;

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
     * @param en
     */
    public void setEntity(EntityType en) {
        this.entityType = en;
    }

    /**
     * @return the entityType
     */
    public EntityType getEntityType() {
        return entityType;
    }
    
    /**
     * @return where this entity is located, in a Cell object
     */
    public Cell getEntityCell() {
        return location;
    }
    
    /**
     * Sets the cell this entity is located in.
     * @param c
     */
    public void setLocation(Cell c) {
        location = c;
    }

}
