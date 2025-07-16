package lab4.figures;

import lab4.interfaces.IntBishop;
/**
 * Represents a Queen chess piece.
 * 
 * @author Calleros, Enrique
 */
public class Queen extends Rook implements IntBishop{
    /**
     * Creates a Queen with a specific color and starting position.
     * 
     * @param color the color of the Queen ("WHITE" or "BLACK")
     * @param x the x position of the Queen
     * @param y the y position of the Queen
     * @author Calleros, Enrique
     */
    public Queen(String color, LocationX x, int y){
        super(color, x, y);
    }

    /**
     * Checks if the Queen can move to the target coordinates.
     * Uses both Rook and Bishop movement logic.
     * 
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the Queen can move like a rook or bishop, false otherwise
     * @see lab4.figures.Rook
     * @see lab4.interfaces.IntBishop
     * @author Calleros, Enrique    
     */
    @Override
    public boolean moveTo(LocationX X, int Y){
        // super to rook's moveTo, moveToBishop for bishop's moveTo
        return super.moveTo(X,Y) || IntBishop.super.moveTo(this.x, this.y, X,Y);
    }

    /**
     * Returns a string representation of the Queen's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString() {
        return this.color + " Queen at " + this.x + this.y;
    }
}