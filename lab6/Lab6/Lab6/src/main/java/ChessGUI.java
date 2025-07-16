import figures.*;
import interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChessGUI extends JFrame {
    // Set up the chessboard
    private ChessBoard board = new ChessBoard();

    // Set to track used pieces (as required in the assignment)
    private Set<ChessPiece> usedPieces = new HashSet<>();

    // GUI Components
    private JComboBox<ChessPiece> pieceSelector;
    private JComboBox<String> colorSelector;
    private JComboBox<Character> initialXSelector;
    private JSpinner initialYSelector;
    private JComboBox<Character> targetXSelector;
    private JSpinner targetYSelector;
    private JButton placePieceButton;
    private JButton moveButton;
    private JButton newPieceButton;
    private JButton exitButton;
    private JLabel statusLabel;

    // Current piece and position
    private Figure currentPiece;
    private ChessPiece currentPieceType;
    private char currentX;
    private int currentY;

    // Chess board panel
    private ChessBoardPanel boardPanel;

    public ChessGUI() {
        // Set up the JFrame
        super("Chess Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create the control panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.WEST);

        // Create the chess board panel
        boardPanel = new ChessBoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Create status panel
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Select a piece, set its color and position, then place it.");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        // Set the frame visible
        setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Piece selection
        JPanel piecePanel = new JPanel();
        piecePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        piecePanel.add(new JLabel("Select Piece:"));
        pieceSelector = new JComboBox<>(ChessPiece.values());
        piecePanel.add(pieceSelector);
        panel.add(piecePanel);

        // Color selection
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        colorPanel.add(new JLabel("Select Color:"));
        colorSelector = new JComboBox<>(new String[]{"WHITE", "BLACK"});
        colorPanel.add(colorSelector);
        panel.add(colorPanel);

        // Initial position
        JPanel initialPosPanel = new JPanel();
        initialPosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        initialPosPanel.add(new JLabel("Initial Position:"));

        Character[] columns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        initialXSelector = new JComboBox<>(columns);
        initialPosPanel.add(initialXSelector);

        SpinnerNumberModel initialYModel = new SpinnerNumberModel(1, 1, 8, 1);
        initialYSelector = new JSpinner(initialYModel);
        initialPosPanel.add(initialYSelector);

        panel.add(initialPosPanel);

        // Place piece button
        JPanel placePiecePanel = new JPanel();
        placePieceButton = new JButton("Place Piece");
        placePiecePanel.add(placePieceButton);
        panel.add(placePiecePanel);

        // Target position
        JPanel targetPosPanel = new JPanel();
        targetPosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        targetPosPanel.add(new JLabel("Target Position:"));

        targetXSelector = new JComboBox<>(columns);
        targetPosPanel.add(targetXSelector);

        SpinnerNumberModel targetYModel = new SpinnerNumberModel(1, 1, 8, 1);
        targetYSelector = new JSpinner(targetYModel);
        targetPosPanel.add(targetYSelector);

        panel.add(targetPosPanel);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        moveButton = new JButton("Move Piece");
        moveButton.setEnabled(false);
        buttonsPanel.add(moveButton);

        newPieceButton = new JButton("New Piece");
        newPieceButton.setEnabled(false);
        buttonsPanel.add(newPieceButton);

        exitButton = new JButton("Exit");
        buttonsPanel.add(exitButton);

        panel.add(buttonsPanel);

        // Add action listeners
        placePieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePiece();
            }
        });

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePiece();
            }
        });

        newPieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForNewPiece();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return panel;
    }

    private void placePiece() {
        // Get selected values
        ChessPiece selectedPiece = (ChessPiece) pieceSelector.getSelectedItem();
        String selectedColor = (String) colorSelector.getSelectedItem();
        char selectedX = (Character) initialXSelector.getSelectedItem();
        int selectedY = (Integer) initialYSelector.getValue();

        // Check if piece is already used
        if (usedPieces.contains(selectedPiece)) {
            statusLabel.setText("Error: This piece is already in use. Choose another.");
            return;
        }

        // Verify coordinates
        if (!board.verifyCoordinate(selectedX, selectedY)) {
            statusLabel.setText("Error: Invalid board position.");
            return;
        }

        // Create the chess piece
        currentPiece = createChessPiece(selectedPiece, selectedColor, selectedX, selectedY);
        currentPieceType = selectedPiece;

        // Add to used pieces
        usedPieces.add(selectedPiece);

        // Save current position
        currentX = selectedX;
        currentY = selectedY;

        // Update board
        boardPanel.placePiece(selectedPiece, selectedColor, selectedX, selectedY);
        boardPanel.repaint();

        // Update UI
        String pieceName = selectedPiece.toString();
        statusLabel.setText(selectedColor + " " + pieceName + " placed at " +
                selectedX + selectedY + ". Select a target position to move to.");
        placePieceButton.setEnabled(false);
        moveButton.setEnabled(true);
        newPieceButton.setEnabled(true);
    }

    private void movePiece() {
        // Get target position
        char targetX = (Character) targetXSelector.getSelectedItem();
        int targetY = (Integer) targetYSelector.getValue();

        // Verify coordinates
        if (!board.verifyCoordinate(targetX, targetY)) {
            statusLabel.setText("Error: Invalid target position.");
            return;
        }

        // Check if already at target
        if (currentX == targetX && currentY == targetY) {
            statusLabel.setText(currentPieceType.toString() + " is already at " + targetX + targetY);
            return;
        }

        // Check if the piece can move to the target
        boolean canMove = currentPiece.moveTo(targetX, targetY);

        if (canMove) {
            // Update the board
            boardPanel.removePiece(currentX, currentY);

            // Update the piece position
            currentPiece.setX(targetX);
            currentPiece.setY(targetY);

            boardPanel.placePiece(currentPieceType, currentPiece.getColor(), targetX, targetY);
            boardPanel.repaint();

            // Update current position
            currentX = targetX;
            currentY = targetY;

            statusLabel.setText(currentPieceType.toString() + " moved to " + targetX + targetY);
        } else {
            statusLabel.setText(currentPieceType.toString() + " cannot move to " + targetX + targetY);
        }
    }

    private void resetForNewPiece() {
        // Reset UI for new piece
        placePieceButton.setEnabled(true);
        moveButton.setEnabled(false);

        // Clear board
        boardPanel.clearBoard();
        boardPanel.repaint();

        statusLabel.setText("Select a new piece, set its color and position, then place it.");
    }

    // Create a chess piece based on type
    private Figure createChessPiece(ChessPiece pieceType, String color, char x, int y) {
        switch (pieceType) {
            case PAWN:
                return new Pawn(color, x, y);
            case ROOK:
                return new Rook(color, x, y);
            case KNIGHT:
                return new Knight(color, x, y);
            case BISHOP:
                return new Bishop(color, x, y);
            case QUEEN:
                return new Queen(color, x, y);
            case KING:
                return new King(color, x, y);
            default:
                return null; // should not happen
        }
    }

    // Inner class for the chess board panel
    private class ChessBoardPanel extends JPanel {
        private final int BOARD_SIZE = 8;
        private final int CELL_SIZE = 60;
        private final Map<String, PieceInfo> piecePositions = new HashMap<>();

        public ChessBoardPanel() {
            setPreferredSize(new Dimension(BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        // Store piece type and color
        private class PieceInfo {
            ChessPiece type;
            String color;

            PieceInfo(ChessPiece type, String color) {
                this.type = type;
                this.color = color;
            }
        }

        public void placePiece(ChessPiece type, String color, char x, int y) {
            String key = x + "," + y;
            piecePositions.put(key, new PieceInfo(type, color));
        }

        public void removePiece(char x, int y) {
            String key = x + "," + y;
            piecePositions.remove(key);
        }

        public void clearBoard() {
            piecePositions.clear();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the chess board
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    // Alternate cell colors
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(new Color(139, 69, 19)); // Brown
                    }

                    g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }

            // Draw column labels (a-h)
            g.setColor(Color.BLACK);
            for (int col = 0; col < BOARD_SIZE; col++) {
                g.drawString(String.valueOf((char)('a' + col)),
                        col * CELL_SIZE + CELL_SIZE / 2,
                        BOARD_SIZE * CELL_SIZE + 15);
            }

            // Draw row labels (1-8)
            for (int row = 0; row < BOARD_SIZE; row++) {
                g.drawString(String.valueOf(BOARD_SIZE - row),
                        BOARD_SIZE * CELL_SIZE + 5,
                        row * CELL_SIZE + CELL_SIZE / 2);
            }

            // Draw the chess pieces
            for (Map.Entry<String, PieceInfo> entry : piecePositions.entrySet()) {
                String[] coordinates = entry.getKey().split(",");
                char col = coordinates[0].charAt(0);
                int row = Integer.parseInt(coordinates[1]);
                PieceInfo pieceInfo = entry.getValue();

                // Draw the piece
                drawPiece(g, pieceInfo.type, pieceInfo.color, col, row);
            }
        }

        private void drawPiece(Graphics g, ChessPiece type, String color, char x, int y) {
            // Convert coordinates to screen position
            int screenX = (x - 'a') * CELL_SIZE;
            int screenY = (8 - y) * CELL_SIZE; // Invert y-axis

            // Calculate center position for piece
            int centerX = screenX + CELL_SIZE / 2;
            int centerY = screenY + CELL_SIZE / 2;

            // Set color
            g.setColor(color.equals("WHITE") ? Color.WHITE : Color.BLACK);

            // Draw circle for the piece
            int circleSize = (int)(CELL_SIZE * 0.8);
            int offset = (CELL_SIZE - circleSize) / 2;
            g.fillOval(screenX + offset, screenY + offset, circleSize, circleSize);

            // Draw outline
            g.setColor(color.equals("WHITE") ? Color.BLACK : Color.WHITE);
            g.drawOval(screenX + offset, screenY + offset, circleSize, circleSize);

            // Draw piece type
            String symbol = "";
            switch (type) {
                case PAWN: symbol = "P"; break;
                case ROOK: symbol = "R"; break;
                case KNIGHT: symbol = "N"; break;
                case BISHOP: symbol = "B"; break;
                case QUEEN: symbol = "Q"; break;
                case KING: symbol = "K"; break;
            }

            // Use larger font for pieces
            Font pieceFont = new Font("Arial", Font.BOLD, 20);
            g.setFont(pieceFont);
            FontMetrics metrics = g.getFontMetrics(pieceFont);
            int textWidth = metrics.stringWidth(symbol);
            int textHeight = metrics.getHeight();

            g.drawString(symbol, centerX - textWidth / 2, centerY + textHeight / 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessGUI();
            }
        });
    }
}