package figures;

public class Rook extends Figure {

    public Rook(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // Rook moves horizontally or vertically
        return (targetX == this.x || targetY == this.y);
    }
}