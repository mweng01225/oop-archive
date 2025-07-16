package lab4.figures;

import lab4.interfaces.IntBishop;

public class Queen extends Rook implements IntBishop{
    public Queen(String color, LocationX x, int y){
        super(color, x, y);
    }

    // Queen's movecheck
    @Override
    public boolean moveTo(LocationX X, int Y){
        // super to rook's moveTo, moveToBishop for bishop's moveTo
        return super.moveTo(X,Y) || IntBishop.super.moveTo(X,Y);
    }

    @Override
    public LocationX getX() {
        return this.x;
    }
    @Override
    public int getY() {
        return this.y;
    }

    // Print
    @Override
    public String toString() {
        return this.color + " Queen at " + this.x + this.y;
    }
}