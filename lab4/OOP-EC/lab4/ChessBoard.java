package lab4;

import lab4.interfaces.IntChessBoard;
import lab4.figures.LocationX;

public class ChessBoard implements IntChessBoard{
    // This method checks if the position X, Y is in bounds
    @Override
    public boolean verifyCoordinate(LocationX X, int Y){
        if(X == null){
            return false;
        }
        return (X.ordinal() >= 0 && X.ordinal() < 8) && (Y >= 1 && Y <= 8);
    }
}