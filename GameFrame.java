package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * .
 * @author Jacky
 *
 */
public class GameFrame extends JFrame {
    private final World world;

    public GameFrame(final World w) {
        world = w;
    }

    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        addMouseListener(new TurnListener(this));
    }
    
    /**
     * repaints the world.
     */
    public void repaint() {
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
    }
    
    /**
     * Calls world.takeTurn(), let it update, repaint the Frame.
     */
    public void takeTurn() {
        System.out.println("before world turn");
        world.takeTurn();
        System.out.println("after world turned");
        repaint();
        System.out.println("repainted");
    }
}