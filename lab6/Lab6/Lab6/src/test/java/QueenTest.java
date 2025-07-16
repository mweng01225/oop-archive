import static org.junit.Assert.*;
import org.junit.Test;
import figures.Queen;

public class QueenTest {

    @Test
    public void testQueenHorizontalMove() {
        // Queen can move horizontally like a rook
        Queen queen = new Queen("WHITE", 'd', 5); // Starting at d5
        assertTrue("Queen should be able to move horizontally",
                queen.moveTo('h', 5)); // To h5
    }

    @Test
    public void testQueenVerticalMove() {
        // Queen can move vertically like a rook
        Queen queen = new Queen("BLACK", 'f', 3); // Starting at f3
        assertTrue("Queen should be able to move vertically",
                queen.moveTo('f', 8)); // To f8
    }

    @Test
    public void testQueenDiagonalMoveUpRight() {
        // Queen can move diagonally like a bishop
        Queen queen = new Queen("WHITE", 'c', 3); // Starting at c3
        assertTrue("Queen should be able to move diagonally",
                queen.moveTo('f', 6)); // To f6
    }

    @Test
    public void testQueenDiagonalMoveDownLeft() {
        // Queen can move diagonally like a bishop
        Queen queen = new Queen("BLACK", 'g', 7); // Starting at g7
        assertTrue("Queen should be able to move diagonally",
                queen.moveTo('d', 4)); // To d4
    }

    @Test
    public void testQueenInvalidMove() {
        // Queen cannot move in L-shaped patterns like a knight
        Queen queen = new Queen("WHITE", 'e', 4); // Starting at e4
        assertFalse("Queen should not be able to move in an L-shaped pattern",
                queen.moveTo('g', 5)); // To g5 (knight's move)
    }
}