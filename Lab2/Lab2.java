import java.util.Scanner;

class Lab2{
    //variables for all methods to use
    enum PieceType {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    // colors
    enum Colors {
        BLACK,
        WHITE
    }

    // Create scanner, this won't be closed until the program ends
    private static Scanner s = new Scanner(System.in);

    // Chessboard class to verify piece
    private static Chessboard board = new Chessboard();

    public static void main(String args[]){
        // chesspiece array
        ChessPiece[] pieces = new ChessPiece[6];


        for(int i = 0; i < 6; i++){
            while(true){
                // Ask user for piece
                String piece = input("Select a chess piece: (Pawn | Knight | Bishop | Rook | Queen | King)");
                PieceType pieceType = isValidPiece(piece);

                if(pieceType == null){
                    System.out.println("Invalid piece type, try again.");
                    continue;
                }

                // Pawn=0, Rook=1, Knight=2, Bishop=3, Queen=4, King=5..
                int index = pieceType.ordinal();
                //System.out.println(index);


                // Check if there's already that piece in the array
                if (pieces[index] != null){
                    System.out.println("You already added a " + pieceType + ". Choose another chess piece.");
                    continue;
                }

                // get color
                String color = askUserColor();

                // get valid position
                int[] position = askUserPosition("initial");
                int x = position[0];
                int y = position[1];

                pieces[index] = (ChessPiece) createChessPiece(pieceType, color, x, y);
                break;
            }
        }

        // Step 2: Prompt user for target position
        int[] target = askUserPosition("target");
        int targetX = target[0];
        int targetY = target[1];

        // Step 3: Traverse the array and verify moves (no if/switch used for class types)
        for (ChessPiece piece : pieces) {
            boolean canMove = piece.verifyMove(targetX, targetY);
            String move;
            if(canMove){
                move = " can move to ";
            } else {
                move = " can NOT move to ";
            }

            System.out.println(piece.piece_name + " at " + convertNumberToLetter(piece.getColumn()) + " " + (piece.getRow() + 1)
                    + move + convertNumberToLetter(targetX) + " " +(targetY + 1));
        }
    }

    // ----- Ask for position, verifies position, returns [posX, posY]
    public static int[] askUserPosition(String input){ // input should either be "final" or "initial"
        int[] position;

        // --- Ask user for position ('h 8' or 'a 5') ---
        while (true){
            String init_pos = input("Choose a " + input + " position ('h 8' or 'a 5'.. etc.)");

            // --- Verify user's input position ---
            position = isValidInput(init_pos); // Returns [int,int]

            // Check input
            if(position == null){
                System.out.println("Invalid input");
                continue; // ask for position again
            }

            // Everything checks out, break out of loop
            break;
        }
        return position; // [int, int]
    }


    // This method takes in a string, prints the string, and then takes in the next user input
    public static String input(String input_str){
        System.out.println(input_str);
        return s.nextLine();
    }


    // Convert a character (e.g., "a", "b") to a 0-indexed number
    public static int convertLetterToNumber(char letter) {
        return Character.toLowerCase(letter) - 'a';
    }

    // Convert a number (e.g., 0) to a character (e.g., "A") 0 -> A, 1 -> B, etc.
    public static String convertNumberToLetter(int number) {
        return (char) ('A' + number) + "";
    }


    // This method takes in position, verifies, and returns (int,int)
    public static int[] isValidInput(String s){
        /* Example input -> output
        "h 3" -> [7, 3]
        " a 6 " -> [1, 6]
        "b4" -> ['b', 4]
        */
        s = s.trim().toLowerCase().replaceAll(" ", "");

        // Ensure it's actually a valid input
        if (s.length() != 2){
            return null;
        }

        // Extract info from s
        char x = s.charAt(0);
        int y = Character.getNumericValue(s.charAt(1));

        // Check if valid input
        if(!board.withinChessboard(x, y)){
            return null;
        }

        return new int[]{convertLetterToNumber(x), y - 1}; // zero index
    }


    // ----- Ask user for color, returns string----- 
    public static String askUserColor(){
        String color;
        while(true){
            color = input("Choose a color: 'WHITE' or 'BLACK'").trim().toUpperCase();
            try{
                return Colors.valueOf(color.toUpperCase()).name();
            }
            catch (IllegalArgumentException e){ // not in colors enum
                System.out.println("Not a valid color");
            }
        }
    }


    // Takes in user's input and checks if it's in enum PieceType
    public static PieceType isValidPiece(String user_piece){
        try{
            return PieceType.valueOf(user_piece.toUpperCase());
        }
        catch (IllegalArgumentException e){ // not in enum
            return null;
        }
    }


    // Create a piece object based on name
    public static ChessPiece createChessPiece(PieceType pieceType, String color, int x, int y){
        switch (pieceType){
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
                return null;
        }
    }
}