package figures;

public class Pawn extends Figure {

    public Pawn(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // Pawn's move logic
        // WHITE pawns move up (increasing y), BLACK pawns move down (decreasing y)
        return (targetX == this.x) && (color.equals("WHITE") ? targetY == y + 1 : targetY == y - 1);
    }
}