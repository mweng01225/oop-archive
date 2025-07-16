public class MyChessInterface {
    public static void main(String[] args) {
        // define rook attributes
        String rookColor = "White";
        char rookX = 'D';
        int rookY = 4;

        // create rook and bishop with specific values
        Rook rook = new Rook(rookColor, rookX, rookY);
        Bishop bishop = new Bishop("Black", 'F', 6);

        // rook values
        System.out.println("Rook Color: " + rookColor);
        System.out.println("Rook Position: " + rookX + rookY);
        System.out.println();

        // extra bishop method in IntBishop
        bishop.printValues();
        System.out.println();

        // rook moves
        System.out.println("Rook moving to D1: " + rook.isValidMove('D', 1));     // yes
        System.out.println("Rook moving to E5: " + rook.isValidMove('E', 5));     // no
        System.out.println();

        // bishop moves
        System.out.println("Bishop moving to H8: " + bishop.isValidMove('H', 8)); // yes
        System.out.println("Bishop moving to A8: " + bishop.isValidMove('A', 8)); // no


    }
}