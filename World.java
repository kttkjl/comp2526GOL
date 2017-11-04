package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * Creates a world which hosts Cells.
 * @author Jacky
 * @version 1.0a
 */
public class World {
    private final int rows;
    private final int columns;
    private Cell[][] cells;
    
    /**
     * Constructs the world.
     * Holds Cells
     * Using:
     * @param rows  : allowed y coords.
     * @param cols  : allowed x coords.
     * 
     */
    public World(int rows, int cols) {
        super();
        this.rows = rows;
        this.columns = cols;
        this.cells = new Cell[rows][cols];
    }
    
    /**
     * Puts the Cells on the World and adds Herbivores + Plants via 
     * Cell.init()
     * for the initial condition of the world.
     *      When new World created, all cells have 
     *          20% chance = Herbivore
     *          30% chance = Plant
     */
    public void init() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                Cell newCreatedCell = new Cell(this, row, col);
                newCreatedCell.init();
                this.cells[row][col] = newCreatedCell;
            }
        }
    }
    
    /**
     * Returns the Cell object at.
     * @param col (x coordinate),
     * @param row (y coordinate).
     * @return Cell the cell to return at given coordinate.
     */
    public Cell getCellAt(int row, int col) {
        return this.cells[row][col];
    }
    
    /**
     * Removes dead herbivores.
     * Checks plant cond. for growth.
     * Moves all living Herbivores 1 cell randomly.
     *      Eat plant if plant cell.
     */
    public void takeTurn() {
        //Removes dead Herbivores from this World object.
        removeDeadHerbivores();
        
        //Seeding Phase
        seedCells();
        
        //All herbivores take a turn.
        herbivoreTakeTurn();
    }
    
    /**
     * Seeds all the Cells in the world.
     */
    private void seedCells() {
        ArrayList<Plant> newPlants = new ArrayList<Plant>();
        Plant[] plants = Plant.getAllPlants();
        for (Plant plant : plants) {
            if (!plant.justSeeded()) {
                Cell c = plant.getEntityCell();
                Cell[] cs = c.getSeedCells(c.getAdjacentCells(1));
                if (cs != null) {
                    c = cs[RandomGenerator.nextNumber(cs.length)];
                    c.seedCell();
                    newPlants.add(c.getPlant());
                }
            }
        }
        for (Plant p : newPlants) {
            if (p != null) {
                p.init();
            }
        }
    }
    
    
//    /**
//     * Gets an Array of Cells to be Seeded.
//     * @return Array of Cells to be Seeded.
//     */
//    private Cell[] getCellsToSeed() {
//        //Loop through all cells in the world
//        int retIndex = 0;
//        Cell[] retCells = new Cell[rows * columns];
//        for (int row = 0; row < this.rows; row++) {
//            for (int col = 0; col < this.columns; col++) {
//                //Grabs each Cell, put as workingCell.
//                Cell workingCell = getCellAt(col, row);
//                Plant p = workingCell.getPlant();
//                
//                if ((p !=  null) && (!p.justSeeded())) {
//                    System.out.println("Curr cell plant, "
//                            + "check for seedable adjs");
//                    Cell[] seedables = workingCell.getSeedCells(
//                            workingCell.getAdjacentCells(1));
//                    if (seedables != null) {
//                        Cell newCell = seedables[RandomGenerator.nextNumber(
//                              seedables.length)];
//                        newCell.setSeededStatus(true);
//                        retCells[retIndex] = newCell;
//                        retIndex++;
//                    }
//                    System.out.println("finished getCellsToSeed");
//                }
//            }
//        }
//        return retCells;
//    }
//    
//    /**
//     * Seeds the cells given.
//     * @param givenCells the cells to be seeded
//     */
//    private void seedCells(Cell[] givenCells) {
//        for (Cell cell : givenCells) {
//            if (cell != null) {
//                cell.seedCell();
//            }
//        }
//    }
    
    /**
     * Set a Cell's reference to a Herbivore to null if Herbivore is dead.
     * updates the world's cellsContHerbivore
     */
    public void removeDeadHerbivores() {
        Herbivore[] herbs = Herbivore.getAllHerbivores();
        for (Herbivore h: herbs) {
            if (h.isDead()) {
                //removes the Cell's ref to the Herbivore object
                h.getEntityCell().removeHerbivore();
                //remove this herbivore from the static all herbivore list
                h.removeFromAllHerbs();
            }
        }
    }
    
    /**
     * Takes care of this world's Herbivores.
     */
    private void herbivoreTakeTurn() {
        Herbivore[] herbs = Herbivore.getAllHerbivores();
        for (Herbivore h : herbs) {
            //Moves all Herbivore, eat Plant if exists. 
            if (!h.haveMoved()) {
                h.move();
                h.eatPlant();
            }
            //Whack
            h.minusHitPoint();
        }
        for (Herbivore h : herbs) {
            h.setMoved(false);
        }
    }
    
    /**
     * Gets the number of rows in the World.
     * @return INT the x-max of the world.
     */
    public int getRowCount() {
        return this.rows;
    }
    
    /**
     * Gets the number of columns in the World.
     * @return INT the y-max of the world
     */
    public int getColumnCount() {
        return this.columns;
    }
    
    /**
     * Return the entire 2d Cell array of this world.
     * @return Cell[][] 
     */
    public Cell[][] getCells() {
        return this.cells;
    }
}
