package figures;

public class Knight extends Figure {

    public Knight(String color, char x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean moveTo(char targetX, int targetY) {
        // Knight moves in an L-shape pattern
        int xDiff = Math.abs(targetX - this.x);
        int yDiff = Math.abs(targetY - this.y);
        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }
}