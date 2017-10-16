package ca.bcit.comp2526.a2a;

import javax.swing.JPanel;

public class Cell extends JPanel{

    private final World world;
    private final LocationPoint locationPoint;
    protected Entity entity;
    protected Herbivore herbivore;
    protected Plant plant;
    
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
            this.herbivore = new Herbivore(this);
        } else if (rn <= 30) {
            this.plant = new Plant(this);
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
     * Inserts an Herbivore object
     * @param hb   into the Cell
     */
    public void insertHerbivore(Herbivore hb) {
        this.herbivore = hb;
    }
    /**
     * Inserts a Plant object 
     * @param pt    into the cell
     */
    public void insertPlant(Plant pt) {
        this.plant = pt;
    }
    /**
     * @return
     *      the plant object from this Cell.
     */
    public Plant getPlant() {
        return this.plant;
    }
    /**
     * @return 
     *      the herbivore object from this Cell.
     */
    public Herbivore getHerbivore() {
        return this.herbivore;
    }
    /**
     * Removes this Cell's reference to a Plant object.
     */
    public void removePlant() {
        this.plant = null;
    }
    /**
     * Removes this Cell's reference to a Herbivore object.
     */
    public void removeHerbivore() {
        this.herbivore = null;
    }
    
    
}
