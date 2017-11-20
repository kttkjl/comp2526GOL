package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * Creates an Entity of type Herbivore.
 * @author Jacky
 * @version 1.0a
 */
public class Herbivore extends LifeForm implements CarnivoreEdible, 
OmnivoreEdible {
    private static final int MAXHP = 10;
    private static final int PARTNERREQ = 2;
    private static final int EMPTYREQ = 1;
    private static final int FOODREQ = 2;
    
    /**
     * Creates a Herbivore object.
     * @param cell  the cell which this Herbivore is created in
     */
    public Herbivore(Cell cell) {
        super(cell);
    }
    
    /**
     * Inits the Herbivore object.
     */
    public void init() {
        this.setHitPoint(MAXHP);
        this.setColor(Color.YELLOW);
        this.setEntityType(EntityType.HERBIVORE);
        this.setTurnTaken(true);
    }

    @Override
    boolean isFood(Entity e) {
        if (e instanceof HerbivoreEdible) {
            return true;
        }
        return false;
    }

    @Override
    void giveBirth(int eCount, int tCount, int fCount, Cell[] eCells) {
        if ((eCount >= EMPTYREQ) && (fCount >= FOODREQ) 
                && (tCount >= PARTNERREQ)) {
            Cell tc = eCells[RandomGenerator.nextNumber(eCount)];
            Herbivore h = new Herbivore(tc);
            h.init();
            tc.setEntity(h);
//            System.out.println("HerbivoreBirth");
        }
    }

    @Override
    void resetLife() {
        // TODO Auto-generated method stub
        this.setHitPoint(MAXHP);
    }
}
