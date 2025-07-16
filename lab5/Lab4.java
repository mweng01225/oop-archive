package lab4;

import lab4.figures.*;
//import lab4.interfaces.*;

import java.util.*;

/**
 * Main class
 * Prompts the user to input six unique chess pieces and their starting positions.
 * After getting the unique pices, it asks for a target position and checks if each piece can legally move to it.
 * 
 * @implNote This method uses a HashMap to track chess pieces and their positions,
 *           separates logic for Bishops, and handles board input validation through helper methods.
 * @author Marvin Weng
 * @version 1.0
 * @since 3/17/2025
 */

public class Lab4 {
    private static Scanner s = new Scanner(System.in);
    private static ChessBoard board = new ChessBoard();

    // colors
    enum Colors {
        BLACK,
        WHITE
    }

    /**
     * Main method that runs the chess simulation.
     * Handles user input, creates pieces, and checks move validity.
     *
     */
    public static void main(String[] args) {
        // Store chess positions except bishop for tracking if curr==target
        Map<ChessPiece, Object[]> pieceMap = new HashMap<>();
        // Key = ChessPiece : Value = Object[(Figure) piece, (char) posX, (int) posY]

        // THIS IS NOT NEEDED AS A HASHMAP SERVES THE SAME PURPOSE
        //Figure[] pieces = new Figure[5];

        // Store bishop separately
        Bishop bishop = null;

        // Get the 6 pieces
        for(int i = 0; i < 6; i++){
            // Ask user for piece
            // This method guarantees a valid piece is returned
            ChessPiece piece = getPiece(pieceMap);

            // Get BLACK or WHITE from user
            String color = askUserColor();

            // Get a valid position
            Object[] position = askUserPosition("initial");
            LocationX posX = LocationX.valueOf(String.valueOf(Character.toUpperCase((char) position[0])));
            int posY = (int) position[1];

            // Create bishop separately
            if (piece == ChessPiece.BISHOP) {
                bishop = new Bishop(color, posX, posY);
                pieceMap.put(piece, new Object[]{bishop, posX, posY});
            } else {
                Figure newPiece = createChessPiece(piece, color, posX, posY);
                pieceMap.put(piece, new Object[]{newPiece, posX, posY});
            }
        }

        // Ask for final position
        Object[] finalPosition = askUserPosition("final");
        LocationX targetX = LocationX.valueOf(String.valueOf(Character.toUpperCase((char) finalPosition[0])));
        int targetY = (int) finalPosition[1];


        // Check each piece move
        for (Map.Entry<ChessPiece, Object[]> entry : pieceMap.entrySet()) {
            // Get the position that the piece was placed on
            ChessPiece pieceName = entry.getKey();

            LocationX posX;
            int posY;

            if (pieceName == ChessPiece.BISHOP){
                // Lone bishop needs to be checked differently
                Object[] values = entry.getValue();

                // bishop already stored in IntBishop variable above
                posX = (LocationX) values[1];
                posY = (int) values[2];

                if (posX == targetX && posY == targetY){
                    System.out.println(bishop + " is already at " + targetX + targetY);
                } else {
                    if (bishop.moveTo(targetX, targetY)) {
                        System.out.println(bishop + " can move to " + targetX + targetY);
                    } else {
                        System.out.println(bishop + " can NOT move to " + targetX + targetY);
                    }
                }
                continue; // Ignore rest of loop since it will break
            }

            // Extract values and cast appropriately
            Object[] values = entry.getValue();

            Figure piece = (Figure) values[0];
            posX = (LocationX) values[1];
            posY = (int) values[2];


            // Verify if piece is on target position
            if (posX == targetX && posY == targetY){
                System.out.println(piece + " is already at " + targetX + targetY);
            } else {
                boolean canMove = piece.moveTo(targetX, targetY);
                if(canMove){
                    System.out.println(piece + " can move to " + targetX + targetY);
                } else {
                    System.out.println(piece + " can NOT move to " + targetX + targetY);
                }
            }
        }
    }

    /**
     * Prompts the user for input and returns it as a string.
     * 
     * @param input_str the prompt message to display
     * @return the user's input
     * @author Marvin Weng
     */
    private static String input(String input_str){
        System.out.println(input_str);
        return s.nextLine();
    }


    /**
     * Get and validate chess piece, will return a valid, non duplicate piece
     * @param pieceMap the map of already selected pieces
     * @return a valid and unique ChessPiece
     * @author Marvin Weng
     */
    private static ChessPiece getPiece(Map<ChessPiece, Object[]> pieceMap){
        // Prompt user until valid chess piece is provided
        ChessPiece piece;
        while (true){
            // Ask user for chess piece
            String input = input("Select a chess piece: (Pawn | Knight | Bishop | Rook | Queen | King)").trim().toUpperCase();


            // Convert piece to ChessPiece object
            try{
                piece = ChessPiece.valueOf(input);                
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid piece type. Try again.");
                continue;
            }

            // Check if the set already contains this piece
            if (pieceMap.containsKey(piece)) { 
                System.out.println("Duplicate piece chosen. Try a different piece.");
                continue;
            }

            return piece;
        }
    }

    /**
     * Ask user for color, returns valid color
     * @return "WHITE" or "BLACK"
     * @author Marvin Weng
     */
    public static String askUserColor(){
        String color;
        while(true){
            color = input("Choose a color: 'WHITE' or 'BLACK'");
            try{
                return Colors.valueOf(color.toUpperCase()).name();
            }
            catch (IllegalArgumentException e){ // not in colors enum
                System.out.println("Not a valid color");
            }
        }
    }

    /**
     * Ask for position, verifies position, returns [char posX, 'char' posY]
     * @throws IllegalArgumentException if the user enters a string that is not a valid board position
     * @param input either "initial" or "final" to determine the prompt type
     * @return an Object[] containing the x and y position
     * @author Marvin Weng
     */
    public static Object[] askUserPosition(String input){ // input should either be "final" or "initial"
        while (true){
            try{
                // Ask for user position
                String init_pos = input("Choose a " + input + " position ('h 8' or 'a 5'.. etc.)");

                init_pos = init_pos.trim().toLowerCase().replaceAll(" ", "");

                // check length == 2
                if (init_pos.length() != 2){
                    throw new IllegalArgumentException();
                }

                // Extract info from init_pos
                char x = init_pos.charAt(0);
                int y = Character.getNumericValue(init_pos.charAt(1));

                // Check if valid input
                if(!board.verifyCoordinate(LocationX.valueOf(String.valueOf(Character.toUpperCase(x))), y)){
                    throw new IllegalArgumentException();
                }

                // Everything checks out, return values
                return new Object[]{x, y};
            } catch (IllegalArgumentException e){
                System.out.println("Not a valid position on the board");
            }
        }
    }


    /**
     * Create a piece object based on name
     * 
     * @param pieceType the ChessPiece type
     * @param color the color of the piece
     * @param x the horizontal board position
     * @param y the vertical board position
     * @return a new Figure representing the chess piece
     * @author Marvin Weng
     */
    public static Figure createChessPiece(ChessPiece pieceType, String color, LocationX x, int y){
        switch (pieceType){
            case PAWN:
                return new Pawn(color, x, y);
            case ROOK:
                return new Rook(color, x, y);
            case KNIGHT:
                return new Knight(color, x, y);
            case QUEEN:
                return new Queen(color, x, y);
            case KING:
                return new King(color, x, y);
            default:
                return null; // this should not happen
        }
    }
}
