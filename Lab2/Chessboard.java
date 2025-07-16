public class Chessboard {
    // row boundaries
    private static final int MAX_ROW = 8;
    private static final int MIN_ROW = 1;

    // col boundaries
    enum LocationX {
        a, b, c, d, e, f, g, h
    }

    public boolean withinChessboard(char col, int row) {
        // check if position is within the defined hard coded chessboard
        // test row
        boolean testRow = (row >= MIN_ROW && row <= MAX_ROW);

        // test col
        boolean testCol;
        try{
            LocationX location = LocationX.valueOf(String.valueOf(col).toLowerCase());
            testCol = true;
        } catch (IllegalArgumentException e){
            testCol = false;
        }
        return testCol && testRow;
    }
}
