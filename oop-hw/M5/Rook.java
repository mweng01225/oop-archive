public class Rook implements IntFigure {
    private String color;
    private char posX;
    private int posY;

    public Rook(String color, char x, int y) {
        this.color = color;
        this.posX = x;
        this.posY = y;
    }

    @Override
    public boolean isValidMove(char targetX, int targetY) {
        return (this.posX == targetX || this.posY == targetY);
    }
}