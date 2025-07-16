package lab4.figures;

import lab4.interfaces.IntBishop;

public class Queen extends Rook implements IntBishop{
    public Queen(String color, LocationX x, int y){
        super(color, x, y);
    }

    // Override because it implements abstract method 'moveToBishop' from IntBishop interface
    @Override
    public boolean moveToBishop(LocationX X, int Y){
        return Math.abs(this.x.ordinal() - X.ordinal()) == Math.abs(Y - this.y);
    }

    // Queen's movecheck
    @Override
    public boolean moveTo(LocationX X, int Y){
        // super to rook's moveTo, moveToBishop for bishop's moveTo
        return super.moveTo(X,Y) || moveToBishop(X,Y);
    }

    // Print
    @Override
    public String toString() {
        return this.color + " Queen at " + this.x + this.y;
    }
}