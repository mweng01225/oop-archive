package lab4.interfaces;

import lab4.figures.LocationX;

/**
 * Interface for any chess figure that supports movement on the board.
 * Each chess piece implements this and defines its own movement logic.
 * 
 * This is a base contract for pieces like Pawn, Bishop, Knight, etc.
 * 
 * @author Dukda, Kuenkhel N
 */

public interface IntFigure{
    /**
     * Checks if the figure can move to a (X, Y) position.
     *
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the move is allowed for this figure; false otherwise
     * @author Dukda, Kuenkhel N
     */
    boolean moveTo(LocationX X, int Y);
}