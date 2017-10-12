package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
     */
    public void mouseClicked (MouseEvent e) {
        System.out.println("clicked");
        gameframe.takeTurn();       
    }
}
