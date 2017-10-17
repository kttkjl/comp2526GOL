package ca.bcit.comp2526.a2a;

public class Herbivore extends Entity{
    private static int totalNum = 0;
    private int hitPoint = 10;
    
    public Herbivore(Cell cell) {
        super(cell);
        // TODO Auto-generated constructor stub
    }

    /**
     * Sets the background to Yellow.
     */
    public void init() {
        
    }
   
    public void minusHp() {
        this.hitPoint--;
    }
    /**
     * Moves the Herbivore one cell
     * Eats plant if cell has a plant
     */
    public void move() {
        
    }
    
}
