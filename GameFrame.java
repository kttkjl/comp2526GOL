package ca.bcit.comp2526.a2a;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;


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
    private static final int UPDATE_INTERVAL = 50;
    private World world;
    private JButton sb;
    private JButton pb;
    private Timer timer;

    /**
     * Constructor for this GameFrame, adhereing to a new world.
     * @param w the world this GameFrame is referencing.
     */
    public GameFrame(World w) {
        world = w;
    }

    /**
     * Initializes the GameFrame.
     */
    public void init() {
        setTitle("Assignment 2b");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                c.gridx = col;
                c.gridy = row;
                c.weightx = 1;
                c.weighty = 1;
                c.fill = GridBagConstraints.BOTH;
                add(world.getCellAt(row, col), c);
            }
        }
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = world.getColumnCount() / 2;
        sb = new JButton(">");
        pb = new JButton("||");
        add(sb, c);
        c.gridx += world.getColumnCount() / 2 + 1;
        c.gridwidth = world.getColumnCount() / 2;
        add(pb, c);
        ActionListener button = new ButtonListener();
        sb.addActionListener(button);
        pb.addActionListener(button);
        timer = new Timer(UPDATE_INTERVAL, button);
        addMouseListener(new TurnListener(this));
    }
    
    /**
     * Calls world.takeTurn(), let it update, repaint the Frame.
     */
    public void takeTurn() {
//        System.out.println("before world turn");
        world.takeTurn();
//        System.out.println("after world turned");
        for (Cell[] cells: world.getCells()) {
            for (Cell cell: cells) {
                cell.repaint();
            }
        }
//        System.out.println("repainted");
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (arg0.getSource() == sb) {
                timer.start();
            } else if (arg0.getSource() == pb) {
                timer.stop();
            }
            takeTurn();
        }
        
    }
}
