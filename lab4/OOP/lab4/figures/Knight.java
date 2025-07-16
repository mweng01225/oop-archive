package lab4.figures;

public class Knight extends Figure{
    public Knight(String color, LocationX x, int y){
        super(color, x, y);
    }

    // Knight's move check
    @Override
    public boolean moveTo(LocationX X, int Y) {
        int xDiff = Math.abs(X.ordinal() - this.x.ordinal());
        int yDiff = Math.abs(Y - this.y);
        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }

    // Print
    @Override
    public String toString() {
        return this.color + " Knight at " + this.x + this.y;
    }
}