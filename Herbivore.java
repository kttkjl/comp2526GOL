package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * Creates an Entity of type Herbivore.
 * @author Jacky
 * @version 1.0a
 */
public class Herbivore extends Entity {
    private static ArrayList<Herbivore> allH = new ArrayList<Herbivore>();
    private static int totalNum;
    private static final int MAXHP = 10;
    private static final int DECREMENT = 1;
    private int hitPoint;
    private boolean moved;


    /**
     * Creates a Herbivore object.
     * @param cell  the cell which this Herbivore is created in
     */
    public Herbivore(Cell cell) {
        super(cell);
        setEntity(EntityType.HERBIVORE);
        totalNum++;
    }
    
    /**
     * Inits the Herbivore object.
     */
    public void init() {
        this.hitPoint = MAXHP;
        allH.add(this);
        this.moved = false;
    }
    
    /**
     * Removes a given cell's reference to a Plant object. resets hitPoint.
     */
    public void eatPlant() {
        Cell cell = this.getEntityCell();
        Plant p = cell.getPlant();
        if (p != null) {
            cell.removePlant();
            p.removeFromAllPlants();
            resetHp();
        }
    }

    /**
     * Decrements the HitPoint of this Herbivore by 1.
     */
    public void minusHitPoint() {
        this.hitPoint -= DECREMENT;
    }

    /**
     * Resets the current Herbivore HP to MAXHP.
     */
    public void resetHp() {
        this.hitPoint = MAXHP;
    }

    /**
     * Grabs all alive Herbivores.
     * @return an array of Herbivores
     */
    public static Herbivore[] getAllHerbivores() {
        return allH.toArray(new Herbivore[allH.size()]);
    }

    /**
     * Removes this herbivore from index of herbivores.
     */
    public void removeFromAllHerbs() {
        allH.remove(this);
        totalNum--;
    }
    
    /**
     * Returns true if herbivore already moved this turn.
     * @return true if this Herbivore already moved
     */
    public boolean haveMoved() {
        return this.moved;
    }
    
    /**
     * Sets the herbivore's moved status.
     * @param b return true if Herbivore already moved
     */
    public void setMoved(boolean b) {
        this.moved = b;
    }
    
    /**
     * Checks to see if this herbivore is dead.
     * @return true if dead, false if alive
     */
    public boolean isDead() {
        if (this.hitPoint <= 0) {
            return true;
        }
        return false;
    };
    
    /**
     * Moves the herbivore, returns the new Cell the herbivore moved to.
     * @return the Cell the herbivore is moving into
     */
    public Cell move() {
        // Grabbing adj cells, put in
        Cell[] adjCells = this.getEntityCell().getAdjacentCells(1);
        Cell[] validCs = new Cell[adjCells.length];
        Cell newCell = null;
        int validCInd = 0;
        for (Cell cell : adjCells) {
            if (cell == null) {
                break;
            }
            // if this adj cell has an herbivore
            if (cell.getHerbivore() != null) {
                continue;
            } else {
                validCs[validCInd] = cell;
                validCInd++;
            }
        }
        if (validCInd > 0) {
            if (validCInd == 1) {
                newCell = validCs[0];
            } else {
                newCell = getPriorityCell(validCs, EntityType.PLANT, validCInd);
            }
            super.move(getEntityCell(), newCell);
            setMoved(true);
        }
        return newCell;
    }

    /**
     * Selects a Cell of the prioritized EntityType, if exists.
     * @param cells given array of cells to select from
     * @param et    EntityType to prioritize
     * @param size  the given cells array size
     * @return the randomly selected Cell to be prioritized to
     */
    private Cell getPriorityCell(Cell[] cells, EntityType et, int size) {
        int pInd = 0;
        int otherInd = 0;
        Cell[] pCells = new Cell[size];
        Cell[] otherCells = new Cell[size];
        for (Cell c : cells) {
            if (c == null) {
                break;
            } else if (c.getEntity(et) != null) {
                pCells[pInd] = c;
                pInd++;
            } else {
                otherCells[otherInd] = c;
                otherInd++;
            }
        }
        return selectPriority(pCells, pInd, otherCells, otherInd);
    }

    /**
     * Selects a Cell from 2 lists: priority, and non-priority. 
     * Selects from priority cell array if not empty.
     * 
     * @param pCells
     *            array of prioritized Cells
     * @param pInd
     *            number of elements in pCells
     * @param otherCells
     *            array of non-priority Cells
     * @param oInd
     *            number of elements in non-p Cells
     * @return the Cell to be returned.
     */ 
    private Cell selectPriority(Cell[] pCells, int pInd, 
            Cell[] otherCells, int oInd) {
        Cell priority = null;
        if (pInd > 0) {
            priority = pCells[RandomGenerator.nextNumber(pInd)];
        } else if (oInd > 0) {
            priority = otherCells[RandomGenerator.nextNumber(oInd)];
        }
        return priority;
    }
    
    /**
     * Returns the total amount of alive herbivores.
     * @return total number of alive Herbivores.
     */
    public static int countTotalAlive() {
        return totalNum;
    }
}
