package Shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shapes {
    public Shapes() {}
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveDown();
    public abstract void turn();
    public abstract void setColor( Color c );
    public abstract int getXA();
    public abstract int getY();
    public abstract boolean[][] getArray();
    public abstract Color getColor();
    public abstract boolean getBlock( int r, int c );
    public abstract void draw( Graphics page );
}