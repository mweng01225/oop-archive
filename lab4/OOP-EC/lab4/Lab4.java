package lab4;

import lab4.figures.*;
import lab4.interfaces.*;

import java.util.*;

public class Lab4 {
    private static Scanner s = new Scanner(System.in);
    private static ChessBoard board = new ChessBoard();

    // colors
    enum Colors {
        BLACK,
        WHITE
    }

    public static void main(String[] args) {
        // Store every piece other than the bishop
        Figure[] pieces = new Figure[5];
        IntBishop bishop = null;

        // Hashset for no duplicates
        Set<ChessPiece> pieceSet = new HashSet<>();

        // Get the 6 pieces
        for(int i = 0; i < 6; i++){
            // Ask user for piece
            // This method guarantees a valid piece is returned
            ChessPiece piece = getPiece(pieceSet);

            // Get BLACK or WHITE from user
            String color = askUserColor();

            // Get a valid position
            Object[] position = askUserPosition("initial");
            LocationX posX = LocationX.valueOf(String.valueOf(Character.toUpperCase((char) position[0])));
            int posY = (int) position[1];
            // System.out.println(position[0]);
            // System.out.println(position[1]);

            // Store bishop separately, else put into array
            if (piece == ChessPiece.BISHOP) {
                bishop = new Bishop(color, posX, posY);
            } else {
                pieces[i - (bishop == null ? 0 : 1)] = createChessPiece(piece, color, posX, posY);
            }
        }

        // Ask for final position
        Object[] finalPosition = askUserPosition("final");
        LocationX targetX = LocationX.valueOf(String.valueOf(Character.toUpperCase((char) finalPosition[0])));
        int targetY = (int) finalPosition[1];

        // Check each piece move
        for (Figure piece : pieces) {
            boolean canMove = piece.moveTo(targetX, targetY);
            if(canMove){
                System.out.println(piece + " can move to " + targetX.name() + targetY);
            } else {
                System.out.println(piece + " can NOT move to " + targetX.name() + targetY);
            }
        }

        // Lone bishop needs to be checked
        if (bishop.moveTo(targetX, targetY)) {
            System.out.println(bishop + " can move to " + targetX + targetY);
        } else {
            System.out.println(bishop + " can NOT move to " + targetX + targetY);
        }

    }

    // Helper method
    private static String input(String input_str){
        System.out.println(input_str);
        return s.nextLine();
    }

    // ----- Get and validate chess piece, will return a valid, non duplicate piece -----
    private static ChessPiece getPiece(Set<ChessPiece> pieceSet){
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
            if (pieceSet.contains(piece)) { 
                System.out.println("Duplicate piece chosen. Try a different piece.");
                continue;
            }

            // Add piece to set and return
            pieceSet.add(piece);
            return piece;
        }
    }

    // ----- Ask user for color, returns valid color ----- 
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

    // ----- Ask for position, verifies position, returns [char posX, 'char' posY] -----
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

    // Create a piece object based on name
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
                return null;
        }
    }
}
