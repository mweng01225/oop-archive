package lab4.interfaces;

import lab4.figures.LocationX;
// Step 6
public interface IntBishop{
    default boolean moveTo(LocationX X, int Y){
        return Math.abs(this.getX().ordinal() - X.ordinal()) == Math.abs(this.getY() - Y);
    }

    LocationX getX();
    int getY();

}