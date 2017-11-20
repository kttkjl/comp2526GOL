package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * a Plant object that resides inside a Cell. 
 * Can duplicate under certain conditions.
 * @author Jacky
 * @version 1.0b
 */
public class Plant extends LifeForm implements HerbivoreEdible, OmnivoreEdible {
    
    private static final int PARTNERREQ = 2;
    private static final int EMPTYREQ = 3;
    private static final int FOODREQ = 0;
    
    /**
     * Creates a Plant Object.
     * @param cell the Cell in which this Plant resides
     */
    public Plant(Cell cell) {
        super(cell);
    }
    
    /**
     * Sets that this Plant is not just seeded.
     */
    public void init() {
        this.setHitPoint(1);
        this.setColor(Color.GREEN);
        this.setEntityType(EntityType.PLANT);
        this.setTurnTaken(true);
    }
    
    /**
     * Takes a turn for this plant.
     */
    public void takeTurn() {
        if (!isTurnTaken()) {
            reproduce();
            this.setTurnTaken(true);
        }
    }

    /**
     * Plant moves, doesn't move.
     */
    public void move() {
        
    }

    @Override
    boolean isFood(Entity e) {
        // Nothing is food for Plant, so far, leave empty.
        return false;
    }

    @Override
    void giveBirth(int eCount, int tCount, int fCount, Cell[] eCells) {
        if ((eCount >= EMPTYREQ) && (fCount >= FOODREQ) 
                && (tCount >= PARTNERREQ)) {
            Cell tc = eCells[RandomGenerator.nextNumber(eCount)];
            Plant p = new Plant(tc);
            p.init();
            tc.setEntity(p);
//            System.out.println("PlantBirth");
        }
    }

    @Override
    void resetLife() {
        // We assume plant have infinite life here, 
        // takeTurn() is also overridden.
    }
}
