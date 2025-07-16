package lab4.figures;

import lab4.interfaces.IntBishop;
/**
 * Represents a Bishop chess piece.
 * Implements diagonal movement validation via IntBishop interface.
 * 
 * @author Calleros, Enrique
 */
public class Bishop implements IntBishop{
    private String color;
    private LocationX x;
    private int y;

    /**
     * Creates a Bishop with a specific color and starting position.
     * 
     * @param color the color of the bishop ("WHITE" or "BLACK")
     * @param x the x position of the bishop
     * @param y the y position of the bishop
     * @author Calleros, Enrique
     */
    public Bishop(String color, LocationX x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if the bishop can move to the target coordinates.
     * 
     * @param targetX the horizontal position to verify (A to H)
     * @param targetY the vertical position to verify (1 to 8)
     * @return true if the move is diagonal and valid, false otherwise
     * @author Calleros, Enrique    
     */
    public boolean moveTo(LocationX targetX, int targetY) {
        return IntBishop.super.moveTo(this.x, this.y, targetX, targetY);
    }


    

    /**
     * Returns a string representation of the bishop's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString(){
        return this.color + " Bishop at " + this.x + this.y;
    }
}