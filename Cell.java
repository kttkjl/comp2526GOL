package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A Cell object with a reference to a World object. Contains an ArrayList of
 * Entities
 * 
 * @author Jacky
 * @version 1.0a
 */
public class Cell extends JPanel {
    /**
     * BECAUSE IT SAID SO.
     */
    private static final long serialVersionUID = 6784752020553593522L;
    private static final int UPPER_LIM = 100;
    private static final int HERBIVORE_RATIO = 80;
    private static final int PLANT_RATIO = 50;
    private static final int CARNIVORE_RATIO = 40;
    private static final int OMNIVORE_RATIO = 32;
    private Entity entity;
    private final World world;
    private final LocationPoint locationPoint;

    /**
     * Creates a Cell with a reference to a World Object, and coordinates.
     * 
     * @param world
     *            the world Object this cell is stored in.
     * @param row
     *            Row (y).
     * @param column
     *            Column (x).
     */
    public Cell(World world, int row, int column) {
        super();
        this.world = world;
        this.locationPoint = new LocationPoint(row, column);
    }

    /**
     * Sets up the cell to have randomly a plant or herbivore.
     */
    public void init() {
        int rn = RandomGenerator.nextNumber(UPPER_LIM);
        if (rn >= HERBIVORE_RATIO) {
            Herbivore newHerbivore = new Herbivore(this);
            newHerbivore.init();
            this.entity = newHerbivore;
        } else if (rn >= PLANT_RATIO) {
            Plant newPlant = new Plant(this);
            newPlant.init();
            this.entity = newPlant;
        } else if (rn >= CARNIVORE_RATIO) {
            Carnivore newCarnivore = new Carnivore(this);
            newCarnivore.init();
            this.entity = newCarnivore;
        } else if (rn >= OMNIVORE_RATIO) {
            Omnivore newOmnivore = new Omnivore(this);
            newOmnivore.init();
            this.entity = newOmnivore;
        }
    }

    /**
     * Paints this Cell according to Entities in the Cell.
     * 
     * @param g
     *            Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (entity == null) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(entity.getColor());
        }
        // Fills in the Cell according to inhabitants
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getSize().width, getSize().height);
    }

    /**
     * Returns the LocationPoint object of this Cell.
     * 
     * @return the LocationPoint object of this Cell.
     */
    public LocationPoint getLocationPoint() {
        return this.locationPoint;
    }

    /**
     * Grabs all the cells within a radius.
     * 
     * @return the cells around a particular cell.
     * @param radius
     *            the radius in which to grab the cells from
     */
    public Cell[] getAdjacentCells(int radius) {
        int grabbedIndex = 0;
        int totalAdjCells = (radius * 2 + 1) * (radius * 2 + 1) - 1;
        Cell[] grabbedCells = new Cell[totalAdjCells];
        int currRow = this.locationPoint.getRow();
        int currCol = this.locationPoint.getCol();
        for (int i = currRow - radius; i <= currRow + radius; i++) {
            if (i < 0 || i >= world.getColumnCount()) {
                continue;
            }
            for (int j = currCol - radius; j <= currCol + radius; j++) {
                if (j < 0 || j >= world.getRowCount()) {
                    continue;
                }
                if (i == currRow && j == currCol) {
                    continue;
                }
                grabbedCells[grabbedIndex] = world.getCellAt(i, j);
                grabbedIndex++;
            }
        }
        return grabbedCells;
    }

    /**
     * Returns the entity of this Cell.
     * @return null if Entity does not exist.
     */
    public Entity getEntity() {
        return this.entity;
    }
    
    /**
     * Sets this Cells' entity.
     * @param en Entity to be set.
     */
    public void setEntity(Entity en) {
        this.entity = en;
    }

    /**
     * Removes this cell's Entity.
     */
    protected void removeEntity() {
        if (entity != null) {
            this.entity.setLocation(null);
        }
        this.entity = null;
    }

}
