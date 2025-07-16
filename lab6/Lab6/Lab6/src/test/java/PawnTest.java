import static org.junit.Assert.*;
import org.junit.Test;
import figures.Pawn;

public class PawnTest {

    @Test
    public void testWhitePawnForwardMove() {
        // White pawn can move one space forward
        Pawn whitePawn = new Pawn("WHITE", 'e', 4); // Starting at e4
        assertTrue("White pawn should be able to move one space forward",
                whitePawn.moveTo('e', 5)); // To e5
    }

    @Test
    public void testWhitePawnInvalidHorizontalMove() {
        // White pawn cannot move horizontally
        Pawn whitePawn = new Pawn("WHITE", 'd', 5); // Starting at d5
        assertFalse("White pawn should not be able to move horizontally",
                whitePawn.moveTo('e', 5)); // To e5
    }

    @Test
    public void testWhitePawnInvalidBackwardMove() {
        // White pawn cannot move backward
        Pawn whitePawn = new Pawn("WHITE", 'f', 6); // Starting at f6
        assertFalse("White pawn should not be able to move backward",
                whitePawn.moveTo('f', 5)); // To f5
    }

    @Test
    public void testBlackPawnForwardMove() {
        // Black pawn can move one space forward (downward)
        Pawn blackPawn = new Pawn("BLACK", 'h', 7); // Starting at h7
        assertTrue("Black pawn should be able to move one space forward (downward)",
                blackPawn.moveTo('h', 6)); // To h6
    }

    @Test
    public void testBlackPawnInvalidHorizontalMove() {
        // Black pawn cannot move horizontally
        Pawn blackPawn = new Pawn("BLACK", 'b', 6); // Starting at b6
        assertFalse("Black pawn should not be able to move horizontally",
                blackPawn.moveTo('c', 6)); // To c6
    }

    @Test
    public void testBlackPawnInvalidBackwardMove() {
        // Black pawn cannot move backward
        Pawn blackPawn = new Pawn("BLACK", 'e', 4); // Starting at e4
        assertFalse("Black pawn should not be able to move backward",
                blackPawn.moveTo('e', 5)); // To e5
    }
}