import static org.junit.Assert.*;
import org.junit.Test;
import figures.Knight;

public class KnightTest {

    @Test
    public void testKnightLShapeMove1() {
        // Knight can move in an L-shape (2 horizontal, 1 vertical)
        Knight knight = new Knight("WHITE", 'd', 4); // Starting at d4
        assertTrue("Knight should be able to move 2 horizontal, 1 vertical",
                knight.moveTo('f', 5)); // To f5
    }

    @Test
    public void testKnightLShapeMove2() {
        // Knight can move in an L-shape (1 horizontal, 2 vertical)
        Knight knight = new Knight("BLACK", 'c', 5); // Starting at c5
        assertTrue("Knight should be able to move 1 horizontal, 2 vertical",
                knight.moveTo('d', 7)); // To d7
    }

    @Test
    public void testKnightLShapeMove3() {
        // Knight can move in an L-shape (2 vertical, 1 horizontal) downward
        Knight knight = new Knight("WHITE", 'e', 6); // Starting at e6
        assertTrue("Knight should be able to move 2 vertical, 1 horizontal",
                knight.moveTo('f', 4)); // To f4
    }

    @Test
    public void testKnightInvalidMove() {
        // Knight cannot move in non-L-shaped patterns
        Knight knight = new Knight("BLACK", 'd', 4); // Starting at d4
        assertFalse("Knight should not be able to move in a non-L-shaped pattern",
                knight.moveTo('f', 6)); // To f6 (diagonal)
    }
}