package lab4.figures;

/**
 * Represents a Rook chess piece.
 * 
 * @author Calleros, Enrique
 */
public class Rook extends Figure{
    /**
     * Creates a Rook with a specific color and starting position.
     * 
     * @param color the color of the Rook ("WHITE" or "BLACK")
     * @param x the x position of the Rook
     * @param y the y position of the Rook
     * @author Calleros, Enrique
     */
    public Rook (String color, LocationX x, int y){
        super(color, x, y);
    }

    /**
     * Checks if the Rook can move to the target coordinates.
     * 
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the Rook can move to the target coordinates, false otherwise
     * @author Calleros, Enrique    
     */
    @Override
    public boolean moveTo(LocationX X, int Y) {
        return (X.ordinal() == this.x.ordinal() || Y == y);
    }

    /**
     * Returns a string representation of the Rook's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString() {
        return this.color + " Rook at " + this.x + this.y;
    }
}