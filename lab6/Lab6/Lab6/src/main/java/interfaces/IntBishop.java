package interfaces;

public interface IntBishop {
    default boolean bishopMoveTo(char startX, int startY, char targetX, int targetY) {
        // Bishop moves diagonally - difference in x and y must be the same
        int xDiff = Math.abs(targetX - startX);
        int yDiff = Math.abs(targetY - startY);
        return xDiff == yDiff;
    }
}