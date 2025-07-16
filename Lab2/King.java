// King class
public class King extends Queen {
    public King(String color, int x, int y) {
        super(color, x, y);
        this.piece_name = "King";
    }

    @Override
    public boolean verifyMove(int targetX, int targetY) {
        int xDiff = Math.abs(targetX - x);
        int yDiff = Math.abs(targetY - y);
        return (xDiff <= 1 && yDiff <= 1);
    }
}