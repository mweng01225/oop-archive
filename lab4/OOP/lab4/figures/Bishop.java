package lab4.figures;

import lab4.interfaces.IntBishop;

// Step 6
public class Bishop implements IntBishop{
    private String color;
    private LocationX x;
    private int y;

    public Bishop(String color, LocationX x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Bishop move check
    @Override
    public boolean moveToBishop(LocationX X, int Y){
        return Math.abs(this.x.ordinal() - X.ordinal()) == Math.abs(Y - this.y);
    }


    // Print
    @Override
    public String toString(){
        return this.color + " Bishop at " + this.x + this.y;
    }
}