package ca.bcit.comp2526.a2a;

/**
 * Alive things.
 * @author Jacky
 * @version 2B
 */
abstract class LifeForm extends Entity {

    private int hitPoint;
    
    /**
     * Constructor.
     * @param c - the cell where this LifeForm resides.
     */
    LifeForm(Cell c) {
        super(c);
    }
    
    /**
     * This lifeform takes a turn.
     */
    public void takeTurn() {
        if (!isTurnTaken()) {
            hitPoint--;
            move();
            reproduce();
            this.setTurnTaken(true);
        }
    }

    /**
     * Finds an array of Cells containing food for this LifeForm.
     * @param e - the Entity to be checked.
     * @return  an array of Cells that contain food.
     */
    abstract boolean isFood(Entity e);
    
    /**
     * Generic move method.
     */
    public void move() {
        Cell[] adj = this.getLocation().getAdjacentCells(1);
        int eCount = 0;
        int tCount = 0; 
        Cell[] eCells = new Cell[adj.length];
        Cell[] tCells = new Cell[adj.length];
        for (Cell c : adj) {
            if (c == null) {
                break;
            }
            if (isFood(c.getEntity())) {
               tCells[tCount++] = c;
            } else if (c.getEntity() == null) {
               eCells[eCount++] = c;
            }
        }
        move(eCount, tCount, eCells, tCells);
    }
    
    /**
     * Generic move method.
     * @param eCount - the empty Cell count.
     * @param tCount - the Entity Target to be tagetted count
     * @param eCells - the array of Cells that have null entity
     * @param tCells - the array of Cells that have target entity
     */
    private void move(int eCount, int tCount, Cell[] eCells, 
            Cell[] tCells) {
        if (eCount <= 0) {
            return;
        } else if (tCount > 0) {
            Cell target = tCells[RandomGenerator.nextNumber(tCount)];
            eat(target);
            super.move(this.getLocation(), target);
        } else if (eCount > 0) {
            super.move(this.getLocation(), 
                    eCells[RandomGenerator.nextNumber(eCount)]);
        }
    }

    /**
     * Generic LifeForm reproduction.
     */
    public void reproduce() {
        Cell[] adjCells = this.getLocation().getAdjacentCells(1);
        int emptyCount = 0;
        int targetCount = 0;
        int foodCount = 0;
        Cell[] emptyCells = new Cell[adjCells.length];
        Cell[] targetCells = new Cell[adjCells.length];
        for (Cell c : adjCells) {
            if (c == null) {
                break;
            }
            Entity en = c.getEntity();
            if (en == null) {   //Check null first
                emptyCells[emptyCount++] = c;
            } else if (this.possibleMate(c.getEntity())) {
                targetCells[targetCount++] = c;
            } else if (isFood(c.getEntity())) {
                foodCount++;
            }
        }
        if ((emptyCount != 0) && (targetCount != 0)) {
            giveBirth(emptyCount, targetCount, foodCount, emptyCells);
        }
    }
    
    /**
     * Generic give birth prototype.
     * @param eCount - number of empty cells.
     * @param tCount - number of targeted cells.
     * @param fCount - number of food available
     * @param eCells - the array of empty cells to giveBirth into.
     */
    abstract void giveBirth(int eCount, int tCount, int fCount,
            Cell[] eCells);
    
    /**
     * Checks if given Entity is a possible mate, with some options.
     * @param en - the entity to check.
     * @return true if the given Entity is a possible mate
     */
    protected boolean possibleMate(Entity[] en) {
        for (Entity e: en) {
            if (this.getClass().isInstance(e)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if given Entity is a possible mate, Strictly with
     * members of the same LifeForm.
     * @param en - the entity to check.
     * @return true if the given Entity is a possible mate
     */
    protected boolean possibleMate(Entity en) {
        return (this.getClass().isInstance(en));
    }

    /**
     * Eats the entity inside given cell.
     * @param c - the given cell to eat from
     */
    private void eat(Cell c) {
        //Set the existing entity's reference to cell to null.
        c.getEntity().setLocation(null);
        //Sets the Cell's existing entity to null.
        c.setEntity(null);
        //Abstract reset life for this LifeForm.
        this.resetLife();
    }
    /**
     * Resets the Life of this LifeForm to whatever the LF's maximum health is.
     */
    abstract void resetLife();

    /**
     * Gets the HitPoint of this LifeForm.
     * @return the hitPoint.
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * Sets the HitPoint of this LifeForm.
     * @param hitPoint the hitPoint to set.
     */
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }
    
    /**
     * Checks if this lifeForm is dead.
     * @return true if LifeForm is dead.
     */
    public boolean checkDead() {
        if (hitPoint <= 0) {
            return true;
        }
        return false;
    }
    
}
