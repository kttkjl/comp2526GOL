package ca.bcit.comp2526.a2a;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Cell extends JPanel {
    private final int ADJCELLSx1 = 8;
    private final World world;
    private final LocationPoint locationPoint;
    protected ArrayList<Entity> entities = new ArrayList<Entity>();

    
    /**
     * Creates a Cell in a World Object, at 
     * @param world     the world Object this cell is stored in.
     * @param row       Row (y).
     * @param column    Column (x).
     */
    public Cell(World world, int row, int column) {
        this.world = world;
        this.locationPoint = new LocationPoint(column, row);
    }
    
    /**
     * Sets up the cell to have randomly a plant or herbivore.
     */
    public void init() {
        int rn = RandomGenerator.nextNumber(100);
        if (rn >= 80) {
            entities.add(new Herbivore(this));
        } else if (rn <= 30) {
            entities.add(new Plant(this));
        }
    }
    
    /**
     * @return the LocationPoint object of this Cell.
     */
    public LocationPoint getLocationPoint() {
        return this.locationPoint;
    }
    
    /**
     * Grabs all the cells within a radius.
     * @return the cells around a particular cell.
     */
    public Cell[] getAdjacentCells(int radius) {
        int grabbedIndex = 0;
        int totalAdjCells = (radius * 2 + 1) * (radius * 2 + 1) - 1;
        Cell[] grabbedCells = new Cell[totalAdjCells];
        int currX = this.locationPoint.getX();
        int currY = this.locationPoint.getY();
        
        for(int i = currX - radius; i < currX + radius; i++) {
            if (i < 0) {continue;}
            for (int j = currY - radius; j < currY + radius; i++) {
                if (j < 0) {continue;}
                if (i == currX && j == currY) {continue;}
                grabbedCells[grabbedIndex] = world.getCellAt(i, j);
                grabbedIndex++;
            }
        }
        return grabbedCells;
    }
    
    /**
     * Finds if adjacent cells are valid for seeding, given
     * @param cells
     */
    public void checkSeedCells(Cell[] cells) {
        Cell[] validcells = new Cell[cells.length];
        int adjEmpty = 0;
        int adjPlants = 0;
        int validCellIndex = 0;
        for(Cell cell : cells) {
            if((cell.getPlant() == null) && (cell.getHerbivore() == null)) {
                adjEmpty++;
                validcells[validCellIndex] = cell;
                validCellIndex++;
            } else if (cell.getPlant() != null) {
                adjPlants++;
            }
        }
        //If the checked cell is valid, call seedCell
        if (adjEmpty >= 3 && adjPlants >= 2) {
            seedCells(validcells, validCellIndex + 1);
        }
    }
    
    /**
     * Seeds in random cell, with given array of valid cells and array length
     * @param cells .
     * @param length .
     */
    private void seedCells(Cell[] cells, int length) {
        //Grabs the cell at random index of given valid cells
        try {
            if (length != 0) {
                Cell plantingCell = cells[RandomGenerator.nextNumber(length)];
                plantingCell.insertEntity(new Plant(plantingCell));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Did not return any valid cells" + e.getMessage());
        }
    }
    
    
    public void paintComponent(Graphics g) {
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Inserts an Entity object
     * @param en   into the Cell
     */
    public void insertEntity(Entity en) {
        this.entities.add(en);
    }

    /**
     * Goes through the entities in the Cell and
     * @return
     *      the plant object from this Cell.
     */
    public Plant getPlant() {
        Plant p = null;
        for (Entity e : entities)
            if(e.getClass().getName().equals("Plant"))
                p = (Plant) e;
        return p;
    }
    /**
     * @return 
     *      the herbivore object from this Cell.
     */
    public Herbivore getHerbivore() {
        Herbivore h = null;
        for (Entity e : entities)
            if(e.getClass().getName().equals("Herbivore"))
                h = (Herbivore) e;
        return h;
    }
    
    
    
    /**
     * Removes this Cell's reference to a Plant object.
     */
//    public void removePlant() {
//        this.plant = null;
//    }
    /**
     * Removes this Cell's reference to a Herbivore object.
     */
//    public void removeHerbivore() {
//        this.herbivore = null;
//    }
    
    
}
