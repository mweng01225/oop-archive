import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        PawnTest.class,
        RookTest.class,
        KnightTest.class,
        BishopTest.class,
        QueenTest.class,
        KingTest.class
})
public class ChessTestSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}