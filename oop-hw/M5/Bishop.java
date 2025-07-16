public class Bishop implements IntBishop {
    private String color;
    private char posX;
    private int posY;

    public Bishop(String color, char x, int y) {
        this.color = color;
        this.posX = x;
        this.posY = y;
    }

    @Override
    public boolean isValidMove(char targetX, int targetY) {
        return Math.abs(targetX - this.posX) == Math.abs(targetY - this.posY);
    }

    @Override
    public void printValues() {
        System.out.println("Bishop Color: " + color);
        System.out.println("Bishop Position: " + posX + posY);

    }
}