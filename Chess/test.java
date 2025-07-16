public class test{
    public static void main(String args[]){
        ChessGame game = new ChessGame();

        System.out.println("Initial Test:");
        System.out.println("Piece at e2: " + game.getPiece('e', 2));
        System.out.println("Current turn: WHITE");

        // Try moving white pawn from e2 to e4
        boolean moved = game.move('e', 2, 'e', 4);
        System.out.println("Move e2 to e4: " + moved);  // should be true
        game.switchTurn();

        // Try moving black pawn from d7 to d5
        moved = game.move('d', 7, 'd', 5);
        System.out.println("Move d7 to d5: " + moved);  // should be true
        game.switchTurn();

        // Try moving white knight from g1 to f3
        moved = game.move('g', 1, 'f', 3);
        System.out.println("Move g1 to f3: " + moved);  // should be true
        game.switchTurn();

        // Try illegal move: black queen jumps over pawn
        moved = game.move('d', 8, 'h', 4);
        System.out.println("Move d8 to h4 (should be false): " + moved);

        System.out.println("Current turn: " + game.getTurn());
        game.switchTurn();

        moved = game.move('b',1,'c',3);
        System.out.println("Moved knight in front of pawn " + moved);
        game.switchTurn();

        moved = game.move('c',2,'c',4);
        System.out.println("Trying to move pawn (should be false): " + moved);

        // Show updated board position (optional)
        System.out.println("Piece at e4: " + game.getPiece('e', 4));
        System.out.println("Piece at e2: " + game.getPiece('e', 2));  // should be null
    }
}