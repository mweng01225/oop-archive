// Bishop class
public class Bishop extends ChessPiece{
    public Bishop(String color, int x, int y) {
        super("Bishop", color, x, y);
    }

    @Override
    public boolean verifyMove(int targetX, int targetY) {
        return Math.abs(targetX - x) == Math.abs(targetY - y);
    }
}