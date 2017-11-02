package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listens for a mouseclick on the frame to make the world take a turn.
 * @author Jacky
 * @version 1.0a
 */
public class TurnListener extends MouseAdapter {
    
    private final GameFrame gameframe;
    /**
     * Creates a TurnListener that,
     * Listens to a GameFrame object.
     * @param gf Given GameFrame
     */
    public TurnListener(GameFrame gf) {
        gameframe = gf;
    }
    /**
     * Calls takeTurn on the GameFrame when mouse clicked.
     * @param e a MouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        gameframe.takeTurn();       
    }
}
