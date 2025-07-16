package figures;

import interfaces.IntBishop;

public class Queen extends Rook implements IntBishop {

    public Queen(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // Queen can move like a Rook (horizontally/vertically) or a Bishop (diagonally)
        // First check Rook movement (using parent class)
        if (super.moveTo(targetX, targetY)) {
            return true;
        }

        // Then check Bishop movement (using IntBishop interface)
        return bishopMoveTo(this.x, this.y, targetX, targetY);
    }
}