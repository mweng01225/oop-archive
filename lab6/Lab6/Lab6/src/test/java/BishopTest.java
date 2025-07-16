import static org.junit.Assert.*;
import org.junit.Test;
import figures.Bishop;

public class BishopTest {

    @Test
    public void testBishopDiagonalMoveUpRight() {
        // Bishop can move diagonally (up-right)
        Bishop bishop = new Bishop("WHITE", 'c', 3); // Starting at c3
        assertTrue("Bishop should be able to move diagonally up-right",
                bishop.moveTo('f', 6)); // To f6
    }

    @Test
    public void testBishopDiagonalMoveDownLeft() {
        // Bishop can move diagonally (down-left)
        Bishop bishop = new Bishop("BLACK", 'g', 7); // Starting at g7
        assertTrue("Bishop should be able to move diagonally down-left",
                bishop.moveTo('d', 4)); // To d4
    }

    @Test
    public void testBishopDiagonalMoveUpLeft() {
        // Bishop can move diagonally (up-left)
        Bishop bishop = new Bishop("WHITE", 'f', 4); // Starting at f4
        assertTrue("Bishop should be able to move diagonally up-left",
                bishop.moveTo('c', 7)); // To c7
    }

    @Test
    public void testBishopInvalidVerticalMove() {
        // Bishop cannot move vertically
        Bishop bishop = new Bishop("BLACK", 'e', 4); // Starting at e4
        assertFalse("Bishop should not be able to move vertically",
                bishop.moveTo('e', 7)); // To e7
    }

    @Test
    public void testBishopInvalidHorizontalMove() {
        // Bishop cannot move horizontally
        Bishop bishop = new Bishop("WHITE", 'b', 5); // Starting at b5
        assertFalse("Bishop should not be able to move horizontally",
                bishop.moveTo('h', 5)); // To h5
    }
}