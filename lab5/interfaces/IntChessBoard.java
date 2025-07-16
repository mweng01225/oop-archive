package lab4.interfaces;

import lab4.figures.LocationX;

/**
 * Interface representing a chessboard.
 * Used to verify if a given coordinate is within the valid bounds of the board.
 * 
 * @author Dukda, Kuenkhel N
 */
public interface IntChessBoard {
    /**
     * Checks if the given (X, Y) position is valid position on a chessboard.
     *
     * @param X the horizontal position to verify (A to H)
     * @param Y the vertical position to verify (1 to 8)
     * @return true if the coordinates are within the board, false otherwise
     * @author Dukda, Kuenkhel N
     */
    boolean verifyCoordinate(LocationX X, int Y);
}