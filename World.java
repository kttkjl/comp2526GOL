package ca.bcit.comp2526.a2a;

/**
 * Creates a world which hosts Cells.
 * @author Jacky
 * @version 1.0a
 */
public class World {
    private Cell[][] cells;

    private final int ROWS, COLUMNS;
    
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
        this.ROWS = rows;
        this.COLUMNS = cols;
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
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
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
        System.out.println("removing dead herbivores");
        removeDeadHerbivores();
        System.out.println("finished moving dead herbs");
        
        //Seeding Phase
        seedCells(getCellsToSeed());
        System.out.println("Finished seeding cells");
        
        //All herbivores take a turn.
        System.out.println("moving herbivores");
        herbivoreTakeTurn();
        herbivoreResetMove();

    }
    
    /**
     * gets an Array of Cells to be Seeded.
     * @return Array of Cells to be Seeded.
     */
    private Cell[] getCellsToSeed() {
        //Loop through all cells in the world
        int retIndex = 0;
        Cell[] retCells = new Cell[ROWS * COLUMNS];
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                //Grabs each Cell, put as workingCell.
                Cell workingCell = getCellAt(col, row);  
                if (workingCell.getPlant() != null) {
                    System.out.println("Curr cell plant, "
                            + "check for seedable adjs");
                    Cell[] seedables = workingCell.getSeedCells(
                            workingCell.getAdjacentCells(1));
                    if (seedables != null) {
                        Cell newCell = seedables
                                [RandomGenerator.nextNumber(seedables.length)];
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
     * @param givenCells the cells to be seeded
     */
    private void seedCells(Cell[] givenCells) {
        for (Cell cell : givenCells) {
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
        Herbivore[] herbs = Herbivore.getAllHerbivores();
        for (Herbivore h: herbs) {
            if (h.isDead()) {
                System.out.println("HP below 10 detected");
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
            if(!h.haveMoved()) {
                h.eatPlant(h.moveHerbivore());
            }
            //Whack
            h.minusHitPoint();
        }
    }
    
    private void herbivoreResetMove() {
        Herbivore[] herbs = Herbivore.getAllHerbivores();
        for (Herbivore h : herbs) {
            h.setMoved(false);
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
     * Gets the number of columns in the World.
     * @return INT the y-max of the world
     */
    public int getColumnCount() {
        return this.COLUMNS;
    }
    
    /**
     * Return the entire 2d Cell array of this world.
     * @return Cell[][] 
     */
    public Cell[][] getCells() {
        return this.cells;
    }
}
