package lab4.figures;

public class Pawn extends Figure{
    public Pawn(String color, LocationX x, int y){
        super(color, x, y);
    }

    // Pawn move check
    @Override
    public boolean moveTo(LocationX X, int Y) {
        return color.equals("WHITE") ? (X.ordinal() == this.x.ordinal() && Y == y + 1) : (X.ordinal() == this.x.ordinal() && Y == y - 1);
    }

    // Print
    @Override
    public String toString() {
        return this.color + " Pawn at " + this.x + this.y;
    }
}