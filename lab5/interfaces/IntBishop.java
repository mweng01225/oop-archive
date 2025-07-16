package lab4.interfaces;

import lab4.figures.LocationX;

/**
 * Interface that defines the movement behavior for a Bishop chess piece.
 * Includes default logic to validate diagonal movement from a starting to a target position.
 * 
 * This move is valid if the horizontal and vertical distances are equal.
 * 
 * @author Dukda, Kuenkhel N
 */
public interface IntBishop{
    /**
     * Determines if the bishop can move from the starting coordinates to the target coordinates.
     * A bishop moves diagonally, which means the absolute difference between X and Y must be equal.
     *
     * @param startX the starting horizontal position (A to H)
     * @param startY the starting vertical position (1 to 8)
     * @param targetX the target horizontal position
     * @param targetY the target vertical position
     * @return true if the move is a valid diagonal move; false otherwise
     * @author Dukda, Kuenkhel N
     */
    default boolean moveTo(LocationX startX, int startY, LocationX targetX, int targetY){
        return Math.abs(startX.ordinal() - targetX.ordinal()) == Math.abs(startY - targetY);
    }
}