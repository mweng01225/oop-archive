package lab4.figures;
/**
 * Represents a King chess piece.
 * 
 * @author Calleros, Enrique
 */
public class King extends Queen{
    /**
     * Creates a King with a specific color and starting position.
     * 
     * @param color the color of the King ("WHITE" or "BLACK")
     * @param x the x position of the King
     * @param y the y position of the King
     * @author Calleros, Enrique
     */
    public King(String color, LocationX x, int y){
        super(color, x, y);
    }
    
    /**
     * Checks if the King can move to the target coordinates.
     * 
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the King can move to the target coordinates, false otherwise
     * @author Calleros, Enrique    
     */
    @Override
    public boolean moveTo(LocationX X, int Y){
        int xDiff = Math.abs(X.ordinal() - this.x.ordinal());
        int yDiff = Math.abs(Y - this.y);
        return (xDiff <= 1 && yDiff <= 1);
    }

    /**
     * Returns a string representation of the King's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString() {
        return this.color + " King at " + this.x + this.y;
    }
}