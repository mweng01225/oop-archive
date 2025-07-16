package lab4.figures;

import lab4.interfaces.IntFigure;

/**
 * Abstract base class for chess figures.
 * Stores shared properties like color and position.
 * 
 * @author Calleros, Enrique
 */

public abstract class Figure implements IntFigure{
    protected String color;         // 'WHITE' or 'BLACK'
    protected LocationX x;          // which column
    protected int y;                // which row


    /**
     * Creates a generic chess figure.
     * 
     * @param c the color of the figure
     * @param posX starting x position
     * @param posY starting y position
     * @author Calleros, Enrique
     */
    public Figure(String c, LocationX posX, int posY){
        this.color = c;
        this.x = posX;
        this.y = posY;
    }
}