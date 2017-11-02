package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

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
    private static final int HERB_RATIO = 80;
    private static final int PLANT_RATIO = 50;
    private static final int MIN_PLANT_REQ = 2;
    private static final int MIN_EMPTY_REQ = 3;
    private ArrayList<Entity> entities = new ArrayList<Entity>();
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
        if (rn >= HERB_RATIO) {
            Herbivore newHerbivore = new Herbivore(this);
            newHerbivore.init();
            entities.add(newHerbivore);
        } else if (rn >= PLANT_RATIO) {
            Plant newPlant = new Plant(this);
            newPlant.init();
            entities.add(newPlant);
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
        if (entityEmpty(entities)) {
            g.setColor(Color.WHITE);
        } else if (this.getHerbivore() != null) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.GREEN);
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
     * Finds if adjacent cells are valid for seeding, given an array of cells.
     * Returns an array of valid cells to be seeded, else return null.
     * @param cells
     *            the adjacent cells
     * @return the valid Cells to be seeded, else null
     */
    public Cell[] getSeedCells(Cell[] cells) {
        Cell[] validcells = new Cell[cells.length];
        int adjEmpty = 0;
        int adjPlants = 0;
        int validCellIndex = 0;
        // Goes through all the adjacent cells
        for (Cell cell : cells) {
            if (cell == null) {
                break;
            }
            Plant p = cell.getPlant();
            // If it's an empty cell
            if (entityEmpty(cell.entities)) {
                adjEmpty++;
                validcells[validCellIndex] = cell;
                validCellIndex++;
            } else if (p != null) {
                adjPlants++;
            }
        }
        // If the checked cell is valid, return a random cell to be seeded
        if (adjEmpty >= MIN_EMPTY_REQ && adjPlants >= MIN_PLANT_REQ 
                && validCellIndex > 0) {
            // trims this
            Cell[] validcells2 = new Cell[validCellIndex];
            System.arraycopy(validcells, 0, validcells2, 0, validCellIndex);
            return validcells2;
        }
        return null;
    }

    /**
     * Seeds a Plant object in this Cell, resets alreadySeeded Status.
     */
    public void seedCell() {
        Plant p = new Plant(this);
//        p.init();
        this.insertEntity(p);
    }

    /**
     * Checks if the ArrayList of Entity is empty.
     * 
     * @param ar
     *            the ArrayList to check
     * @return true if ArrayList is empty
     */
    private boolean entityEmpty(ArrayList<Entity> ar) {
        for (Entity en : ar) {
            if (en != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Inserts an Entity object into the entities ArrayList of this Cell.
     * 
     * @param en
     *            the Entity object to be inserted
     */
    public void insertEntity(Entity en) {
        this.entities.add(en);
    }

    /**
     * Returns an Entity with given EntityType the entities ArrayList of this
     * Cell.
     * 
     * @param et
     *            the given EntityType.
     * @return null if Entity does not exist.
     */
    protected Entity getEntity(EntityType et) {
        Entity returnEntity = null;
        for (Entity e : entities) {
            if (e.getEntityType() == et) {
                returnEntity = e;
            }
        }
        return returnEntity;
    }

    /**
     * Goes through the entities in this Cell and return the plant object 
     * from this Cell, null if there are no Plants.
     * @return the Plant object from this Cell, null if there are no Plants.
     */
    public Plant getPlant() {
        Plant p = null;
        p = (Plant) getEntity(EntityType.PLANT);
        return p;
    }

    /**
     * Goes through the entities in this Cell and return the herbivore object 
     * from this Cell, null if there are no Herbivores.
     * 
     * @return the Herbivore object from this Cell, null if there are no 
     * Herbivores
     */
    public Herbivore getHerbivore() {
        Herbivore h = null;
        h = (Herbivore) getEntity(EntityType.HERBIVORE);
        return h;
    }

    /**
     * Removes an Entity object reference in this Cell's entities array. with
     * given EntityType.
     * 
     * @param et    EntityType of the Entity to be removed
     */
    protected void removeEntity(EntityType et) {
        for (Iterator<Entity> iterator = entities.iterator(); 
                iterator.hasNext();) {
            Entity en = iterator.next();
            if (en.getEntityType() == et) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
    }

    /**
     * Removes this Cell's Plant Entity reference.
     */
    public void removePlant() {
        removeEntity(EntityType.PLANT);
    }

    /**
     * Removes this Cell's Herbivore Entity reference.
     */
    public void removeHerbivore() {
        removeEntity(EntityType.HERBIVORE);
    }


}
