package lab4.figures;
/**
 * Represents a Pawn chess piece.
 * 
 * @author Calleros, Enrique
 */
public class Pawn extends Figure{
    /**
     * Creates a Pawn with a specific color and starting position.
     * 
     * @param color the color of the Pawn ("WHITE" or "BLACK")
     * @param x the x position of the Pawn
     * @param y the y position of the Pawn
     * @author Calleros, Enrique
     */
    public Pawn(String color, LocationX x, int y){
        super(color, x, y);
    }

    /**
     * Checks if the Pawn can move to the target coordinates.
     * 
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the Pawn can move to the target coordinates, false otherwise
     * @author Calleros, Enrique    
     */
    @Override
    public boolean moveTo(LocationX X, int Y) {
        return color.equals("WHITE") ? (X.ordinal() == this.x.ordinal() && Y == y + 1) : (X.ordinal() == this.x.ordinal() && Y == y - 1);
    }

    /**
     * Returns a string representation of the Pawn's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString() {
        return this.color + " Pawn at " + this.x + this.y;
    }
}