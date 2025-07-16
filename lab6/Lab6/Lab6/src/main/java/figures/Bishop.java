package figures;

import interfaces.IntBishop;

public class Bishop extends Figure implements IntBishop {

    public Bishop(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // Bishop's move logic using IntBishop interface
        return bishopMoveTo(this.x, this.y, targetX, targetY);
    }
}