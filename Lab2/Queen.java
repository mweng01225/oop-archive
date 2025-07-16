// Queen class
public class Queen extends Rook{
    public Queen(String color, int x, int y) {
        super(color, x, y);
        this.piece_name = "Queen";
    }
    @Override
    public boolean verifyMove(int targetX, int targetY) {
        int xDiff = Math.abs(targetX - x);
        int yDiff = Math.abs(targetY - y);
    
        return super.verifyMove(targetX, targetY) || (xDiff == yDiff);
    }
}