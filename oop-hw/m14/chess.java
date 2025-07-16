// Command interface
public interface Command {
    void execute();
}


// default MoveCommand
class MoveCommand implements Command {
    private ChessGame game;  // receiver
    private String from, to; // start position, end position

    public MoveCommand(ChessGame game, String from, String to) {
        this.game = game; // receiver
        this.from = from; // start pos
        this.to = to;     // end pos
    }

    public setFrom(String from){
        this.from = from; // use a different starting position
    }

    public setTo(String to){
        this.to = to; // use a different end position
    }

    @Override
    public void execute() {
        game.move(from, to);
    }
}

// unique CastlingCommand
class CastlingCommand implements Command {
    private ChessGame game;          // receiver
    private String kingFrom, kingTo; // start position, end position
    private String rookFrom, rookTo; // start position, end position

    public CastlingCommand(ChessGame game, String kingFrom, String kingTo, String rookFrom, String rookTo) {
        this.game = game;
        this.kingFrom = kingFrom;
        this.kingTo = kingTo;
        this.rookFrom = rookFrom;
        this.rookTo = rookTo;
    }

    @Override
    public void execute() {
        game.castle(kingFrom, kingTo, rookFrom, rookTo);
    }
}


// unique EnPassantCommand
class EnPassantCommand implements Command {
    private ChessGame game;             // receiver
    private String from, to, capturedPawnPos; // start, end, pawn to delete

    public EnPassantCommand(ChessGame game, String from, String to, String capturedPawnPos) {
        this.game = game;
        this.from = from;
        this.to = to;
        this.capturedPawnPos = capturedPawnPos;
    }

    @Override
    public void execute() {
        game.enPassant(from, to, capturedPawnPos);
    }
}


// ChessGame (receiver)
class ChessGame {
    public void move(String from, String to) {
        System.out.println("Moving piece from " + from + " to " + to);
    } // Moving piece from e2 to e4

    public void castle(String kingFrom, String kingTo, String rookFrom, String rookTo) {
        System.out.println("Castling: King " + kingFrom + " -> " + kingTo + ", Rook " + rookFrom + " -> " + rookTo);
    } // Castling: King e1 -> g1, Rook h1 -> f1

    public void enPassant(String from, String to, String capturedPawnPos) {
        System.out.println("En Passant: " + from + " -> " + to + ", capturing pawn at " + capturedPawnPos);
    } // En Passant: e5 -> d6, capturing pawn at d5
}


// invoker
public class MoveInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}


public class Main {
    public static void main(String[] args) {
        ChessGame game = new ChessGame();           // "device" (tv, stereo, etc.)
        MoveInvoker invoker = new MoveInvoker();    // "remote control"

        Command move = new MoveCommand(game, "e2", "e4");
        Command castle = new CastlingCommand(game, "e1", "g1", "h1", "f1"); // King-side castle
        Command enPassant = new EnPassantCommand(game, "e5", "d6", "d5");

        invoker.setCommand(move);
        invoker.pressButton(); // "Moving piece from e2 to e4"

        move.setFrom("e1"); // set a different starting position
        move.setTo("e2");   // set a different ending position

        invoker.pressButton(); // "Moving piece from e1 to e2"

        invoker.setCommand(castle);
        invoker.pressButton(); // "Castling: King e1 -> g1, Rook h1 -> f1"

        invoker.setCommand(enPassant);
        invoker.pressButton(); // "En Passant: e5 -> d6, capturing pawn at d5"
    }
}