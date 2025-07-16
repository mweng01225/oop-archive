import figures.*;

public class ChessGame {
    private Figure[][] board = new Figure[8][8];
    private String currentTurn = "WHITE";

    public ChessGame() {
        initializeBoard();
    }

    private void initializeBoard() {
        // place white major pieces
        board[rowIndex(1)][colIndex('a')] = new Rook("WHITE", 'a', 1);
        board[rowIndex(1)][colIndex('b')] = new Knight("WHITE", 'b', 1);
        board[rowIndex(1)][colIndex('c')] = new Bishop("WHITE", 'c', 1);
        board[rowIndex(1)][colIndex('d')] = new Queen("WHITE", 'd', 1);
        board[rowIndex(1)][colIndex('e')] = new King("WHITE", 'e', 1);
        board[rowIndex(1)][colIndex('f')] = new Bishop("WHITE", 'f', 1);
        board[rowIndex(1)][colIndex('g')] = new Knight("WHITE", 'g', 1);
        board[rowIndex(1)][colIndex('h')] = new Rook("WHITE", 'h', 1);

        // place black major pieces
        board[rowIndex(8)][colIndex('a')] = new Rook("BLACK", 'a', 8);
        board[rowIndex(8)][colIndex('b')] = new Knight("BLACK", 'b', 8);
        board[rowIndex(8)][colIndex('c')] = new Bishop("BLACK", 'c', 8);
        board[rowIndex(8)][colIndex('d')] = new Queen("BLACK", 'd', 8);
        board[rowIndex(8)][colIndex('e')] = new King("BLACK", 'e', 8);
        board[rowIndex(8)][colIndex('f')] = new Bishop("BLACK", 'f', 8);
        board[rowIndex(8)][colIndex('g')] = new Knight("BLACK", 'g', 8);
        board[rowIndex(8)][colIndex('h')] = new Rook("BLACK", 'h', 8);

        // place black and white pawns on row 2 and 7
        for (char x = 'a'; x <= 'h'; x++) {
            board[rowIndex(2)][colIndex(x)] = new Pawn("WHITE", x, 2);
            board[rowIndex(7)][colIndex(x)] = new Pawn("BLACK", x, 7);
        }

    }
    private boolean isPathClear(char fromX, int fromY, char toX, int toY) {
        int xStep = Integer.compare(toX, fromX); // -1, 0, or 1
        int yStep = Integer.compare(toY, fromY); // -1, 0, or 1

        int currX = fromX + xStep;
        int currY = fromY + yStep;

        while (currX != toX || currY != toY) {
            if (getPiece((char) currX, currY) != null) {
                return false; // Something is blocking the path
            }

            currX += xStep;
            currY += yStep;
        }

        return true;
    }

    public boolean move(char fromX, int fromY, char toX, int toY) {
        // Convert positions
        int fromRow = rowIndex(fromY);
        int fromCol = colIndex(fromX);
        int toRow = rowIndex(toY);
        int toCol = colIndex(toX);

        Figure movingPiece = board[fromRow][fromCol];

        if (movingPiece == null) return false;                          // no piece on this position
        if (!movingPiece.getColor().equals(currentTurn)) return false;  // check if it's player's turn
        if (!movingPiece.moveTo(toX, toY)) return false;                // check if its a valid move

        if (movingPiece instanceof Bishop || movingPiece instanceof Rook || movingPiece instanceof Queen) {
            if (!isPathClear(fromX, fromY, toX, toY)) {
                return false; // Path is blocked
            }
        }

        if (movingPiece instanceof Pawn && fromX == toX) {
            if (getPiece(toX, toY) != null) return false; // Can't move forward into occupied square
        }

        Figure targetPiece = board[toRow][toCol];

        if (targetPiece != null && targetPiece.getColor().equals(currentTurn)) return false; // Can't capture your own piece

        // Move piece
        board[toRow][toCol] = movingPiece;
        board[fromRow][fromCol] = null;
        movingPiece.setX(toX);
        movingPiece.setY(toY);
        return true;
    }

    public boolean canMove(char fromX, int fromY, char toX, int toY) {
        Figure movingPiece = getPiece(fromX, fromY);
        if (movingPiece == null || !movingPiece.getColor().equals(currentTurn)) return false;
        if (!movingPiece.moveTo(toX, toY)) return false;

        Figure targetPiece = getPiece(toX, toY);
        if (targetPiece != null && targetPiece.getColor().equals(currentTurn)) return false;

        // Add any extra logic here (e.g., pawn blocking, path clearance)
        if (movingPiece instanceof Pawn) {
            if (fromX == toX && getPiece(toX, toY) != null) return false;
            if (Math.abs(toY - fromY) == 2) {
                int midY = (fromY + toY) / 2;
                if (getPiece(toX, midY) != null) return false;
            }
            if (Math.abs(toX - fromX) == 1 && getPiece(toX, toY) == null) return false;
        }

        if (movingPiece instanceof Rook || movingPiece instanceof Bishop || movingPiece instanceof Queen) {
            if (!isPathClear(fromX, fromY, toX, toY)) return false;
        }

        return true;
    }

    public boolean isInCheck(String color) {
        char kingX = 0;
        int kingY = 0;

        // Find the king
        for (int row = 1; row <= 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {
                Figure piece = getPiece(col, row);
                if (piece instanceof King && piece.getColor().equals(color)) {
                    kingX = col;
                    kingY = row;
                    break;
                }
            }
        }

        // Check if any opposing piece can attack the king's square
        for (int row = 1; row <= 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {
                Figure piece = getPiece(col, row);
                if (piece != null && !piece.getColor().equals(color)) {
                    if (canMove(col, row, kingX, kingY)) {
                        return true; // King is in check
                    }
                }
            }
        }

        return false;
    }

    public boolean hasLegalMoves(String color) {
        for (int row = 1; row <= 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {
                Figure piece = getPiece(col, row);
                if (piece != null && piece.getColor().equals(color)) {
                    for (char tx = 'a'; tx <= 'h'; tx++) {
                        for (int ty = 1; ty <= 8; ty++) {
                            if (canMove(col, row, tx, ty)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(String color) {
        return isInCheck(color) && !hasLegalMoves(color);
    }

    public void switchTurn(){
        currentTurn = currentTurn.equals("WHITE") ? "BLACK" : "WHITE";
    }

    public Figure getPiece(char x, int y) {
        return board[rowIndex(y)][colIndex(x)];
    }

    public String getTurn(){
        return currentTurn;
    }

    private int rowIndex(int y) {
        return 8 - y;
    }

    private int colIndex(char x) {
        return x - 'a';
    }
}
