package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * A Master class of Object that resides in a Cell Object in a World.
 * @author Jacky
 * @version a2b
 */
public class Entity {
    private Cell location;
    //Add more as needed.
    private EntityType entityType;
    private Color color;
    private boolean turnTaken = true;
    
    
    /**
     * Constructor.
     * @param cell - the cell this entity is created in.
     */
    public Entity(Cell cell) {
        super();
        this.location = cell;
    }
    
    /**
     * Takes a turn.
     */
    public void takeTurn() {

    }
    
    /**
     * Sets the color of this Entity.
     * @param c Color object.
     */
    public void setColor(Color c) {
        this.color = c;
    }
    
    /**
     * Returns the color of this Entity.
     * @return the color of this Entity.
     */
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Sets the entityType of this Entity.
     * @param en the EntityType to set this Entity
     */
    public void setEntityType(EntityType en) {
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
    public Cell getLocation() {
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
       oldC.removeEntity();
       this.setLocation(newC);
       newC.removeEntity();
       newC.setEntity(this);
    }


    /**
     * Returns true if this Entity had already taken a turn.
     * @return the turnTaken
     */
    public boolean isTurnTaken() {
        return turnTaken;
    }

    /**
     * Sets if this Entity already finished with turn.
     * @param turnTaken the turnTaken to set
     */
    public void setTurnTaken(boolean turnTaken) {
        this.turnTaken = turnTaken;
    }


    
}
