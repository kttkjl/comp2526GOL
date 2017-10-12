package ca.bcit.comp2526.a2a;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell extends javax.swing.JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Point point;
    private CellType type;
    
    /**
     * Creates a Cell in a World Object, at 
     * @param world
     * @param row       Row (y).
     * @param column    Column (x).
     */
    public Cell(World world, int row, int column) {
        this.point = new Point(column, row);
        if (Math.random() > 0.66) {
            this.type = CellType.EMPTY;
        } else if (Math.random() < 0.33) {
            this.type = CellType.HERBIVORE;
        } else {
            this.type = CellType.PLANT;
        }
        init();
    }
    
    /**
     * Sets up the layout.
     */
    public void init() {
        switch (this.type) {
        case EMPTY:
            setBackground(Color.WHITE);
            break;
        case HERBIVORE:
            setBackground(Color.YELLOW);
            break;
        case PLANT:
            setBackground(Color.GREEN);
            break;
        default:
            setBackground(Color.WHITE);
            break;
        }
    }
    
    /**
     * Returns the location of the Cell on the World
     * @override java.awt.Component.getLocation
     * @return
     */
    public Point getLocation() {
        return this.point;
    }
    

    
}
