package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

public class World {
    private Cell[][] cells;
    //Index that contains all cells with active herbivores in the world.
    private ArrayList<Cell> cellsContHerbivore;
    private ArrayList<Cell> newlySeededCells;
    protected final int ROWS;
    protected final int COLUMNS;
    /**
     * Constructs the world.
     * Holds Cells
     * Using:
     * @param rows  : allowed y coords.
     * @param cols  : allowed x coords.
     * 
     */
    public World (int rows, int cols) {
        super();
        this.ROWS = rows;
        this.COLUMNS = cols;
        this.cells = new Cell[rows][cols];
        this.cellsContHerbivore = new ArrayList<Cell>(10);
    }
    
    /**
     * Puts the Cells on the World and adds Herbivores + Plants via 
     * Cell.init()
     * for the initial condition of the world
     *      When new World created, all cells have 
     *          20% chance = Herbivore
     *          30% chance = Plant
     */
    public void init() {
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                Cell newCreatedCell = new Cell(this, row, col);
                newCreatedCell.init();
                this.cells[row][col] = newCreatedCell;
            }
        }
    }
    
    /**
     * Returns the Cell object at 
     * @param y column(x coordinate),
     * @param x row (y coordinate).
     * @return 
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
        System.out.println("removing dead herbivores");
        //removeDeadHerbivores();
        System.out.println("finished moving dead herbs");
        
        //Seeding Phase
        seedCells(getCellsToSeed());
        
      //Move Herbivores
        System.out.println("moving herbivores");
//        moveHerbivores();
        //Eat Plant if exists
    }
    
    /**
     * Moves all alive herbivores on the world 1 to a random adj cell.
     */
//    private void moveHerbivores() {
//        //Moves the Herbivore into cell without another Herbivore
//        for (Cell c : cellsContHerbivore) {
//            Cell [] validCells = c.getValidMovePoint(c.getAdjacentCells(1));
//            
//        }
//    }
    /**
     * gets an Array of Cells to be Seeded.
     * @return Array of Cells to be Seeded.
     */
    private Cell[] getCellsToSeed() {
        //Loop through all cells in the world
        int retIndex = 0;
        Cell[] retCells = new Cell[ROWS*COLUMNS];
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                //Grabs each Cell, put as workingCell.
                Cell workingCell = getCellAt(col, row);  
                if(workingCell.getPlant() != null) {
                    //Seed cells:Check the current working cell for seed-able cells
                    System.out.println("Curr cell plant, check for seedable adjs");
                    Cell[] seedables = workingCell.getSeedCells(
                            workingCell.getAdjacentCells(1));
                    if (seedables != null) {
                        Cell newCell = seedables
                                [(int) (Math.random() * (seedables.length - 1))];
                        newCell.setSeededStatus(true);
                        retCells[retIndex] = newCell;
                        retIndex++;
                    }
                    System.out.println("finished getCellsToSeed");
                }
            }
        }
        return retCells;
    }
    
    /**
     * Seeds the cells given.
     * @param cells
     */
    private void seedCells(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell != null) {
                cell.seedCell();
            }
        }
    }
    
    /**
     * Set a Cell's reference to a Herbivore to null if Herbivore is dead.
     * updates the world's cellsContHerbivore
     */
    public void removeDeadHerbivores() {
        for (Cell c : cellsContHerbivore) {
            if(c.getHerbivore().getHp() <= 0) {
                //removes the Cell's ref to the Herbivore object
                c.removeHerbivore();
                //removes the cell from world's Herbivore Index reference
                cellsContHerbivore.remove(c);
                cellsContHerbivore.trimToSize();
            }
        }
    }
    
    /**
     * Gets the number of rows in the World.
     * @return INT the x-max of the world.
     */
    public int getRowCount() {
        return this.ROWS;
    }
    
    /**
     * Gets the number of columns in the World    
     * @return INT the y-max of the world
     */
    public int getColumnCount() {
        return this.COLUMNS;
    }
    
    public Cell[][] getCells() {
        return this.cells;
    }
}
