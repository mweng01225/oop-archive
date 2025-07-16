// Knight class
public class Knight extends ChessPiece{
    public Knight(String color, int x, int y) {
        super("Knight", color, x, y);
    }

    @Override
    public boolean verifyMove(int targetX, int targetY) {
        int xDiff = Math.abs(targetX - x);
        int yDiff = Math.abs(targetY - y);
        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }
}