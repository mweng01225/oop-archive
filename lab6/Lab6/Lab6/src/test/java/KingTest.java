import static org.junit.Assert.*;
import org.junit.Test;
import figures.King;

public class KingTest {

    @Test
    public void testKingHorizontalMove() {
        // King can move one space horizontally
        King king = new King("WHITE", 'd', 5); // Starting at d5
        assertTrue("King should be able to move one space horizontally",
                king.moveTo('e', 5)); // To e5
    }

    @Test
    public void testKingVerticalMove() {
        // King can move one space vertically
        King king = new King("BLACK", 'c', 4); // Starting at c4
        assertTrue("King should be able to move one space vertically",
                king.moveTo('c', 5)); // To c5
    }

    @Test
    public void testKingDiagonalMove() {
        // King can move one space diagonally
        King king = new King("WHITE", 'e', 3); // Starting at e3
        assertTrue("King should be able to move one space diagonally",
                king.moveTo('f', 4)); // To f4
    }

    @Test
    public void testKingInvalidHorizontalMove() {
        // King cannot move more than one space horizontally
        King king = new King("BLACK", 'b', 2); // Starting at b2
        assertFalse("King should not be able to move more than one space horizontally",
                king.moveTo('d', 2)); // To d2
    }

    @Test
    public void testKingInvalidVerticalMove() {
        // King cannot move more than one space vertically
        King king = new King("WHITE", 'g', 6); // Starting at g6
        assertFalse("King should not be able to move more than one space vertically",
                king.moveTo('g', 8)); // To g8
    }

    @Test
    public void testKingInvalidDiagonalMove() {
        // King cannot move more than one space diagonally
        King king = new King("BLACK", 'd', 4); // Starting at d4
        assertFalse("King should not be able to move more than one space diagonally",
                king.moveTo('f', 6)); // To f6
    }
}