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
    private int turn;
    
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
        this.turn = 0;
    }
    
    /**
     * Returns the current turn the world is in.
     * @return the current turn.
     */
    public int getTurn() {
        return this.turn;
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
                Entity newEnt = newCreatedCell.getEntity();
                if (newEnt != null) {
                    newEnt.setTurnTaken(false);
                }
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
     * World takes a turn.
     */
    public void takeTurn() {
        ArrayList<Entity> ents = new ArrayList<Entity>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Cell cell = cells[r][c];
                Entity cellEnt = cell.getEntity();
                if (cellEnt != null) {
                    //Resets the turnTaken boolean
                    cellEnt.setTurnTaken(false);
                    //Checks if entity is dead
                    if (!((LifeForm) cellEnt).checkDead()) {
                        ents.add(cellEnt);
                    } else {
                        //If entity is dead, remove it
                        cellEnt.getLocation().removeEntity();
                    }
                }
            }
        }
        update(ents, EntityType.PLANT);
        update(ents, EntityType.CARNIVORE);
        update(ents, EntityType.OMNIVORE);
        update(ents, EntityType.HERBIVORE);
    }
    
    /**
     * Given ArrayList and EntityType, the Entity takes turn.
     * @param al - given ArrayList of Entities.
     * @param en - EntityType to take turn in.
     */
    private void update(ArrayList<Entity> al, EntityType en) {
        for (Entity a : al) {
            //This checks for the EntityType AND that the entity is valid
            if ((a.getEntityType() == en) && (a.getLocation() != null)) {
                a.takeTurn();
            }
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
