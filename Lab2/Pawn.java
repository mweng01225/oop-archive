// Pawn class
public class Pawn extends ChessPiece {
    public Pawn(String color, int x, int y) {
        super("Pawn", color, x, y);
    }

    @Override
    public boolean verifyMove(int targetX, int targetY) {
        return color.equals("WHITE") ? (targetX == x && targetY == y + 1) : (targetX == x && targetY == y - 1);
    }
}