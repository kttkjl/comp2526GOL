package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import javax.swing.JFrame;
//import javax.swing.JLabel;

/**
 * Creates a GameFrame displaying the world in which GOL is situated.
 * @author Jacky
 * @version 1.0a
 */
public class GameFrame extends JFrame {
    /**
     * Version.
     */
    private static final long serialVersionUID = 1L;
    private final World world;
//    private String text = Herbivore.countTotalAlive() + "";
//    JLabel label = new JLabel() {
//        public void setText(String text) {
//            super.setText(text);
//        }
//    };

    /**
     * Constructor for this GameFrame, adhereing to a new world.
     * @param w the world this GameFrame is referencing.
     */
    public GameFrame(final World w) {
        world = w;
    }
    
    /**
     * Initializes the GameFrame.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        addMouseListener(new TurnListener(this));
//        label.setText(text);
//        add(label);
    }
    
    /**
     * Calls world.takeTurn(), let it update, repaint the Frame.
     */
    public void takeTurn() {
        System.out.println("before world turn");
        world.takeTurn();
//        text = Herbivore.countTotalAlive() + "";
//        label.setText(text);
        System.out.println("after world turned");
        for (Cell[] cells: world.getCells()) {
            for (Cell cell: cells) {
                cell.repaint();
            }
        }
        System.out.println("repainted");
        
    }
}
