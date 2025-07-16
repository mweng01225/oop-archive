package lab4;

import lab4.interfaces.IntChessBoard;
import lab4.figures.LocationX;

/**
 * Represents a chessboard that validates coordinate positions.
 * Implements the IntChessBoard interface to check if a given X and Y are within bounds.
 * 
 * @author Marvin Weng
 * @version 1.0
 * @since 3/17/2025
 * @see lab4.interfaces.IntChessBoard
 */

public class ChessBoard implements IntChessBoard{

    /**
     * Verifies whether the given (X, Y) position is within the bounds of the board.
     * 
     * @param X check on whether the x position is be between A and H
     * @param Y check on whether the y position is be between 1 and 8
     * @return true if the position is valid on the chessboard, false otherwise
     * @author Marvin Weng
     */
    @Override
    public boolean verifyCoordinate(LocationX X, int Y){
        if(X == null){
            return false;
        }
        return (X.ordinal() >= 0 && X.ordinal() < 8) && (Y >= 1 && Y <= 8);
    }
}