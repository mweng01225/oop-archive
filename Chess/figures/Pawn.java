package figures;

public class Pawn extends Figure {

    public Pawn(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        int direction = color.equals("WHITE") ? 1 : -1;
        int startRow = color.equals("WHITE") ? 2 : 7;

        int xDiff = targetX - this.x;
        int yDiff = targetY - this.y;

        // Forward move
        if (xDiff == 0) {
            // 1-square forward
            if (yDiff == direction) return true;

            // 2-square forward from starting row
            if (this.y == startRow && yDiff == 2 * direction) return true;

            return false;
        }

        // Diagonal capture (x difference of 1, y diff of 1 in correct direction)
        if (Math.abs(xDiff) == 1 && yDiff == direction) {
            return true;  // NOTE: actual capture check should be in ChessGame.move()
        }

        return false;
    }
}