package figures;

public class King extends Figure {

    public King(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // King moves one square in any direction
        int xDiff = Math.abs(targetX - this.x);
        int yDiff = Math.abs(targetY - this.y);
        return (xDiff <= 1 && yDiff <= 1);
    }
}