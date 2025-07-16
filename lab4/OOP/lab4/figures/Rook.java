package lab4.figures;


public class Rook extends Figure{
    public Rook (String color, LocationX x, int y){
        super(color, x, y);
    }

    // Rook's move check
    @Override
    public boolean moveTo(LocationX X, int Y) {
        return (X.ordinal() == this.x.ordinal() || Y == y);
    }

    // Print
    @Override
    public String toString() {
        return this.color + " Rook at " + this.x + this.y;
    }
}