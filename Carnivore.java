package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * Carnivore object that eats other animals, except themselves.
 * @author Jacky
 * @version 2B
 */
public class Carnivore extends LifeForm implements CarnivoreEdible, 
OmnivoreEdible {
    private static final int MAXHP = 7;
    private static final int PARTNERREQ = 2;
    private static final int EMPTYREQ = 2;
    private static final int FOODREQ = 2;
    
    /**
     * Creates a Herbivore object.
     * @param cell - the cell which this Herbivore is created in
     */
    public Carnivore(Cell cell) {
        super(cell);
    }
    
    /**
     * Inits the Carnivore object.
     */
    public void init() {
        this.setHitPoint(MAXHP);
        setColor(Color.MAGENTA);
        this.setTurnTaken(true);
        this.setEntityType(EntityType.CARNIVORE);
    }
    
    /**
     * Gives birth to a new Carnivore object.
     * @param eCount - number of empty cells.
     * @param tCount - number of targeted cells.
     * @param fCount - number of food available
     * @param eCells - the array of empty cells to giveBirth into.
     */
    void giveBirth(int eCount, int tCount, int fCount,
            Cell[] eCells) {
        if ((eCount >= EMPTYREQ) && (fCount >= FOODREQ) 
                && (tCount >= PARTNERREQ)) {
            Cell tc = eCells[RandomGenerator.nextNumber(tCount)];
            Carnivore c = new Carnivore(tc);
            c.init();
            tc.setEntity(c);
        }
    }

    @Override
    boolean isFood(Entity e) {
        if ((e instanceof CarnivoreEdible) && (!(e instanceof Carnivore))) {
            return true;
        }
        return false;
    }

    @Override
    void resetLife() {
        setHitPoint(MAXHP);
    }
    
}
