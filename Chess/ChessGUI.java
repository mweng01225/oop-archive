import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import figures.*;
import java.util.List;
import java.util.ArrayList;

public class ChessGUI extends JFrame {
    private ChessGame game = new ChessGame();         //game logic handler
    private BoardPanel boardPanel = new BoardPanel(); //Jpanel for board

    public ChessGUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        add(boardPanel); // add board panel to the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGUI());
    }

    // draws the chessboard and handles user input
    private class BoardPanel extends JPanel {
        private final int CELL_SIZE = 70; // size of each equare
        private char selectedX = 0;       // x coordinate (col)
        private int selectedY = 0;        // y coordinate (row)
        private List<Point> validMoves = new ArrayList<>(); // highlighting valid squares

        public BoardPanel() {
            setPreferredSize(new Dimension(8 * CELL_SIZE, 8 * CELL_SIZE));

            addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int col = e.getX() / CELL_SIZE;
                int row = e.getY() / CELL_SIZE;

                char clickedX = (char) ('a' + col);
                int clickedY = 8 - row;

                Figure clickedPiece = game.getPiece(clickedX, clickedY);

                // select a piece if its the player turn
                if (selectedX == 0 && clickedPiece != null && clickedPiece.getColor().equals(game.getTurn())) {
                    selectedX = clickedX;
                    selectedY = clickedY;
                    validMoves.clear();

                    // generate valid moves for selected piece
                    for (char tx = 'a'; tx <= 'h'; tx++) {
                        for (int ty = 1; ty <= 8; ty++) {
                            if (game.canMove(selectedX, selectedY, tx, ty)) {
                                validMoves.add(new Point(tx - 'a', 8 - ty));
                            }
                        }
                    }
                } else {
                    // Second click for moving
                    if (game.move(selectedX, selectedY, clickedX, clickedY)) {
                        String opponent = game.getTurn().equals("WHITE") ? "BLACK" : "WHITE";
                        if (game.isCheckmate(opponent)) {
                            JOptionPane.showMessageDialog(null, "Checkmate! " + game.getTurn() + " wins!");
                        }
                        game.switchTurn(); // Switch turns after a valid move
                    }
                    selectedX = 0;
                    selectedY = 0;
                    validMoves.clear();
                }

                repaint();
            }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // draw squares
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    boolean light = (row + col) % 2 == 0;
                    g.setColor(light ? Color.WHITE : Color.GRAY);
                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }

            // highlight selected squares
            if (selectedX != 0) {
                int sx = (selectedX - 'a') * CELL_SIZE;
                int sy = (8 - selectedY) * CELL_SIZE;
                g.setColor(Color.YELLOW);
                g.fillRect(sx, sy, CELL_SIZE, CELL_SIZE);
            }

            // highlight valid move targets
            g.setColor(new Color(0, 255, 0, 100)); // semi-transparent green
            for (Point p : validMoves) {
                g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            // draw pieces
            for (int row = 1; row <= 8; row++) {
                for (char col = 'a'; col <= 'h'; col++) {
                    Figure piece = game.getPiece(col, row);
                    if (piece != null) {
                        drawPiece(g, piece, col, row);
                    }
                }
            }
        }

        // draw chess piece as a filled circle with its symbol
        // todo: import images and replace this
        private void drawPiece(Graphics g, Figure piece, char x, int y) {
            int col = x - 'a';
            int row = 8 - y;

            g.setColor(piece.getColor().equals("WHITE") ? Color.WHITE : Color.BLACK);
            g.fillOval(col * CELL_SIZE + 10, row * CELL_SIZE + 10, CELL_SIZE - 20, CELL_SIZE - 20);

            g.setColor(piece.getColor().equals("WHITE") ? Color.BLACK : Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            String symbol = piece.getClass().getSimpleName().substring(0, 1);
            g.drawString(symbol, col * CELL_SIZE + 28, row * CELL_SIZE + 42);
        }
    }
}
