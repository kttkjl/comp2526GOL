package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

public class Herbivore extends Entity{
    private static ArrayList<Herbivore> allH = new ArrayList<Herbivore>();
    private static int totalNum = allH.size();
    private final int MAXHP = 10;
    private int hitPoint;
    private boolean moved;
    
    public Herbivore(Cell cell) {
        super(cell);
    }

    /**
     * Sets the background to Yellow.
     */
    public void init() {
        setEntity(EntityType.HERBIVORE);
        this.hitPoint = MAXHP;
        allH.add(this);
        this.moved = false;
    }
   
    /**
     * Removes a given cell's reference to a Plant object. 
     * resets hitPoint
     * @param plant
     */
    public void eatPlant(Cell cell) {
        cell.removePlant();
        resetHp();
    }
    
    public void minusHitpoint() {
        this.hitPoint--;
    }
    /**
     * Resets the current Herbivore HP to MAXHP
     */
    public void resetHp() {
        this.hitPoint = MAXHP;
    }
    
    public int getHp() {
        return this.hitPoint;
    }
    
//    /**
//     * Moves the herbivore to a new cell, prioritizes plants over empty cell
//     */
//    public void setLocation(Cell c) {
//        this.setLocation(c);
//    }
    
    public static Herbivore[] getAllHerbivores() {
        return allH.toArray(new Herbivore[allH.size()]);
    }
    
    public static void removeFromAllHerbs(Herbivore h) {
        allH.remove(h);
    }
   
   public void setMoved(boolean b) {
       this.moved = b;
   }
   
   /**
    * Moves the herbivore, eats plant if exists.
    */
   public void moveHerbivore() {
       //Grabbing adj cells, put in 
       Cell[] adjCells = this.getEntityCell().getAdjacentCells(1);
       Cell[] validCs = new Cell[adjCells.length];
       Cell newCell = null;
       int validCInd = 0;
       for (Cell cell : adjCells) {
           if (cell == null) {
               break;
           }
           //if this adj cell has an herbivore
           if(cell.getHerbivore() != null) {
               continue;
           }
           else {
               validCs[validCInd] = cell;
               validCInd++;
           }
       }
       //RandomGenerator implementation of a random cell to move into
       if (validCInd > 0) {
           if (validCInd == 1) {
               newCell = validCs[0];
           } else {
               newCell = getPriorityCell(validCs, EntityType.PLANT, validCInd);
           }
           move(this.getEntityCell(), newCell);
           eatPlant(newCell);
       }
   }
   
   /**
    * Selects a Cell of the prioritized EntityType, if exists
    * @param cells
    * @param et
    * @param size
    * @return
    */
   private Cell getPriorityCell(Cell[] cells, EntityType et, int size) {
       int pInd = 0, otherInd = 0;
       Cell [] pCells = new Cell[size], otherCells = new Cell[size];
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
    * Selects priority Cells if array not null
    * @param pCells         array of prioritized Cells
    * @param pInd           number of elements in pCells
    * @param otherCells     array of non-priority Cells
    * @param oInd           number of elements in non-p Cells
    * @return
    */
   private Cell selectPriority(Cell[] pCells, int pInd, 
           Cell[] otherCells, int oInd) {
       Cell priority = null;
       if(pInd > 0) {
               priority = pCells[RandomGenerator.nextNumber(pInd)];
       } else if (oInd > 0){
               priority = otherCells[RandomGenerator.nextNumber(oInd)];
       }
       return priority;
   }
}
