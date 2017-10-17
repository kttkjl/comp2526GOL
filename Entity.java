package ca.bcit.comp2526.a2a;

import java.awt.Color;

public class Entity {
    private Cell location;
    private Color color;
    //Add more as needed.
    private EntityType et;
    
    public Entity(Cell cell) {
        super();
        this.location = cell;
    }
    
    public void init() {
        
    }
    
    public void setEntity(EntityType en) {
        this.et = en;
    }
    
    public void setColor(Color c) {
        this.color = c;
    }
    
    /**
     * Checks if given entity array
     * @param en        contains an object with the
     * @param name      name.
     * @return
     */
    public boolean containsEntity(Entity[] en, String name) {
        
        return true;
    }
    
}
