import interfaces.IntChessBoard;

public class ChessBoard implements IntChessBoard {
    // Board boundaries
    private static final int MAX_ROW = 8;
    private static final int MIN_ROW = 1;
    private static final char MIN_COL = 'a';
    private static final char MAX_COL = 'h';

    @Override
    public boolean verifyCoordinate(char x, int y) {
        // Convert to lowercase for case-insensitive check
        char lowerX = Character.toLowerCase(x);

        // Check if position is within the defined chessboard
        boolean validX = (lowerX >= MIN_COL && lowerX <= MAX_COL);
        boolean validY = (y >= MIN_ROW && y <= MAX_ROW);

        return validX && validY;
    }
}