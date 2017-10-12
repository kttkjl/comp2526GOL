package ca.bcit.comp2526.a2a;

public class World {
    
    private Cell[][] cells;
    final int ROWS;
    final int COLUMNS;
    /**
     * Constructs the world.
     * Holds Cells
     *      When new World created, all cells have 
     *          20% chance = Herbivore
     *          30% chance = Plant
     * Using:
     * @param rows  : allowed y coords
     * @param cols  : allowed x coords
     */
    public World (int rows, int cols) {
        this.ROWS = rows;
        this.COLUMNS = cols;
        this.cells = new Cell[rows][cols];
    }
    
    /**
     * Puts the Cells on the World and adds Herbivores + Plants
     */
    public void init() {
        for (int row = 0; row < this.ROWS; row++) {
            for (int col = 0; col < this.COLUMNS; col++) {
                this.cells[row][col] = new Cell(this, row, col);
            }
        }
    }
    
    /**
     * Returns the Cell object at 
     * @param y column(x coordinate),
     * @param x row (y coordinate).
     * @return
     */
    public Cell getCellAt(int y, int x) {
        return this.cells[y][x];
    }
    
    /**
     * Removes dead herbivores.
     * Checks plant cond. for growth.
     * Moves all living Herbivores 1 cell randomly.
     *      Eat plant if plant cell.
     */
    public void takeTurn() {
        
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
}
