package lab4.figures;
/**
 * Represents a Knight chess piece.
 * 
 * @author Calleros, Enrique
 */
public class Knight extends Figure{
    /**
     * Creates a Knight with a specific color and starting position.
     * 
     * @param color the color of the Knight ("WHITE" or "BLACK")
     * @param x the x position of the Knight
     * @param y the y position of the Knight
     * @author Calleros, Enrique
     */
    public Knight(String color, LocationX x, int y){
        super(color, x, y);
    }

    /**
     * Checks if the Knight can move to the target coordinates.
     * 
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the Knight can move to the target coordinates, false otherwise
     * @author Calleros, Enrique    
     */
    @Override
    public boolean moveTo(LocationX X, int Y) {
        int xDiff = Math.abs(X.ordinal() - this.x.ordinal());
        int yDiff = Math.abs(Y - this.y);
        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }

    /**
     * Returns a string representation of the Knight's current state.
     * @return the color and position
     * @author Calleros, Enrique
     */
    @Override
    public String toString() {
        return this.color + " Knight at " + this.x + this.y;
    }
}