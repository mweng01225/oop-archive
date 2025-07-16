package figures;

import interfaces.IntFigure;

public abstract class Figure implements IntFigure {
    protected String color;         // 'WHITE' or 'BLACK'
    protected char x;               // which column (a-h)
    protected int y;                // which row (1-8)

    public Figure(String color, char x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return color + " " + getClass().getSimpleName() + " at " + x + y;
    }
}