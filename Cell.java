package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Cell extends JPanel {
    /**
     * BECAUSE IT SAID SO.
     */
    private static final long serialVersionUID = 6784752020553593522L;
    private final World world;
    private final LocationPoint locationPoint;
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    private boolean plantAlreadySeeded = false;
    private boolean herbivoreAlreadyMoved = false;
    
    /**
     * Creates a Cell in a World Object, at 
     * @param world     the world Object this cell is stored in.
     * @param row       Row (y).
     * @param column    Column (x).
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
        int rn = RandomGenerator.nextNumber(100);
        int worldHerbivoreIndex = 0;
        if (rn >= 80) {
            Herbivore newHerbivore = new Herbivore(this);
            newHerbivore.init();
            entities.add(newHerbivore);
            /*Insert into world's array of Herbivore locations 
            and updates worldHerbivoreindex;*/
            world.insertIntocellsContHerbivore(worldHerbivoreIndex, this);
            worldHerbivoreIndex++;
        } else if (rn <= 30) {
            Plant newPlant = new Plant(this);
            newPlant.init();
            entities.add(newPlant);
        }
//        repaint();
    }
    
    /**
     * updates the color of the Cell, according to its Entities.
     */
//    private void updateColor() {
//        if(isEmpty(entities)) {
//            setBackground(Color.WHITE);
//        } else if (this.getHerbivore() != null){
//            setBackground(Color.YELLOW);
//        } else {
//            setBackground(Color.GREEN);
//        }
//        repaint();
//    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isEmpty(entities)) {
            g.setColor(Color.WHITE);
        } else if (this.getHerbivore() != null){
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.GREEN);
        }
        //Fills in the Cell according to inhabitants
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.BLACK);
        g.drawRect(0,0, getSize().width, getSize().height);
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
        int currRow = this.locationPoint.getRow();
        int currCol = this.locationPoint.getCol();
        System.out.println("Getting adjacent cells");
        for(int i = currRow - radius; i <= currRow + radius; i++) {
            if (i < 0 || i >= world.COLUMNS) {continue;}
            for (int j = currCol - radius; j <= currCol + radius; j++) {
                if (j < 0 || j >= world.ROWS) {continue;}
                if (i == currRow && j == currCol) {continue;}
                grabbedCells[grabbedIndex] = world.getCellAt(i, j);
                grabbedIndex++;
            }
        }
        System.out.println("returning adjacent cells of: " + "row:"+currRow + " col: " + currCol);
        return grabbedCells;
    }
    
    /**
     * Finds if adjacent cells are valid for seeding, given an array of
     * @param cells
     */
    public void checkForSeedCells(Cell[] cells) {
        Cell[] validcells = new Cell[cells.length];
        int adjEmpty = 0;
        int adjPlants = 0;
        int validCellIndex = 0;
        System.out.println("chkForSeedCells method variables set");
        for(Cell cell : cells) {
            if (cell == null) {break;}
            //If it's an empty cell
            if(isEmpty(cell.entities)) {
                adjEmpty++;
                validcells[validCellIndex] = cell;
                validCellIndex++;
            } else if (cell.getPlant() != null) {
                adjPlants++;
            }
        }
        System.out.println("finished checking valid seed cells");
        //If the checked cell is valid, calls seedCell for the valid cells for seeding
        if (adjEmpty >= 3 && adjPlants >= 2) {
            System.out.println("calling seedcells");
            // - 1 because ++'ed index at the very end
            seedCells(validcells, validCellIndex - 1);
        }
    }
    
    /**
     * Seeds in random cell, with given array of valid cells and array length
     * @param cells .
     * @param length .
     */
    private void seedCells(Cell[] cells, int length) {
        //Grabs the cell at random index of given valid cells
        System.out.println("inside seedCells");
        try {
            if (length > 0) {
                Cell plantingCell = cells[RandomGenerator.nextNumber(length)];
                Plant p = new Plant(plantingCell);
                p.init();
                plantingCell.insertEntity(p);
                System.out.println("repainting" + plantingCell.getLocation());
                repaint();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Did not return any valid cells" + e.getMessage());
        }
        System.out.println("finished seedCells");
    }
    
    /**
     * Checks if the ArrayList of Entity 
     * @param ar
     */
    private boolean isEmpty(ArrayList<Entity> ar) {
        for (Entity en : ar) {
            if(en != null) {return false;}
        }
        return true;
    }
      
    /**
     * Inserts an Entity object
     * @param en   into the Cell
     */
    public void insertEntity(Entity en) {
        this.entities.add(en);
    }
    
    /**
     * Returns an Entity with EntityType
     * @param et
     * @return null if Entity does not exist.
     */
    private Entity getEntity(EntityType et) {
        Entity returnEntity = null;
        for (Entity e : entities) {
            if(e.getEntityType() == et)
                returnEntity = e;
        }
        return returnEntity;
    }
    
    /**
     * Goes through the entities in this Cell and
     * @return
     *      the plant object from this Cell, null if there are no Plants
     */
    public Plant getPlant() {
        Plant p = null;
        p = (Plant) getEntity(EntityType.PLANT);
        return p;
    }
    
    /**
     * Goes through the entities in this Cell and
     * @return 
     *      the herbivore object from this Cell, null if there are no Herbivores
     */
    public Herbivore getHerbivore() {
        Herbivore h = null;
        h = (Herbivore) getEntity(EntityType.HERBIVORE);
        return h;
    }
    
    /**
     * Removes an Entity object reference in this Cell's entities array
     * with given EntityType.
     * @param et
     */
    private void removeEntity(EntityType et) {
        for (Entity e : entities) {
            if(e.getEntityType() == et)
                e = null;
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
    
    /**
     * Moves the Entity
     * @param e     to a new Cell
     * @param c
     */
    public void moveEntity(Entity e, Cell c) {
        
    }
}
