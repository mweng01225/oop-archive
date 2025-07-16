public abstract class ChessPiece{
    protected String piece_name;
    protected String color;
    protected int x;
    protected int y;

    // constructors
    public ChessPiece(){}

    public ChessPiece(String piece_name, String color, int x, int y){
        this.piece_name = piece_name;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // getters
    public String getColor(){
        return this.color;
    }

    public int getColumn(){
        return this.x;
    }
    public int getRow(){
        return this.y;
    }

    // setters
    public void setColumn(int column) {
        this.x = column;
    }
    public void setRow(int row) {
        this.y = row;
    }

    public abstract boolean verifyMove(int targetX, int targetY);
}