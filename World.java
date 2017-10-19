package ca.bcit.comp2526.a2a;

public class World {
    private Cell[][] cells;
    //contains all cells with active herbivores in the world.
    private Cell[] cellsContHerbivore;
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
        this.cellsContHerbivore = new Cell[rows * cols];
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
        removeDeadHerbivores();
        System.out.println("finished moving dead herbs");
        
        //Loop through all cells in the world.
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                //Grabs each Cell, put as workingCell.
                Cell workingCell = getCellAt(col, row);  
                if(workingCell.getPlant() != null) {
                    //Seed cells:Check the current working cell for seed-able cells
                    System.out.println("checking for seedcells");
                    workingCell.checkForSeedCells(workingCell.getAdjacentCells(1));
                    System.out.println("finished checking for seedcells");
                }
            }
        }
      //Move Herbivores
        System.out.println("moving herbivores");
        moveHerbivores();
        //Eat Plant if exists
    }
    
    /**
     * Moves all alive herbivores on the world 1 to a random adj cell.
     */
    private void moveHerbivores() {
//        for (Cell c : cellsContHerbivore) {
//            c.moveEntity();
//        }
    }
    
    /**
     * Inserts into world's array of noted Herbivores.
     * @param i     which index to insert
     * @param c     the cell to insert into the index
     */
    public void insertIntocellsContHerbivore(int i, Cell c) {
        this.cellsContHerbivore[i] = c;
    }
    
    /**
     * Set a Cell's reference to a Herbivore to null if Herbivore is dead.
     * updates the world's cellsContHerbivore
     */
    public void removeDeadHerbivores() {
        int i = 0;
        int newIndex = 0;
        Cell[] updatedCellsContHerbivore = new Cell[cellsContHerbivore.length];
        while(cellsContHerbivore[i] != null) {
            Cell currentCell = cellsContHerbivore[i];
            if(currentCell.getHerbivore().getHp() <= 0) {
                currentCell.removeHerbivore();
            } else {
                updatedCellsContHerbivore[newIndex] = currentCell;
                newIndex++;
            }
            i++;
        }
        cellsContHerbivore = updatedCellsContHerbivore;
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
