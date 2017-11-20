package ca.bcit.comp2526.a2a;

import java.awt.Color;

/**
 * OM NOM NOM NOMNIVORE.
 * @author Jacky
 * @version 2B
 */
public class Omnivore extends LifeForm implements CarnivoreEdible {
    private static final int MAXHP = 2;
    private static final int PARTNERREQ = 1;
    private static final int EMPTYREQ = 3;
    private static final int FOODREQ = 3;
    
    /**
     * Constructor.
     * @param c the cell this Omnivore is in
     */
    public Omnivore(Cell c) {
        super(c);
    }
    
    /**
     * Inits this Omnivore.
     */
    public void init() {
        this.setHitPoint(MAXHP);
        setColor(Color.BLUE);
        this.setTurnTaken(true);
        this.setEntityType(EntityType.OMNIVORE);
    }
    
    /**
     * Omnivore reproduction method.
     * @param eCount - number of empty cells.
     * @param tCount - number of targeted cells.
     * @param fCount - number of food available
     * @param eCells - the array of empty cells to giveBirth into.
     */
    public void giveBirth(int eCount, int tCount, int fCount,
            Cell[] eCells) {
        if ((eCount >= EMPTYREQ) && (fCount >= FOODREQ) 
                && (tCount >= PARTNERREQ)) {
            Cell tc = eCells[RandomGenerator.nextNumber(eCount)];
            Omnivore o = new Omnivore(tc);
            o.init();
            tc.setEntity(o);
//            System.out.println("omnivoreBirth");
        }
    }

    @Override
    boolean isFood(Entity e) {
        // If both Herbivore and Carnivore can eat it, no cannibalism though.
        if (((e instanceof HerbivoreEdible) || (e instanceof CarnivoreEdible)) 
                && (!(e instanceof Omnivore))) {
            return true;
        }
        return false;
    }

    @Override
    void resetLife() {
        this.setHitPoint(MAXHP);
    }
    
    
}
