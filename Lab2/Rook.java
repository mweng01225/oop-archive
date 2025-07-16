// Rook class
public class Rook extends ChessPiece{
    public Rook(String color, int x, int y) {
        super("Rook", color, x, y);
    }

    @Override
    public boolean verifyMove(int targetX, int targetY) {
        return (targetX == x || targetY == y);
    }
}