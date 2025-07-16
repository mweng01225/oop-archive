import static org.junit.Assert.*;
import org.junit.Test;
import figures.Rook;

public class RookTest {

    @Test
    public void testRookHorizontalMove() {
        // Rook can move horizontally
        Rook rook = new Rook("WHITE", 'd', 5); // Starting at d5
        assertTrue("Rook should be able to move horizontally",
                rook.moveTo('h', 5)); // To h5
    }

    @Test
    public void testRookVerticalMove() {
        // Rook can move vertically
        Rook rook = new Rook("BLACK", 'f', 3); // Starting at f3
        assertTrue("Rook should be able to move vertically",
                rook.moveTo('f', 8)); // To f8
    }

    @Test
    public void testRookInvalidDiagonalMove() {
        // Rook cannot move diagonally
        Rook rook = new Rook("WHITE", 'c', 3); // Starting at c3
        assertFalse("Rook should not be able to move diagonally",
                rook.moveTo('e', 5)); // To e5
    }
}