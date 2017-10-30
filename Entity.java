package ca.bcit.comp2526.a2a;

public class Entity {
    private Cell location;
    //Add more as needed.
    private EntityType entityType;
    
    public Entity(Cell cell) {
        super();
        this.location = cell;
    }
    
    public void setEntity(EntityType en) {
        this.entityType = en;
    }
    
    /**
     * Checks if given entity array
     * @param en        contains an object with the
     * @param name      name.
     * @return
     */
//    public boolean containsEntity(Entity[] en, String name) {
//        
//        return true;
//    }

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
     * updates the current Cell to a 
     * @param newlocation Cell
     */
    public void updateLocation(Cell newlocation) {
        this.location = newlocation;
    }
}
