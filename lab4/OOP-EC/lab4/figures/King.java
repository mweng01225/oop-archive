package lab4.figures;

public class King extends Queen{
    public King(String color, LocationX x, int y){
        super(color, x, y);
    }
    
    // King's movecheck
    @Override
    public boolean moveTo(LocationX X, int Y){
        int xDiff = Math.abs(X.ordinal() - this.x.ordinal());
        int yDiff = Math.abs(Y - this.y);
        return (xDiff <= 1 && yDiff <= 1);
    }

    // Print
    @Override
    public String toString() {
        return this.color + " King at " + this.x + this.y;
    }
}