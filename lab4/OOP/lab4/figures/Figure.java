package lab4.figures;

import lab4.interfaces.IntFigure;

public abstract class Figure implements IntFigure{
    protected String color;         // 'WHITE' or 'BLACK'
    protected LocationX x;          // which column
    protected int y;                // which row

    public Figure(String c, LocationX posX, int posY){
        this.color = c;
        this.x = posX;
        this.y = posY;
    }
}